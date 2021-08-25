package pilcrow;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class Task {
    protected String taskName;
    protected Boolean isDone;

    private Task(String taskName, Boolean isDone) {
        this.taskName = taskName;
        this.isDone = isDone;
    }

    /**
     * Factory method to create a Task.
     * @param taskType String representing type of Task to be created. Maybe "todo", "deadline" or "event".
     * @param taskName Name of the Task to be created.
     * @param isDone Done status of the Task to be created.
     * @return Task with specified fields.
     */
    public static Task createTask(String taskType, String taskName, Boolean isDone) {
        Task task;
        if (taskType.equals("todo")) {
            task = new ToDo(taskName, isDone);
        } else if (taskType.equals("deadline")) {
            task = new Deadline(taskName, isDone);
        } else if (taskType.equals("event")) {
            task = new Event(taskName, isDone);
        } else {
            task = new Task("", false);
        }
        return task;
    }

    /**
     * Converted a String that has been stored in Storage into a Task.
     * @param storedTask String representing the stored task in Storage.
     * @return Converted Task
     */
    public static Task convertFromStoredTask(String storedTask) {
        Task task;
        Parser parser = new Parser(storedTask);
        String commandWord = parser.getCommandWord();
        String restOfCommand = parser.getRestOfCommand().substring(1);
        Boolean isDone = parser.getRestOfCommand().charAt(0) == 'T';

        task = Task.createTask(commandWord, restOfCommand, isDone);
        return task;
    }

    /**
     * Dummy method, overridden by methods in subclasses.
     * @return Dummy task
     */
    public String convertToStoredTask() {
        String storedTask = "";
        return storedTask;
    }

    public Boolean containsString(String string) {
        return this.taskName.contains(string);
    }

    public void setIsDone(Boolean isDone) {
        this.isDone = isDone;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public Boolean getIsDone() {
        return this.isDone;
    }

    @Override
    public String toString() {
        String taskAsString;
        taskAsString = "[" + (this.isDone ? 'X' : ' ') + "] " + this.taskName;
        return taskAsString;
    }

    private static class ToDo extends Task {
        private ToDo(String taskName, Boolean isDone) {
            super(taskName, isDone);
        }

        /**
         * Converts a ToDo into String form to be stored.
         * @return String representing ToDo.
         */
        @Override
        public String convertToStoredTask() {
            String storedTask = "todo " + (this.isDone ? 'T' : 'F') + this.taskName;
            return storedTask;
        }

        @Override
        public String toString() {
            String taskAsString;
            taskAsString = "[T] " + super.toString();
            return taskAsString;
        }
    }

    private static class Deadline extends Task {
        private LocalDate deadline;

        private Deadline(String taskName, Boolean isDone) {
            super(taskName, isDone);

            // Retrieve Deadline name as String
            taskName.trim();
            if (taskName.length() == 0) {
                throw new InvalidInputException("Must enter valid description.");
            }
            if (!taskName.contains("/")) {
                throw new InvalidInputException("Must indicate deadline with '/' symbol.");
            }
            String deadlineName = taskName.substring(0, taskName.indexOf("/")).trim();
            if (deadlineName.length() == 0 ) {
                throw new InvalidInputException("Must enter valid deadline name.");
            }
            this.taskName = deadlineName;

            // Retrieve Deadline deadline as LocalDate
            String deadline = taskName.substring(taskName.indexOf("/") + 1).trim();
            try {
                this.deadline = LocalDate.parse(deadline);
            } catch (DateTimeParseException exception) {
                throw new InvalidInputException("Must enter a valid date.");
            }
        }

        /**
         * Converts a Deadline into String form to be stored.
         * @return String representing Deadline.
         */
        @Override
        public String convertToStoredTask() {
            String storedTask = "deadline " + (this.isDone ? 'T' : 'F') + this.taskName + "/" + this.getDeadline();
            return storedTask;
        }

        public LocalDate getDeadline() {
            return this.deadline;
        }

        @Override
        public String toString() {
            String taskAsString;
            taskAsString = "[D] " + super.toString() + " ("
                    + this.deadline.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ")";
            return taskAsString;
        }
    }

    private static class Event extends Task {
        String duration;

        private Event(String taskName, Boolean isDone) {
            super(taskName, isDone);

            // Retrieve Event name as String
            taskName.trim();
            if (taskName.length() == 0) {
                throw new InvalidInputException("Must enter valid description.");
            }
            if (!taskName.contains("/")) {
                throw new InvalidInputException("Must indicate duration with '/' symbol.");
            }
            String eventName = taskName.substring(0, taskName.indexOf("/")).trim();
            if (eventName.length() == 0 ) {
                throw new InvalidInputException("Must enter valid event name.");
            }
            this.taskName = eventName;

            // Retrieve Event duration as String
            String duration = taskName.substring(taskName.indexOf("/") + 1).trim();
            this.duration = duration;
        }

        /**
         * Converts an Event into String form to be stored.
         * @return String representing Event.
         */
        @Override
        public String convertToStoredTask() {
            String storedTask = "event " + (this.isDone ? 'T' : 'F') + this.taskName + "/" + this.getDuration();
            return storedTask;
        }

        public String getDuration() {
            return this.duration;
        }

        @Override
        public String toString() {
            String taskAsString;
            taskAsString = "[E] " + super.toString() + " (" + this.duration + ")";
            return taskAsString;
        }
    }
}