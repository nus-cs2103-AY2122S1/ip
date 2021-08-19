import java.util.ArrayList;
import java.util.Scanner;

/**
 * Pilcrow is a personal assistant chatbot made for CS2103, based off of Duke.
 */
public class Pilcrow {
    private final static String PILCROW_LOGO = """
              _____
             /   | |
            |    | |
            |    | |
             \\___| |
                 | |
                 | |
                _| |_
               |_|_|_|
            """;

    /**
     * Runs the main body of the Pilcrow script.
     * @param args
     */
    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Reads the text from the scanner
            String text = scanner.nextLine();
            String trimmedText = text.trim();
            String[] splitText = trimmedText.split(" ");
            ArrayList<String> words = new ArrayList<>();
            for (int i = 0; i < splitText.length; i++) {
                if (!splitText[i].equals("")) {
                    words.add(splitText[i]);
                }
            }

            // Parses the text and does the appropriate action
            try {
                Pilcrow.parseText(trimmedText, words, tasks);
            } catch (InvalidInputException exception) {
                System.out.println(exception);
            }

            // Exit while loop if first word of trimmed text is "bye"
            if (words.size() > 0 && words.get(0).equals("bye")) {
                break;
            }
        }
    }

    private static void parseText(String trimmedText, ArrayList<String> words, ArrayList<Task> tasks)
            throws InvalidInputException {
        String displayText = "";

        // Exception thrown if input is empty
        if (words.size() == 0) {
            throw new InvalidInputException("Nothing entered.");
        }

        String command = words.get(0);

        // Chooses what to do based on which command is entered
        switch (command) {
        case "todo":
            // Exception thrown if description of todo is empty
            if (words.size() == 1) {
                throw new InvalidInputException("Description of todo cannot be empty.");
            }
            String toDoName = trimmedText.substring(trimmedText.indexOf(" ") + 1);
            Task newTask = new ToDo(toDoName);
            tasks.add(newTask);
            displayText = "Added todo to tasks(" + tasks.size() + "): \n" + newTask.toString();
            break;
        case "deadline":
            // Fallthrough
        case "event":
            // Exception thrown if description of event/deadline is empty
            if (words.size() == 1) {
                throw new InvalidInputException("Description of event/deadline cannot be empty.");
            }
            // Exception thrown if description of event/deadline contains no '/'
            if (!trimmedText.contains("/")) {
                throw new InvalidInputException("No duration/deadline specified with '/'.");
            }

            String taskName = trimmedText.substring(0, trimmedText.indexOf("/")).trim();
            // Exception thrown if description of event/deadline is empty
            if (!taskName.contains(" ")) {
                throw new InvalidInputException("Description of event/deadline cannot be empty.");
            }
            taskName = taskName.substring(taskName.indexOf(" "));
            String taskTime = trimmedText.substring(trimmedText.indexOf("/") + 1);
            if (command.equals("deadline")) {
                newTask = new Deadline(taskName, taskTime);
                tasks.add(newTask);
                displayText = "Added deadline to tasks(" + tasks.size() + "): \n" + newTask.toString();
            } else if (command.equals("event")) {
                newTask = new Event(taskName, taskTime);
                tasks.add(newTask);
                displayText = "Added event to tasks(" + tasks.size() + "): \n" + newTask.toString();
            }
            break;
        case "list":
            displayText = Pilcrow.printAllTasks(tasks);
            break;
        case "done":
            // Exception thrown if no word given after done command
            if (words.size() == 1) {
                throw new InvalidInputException("Must specify a task as done.");
            }

            // Exception thrown if word given cannot be converted to integer
            int doneTask;
            try {
                doneTask = Integer.valueOf(words.get(1));
            } catch (NumberFormatException exception) {
                throw new InvalidInputException("Must specify task number as an integer.");
            }

            int taskIndex = doneTask - 1;
            // Exception thrown if task specified does not exist
            if (taskIndex >= tasks.size()) {
                throw new InvalidInputException("Must specify a task that exists.");
            }
            tasks.get(taskIndex).markAsDone(true);
            displayText = "Task " + doneTask + " set as done:\n" + tasks.get(taskIndex).toString();
            break;
        case "bye":
            displayText = "C'est fini.\n" + Pilcrow.PILCROW_LOGO;
            break;
        default:
            newTask = new Task(trimmedText);
            tasks.add(newTask);
            displayText = "Added generic task to tasks(" + tasks.size() + "): \n" + newTask.toString();
            break;
        }
        System.out.println(displayText);
    }

    private static String printAllTasks(ArrayList<Task> tasks) {
        String tasksText = "";
        for (int i = 0; i < tasks.size(); i++) {
            tasksText += (i + 1) + ". " + tasks.get(i).toString() + "\n";
        }
        return tasksText;
    }

    // Inner classes for Task and its subclasses
    private static class Task {
        protected String taskName;
        protected Boolean isDone;

        /**
         * Constructor for a generic Task.
         * @param taskName Task name
         */
        public Task(String taskName) {
            this.taskName = taskName;
            this.isDone = false;
        }

        public void markAsDone(Boolean isDone) {
            this.isDone = isDone;
        }

        /**
         * Converts Task into a string.
         * @return Task in string form
         */
        @Override
        public String toString() {
            String taskText = "    [" + (this.isDone ? 'X' : ' ') + "] " + taskName;
            return taskText;
        }
    }

    private static class ToDo extends Task {
        /**
         * Constructor for a ToDo Task.
         * @param toDoName ToDo Task name
         */
        public ToDo(String toDoName) {
            super(toDoName);
        }

        /**
         * Converts ToDo Task into a string.
         * @return ToDo Task in string form
         */
        @Override
        public String toString() {
            String toDoText = "[T] [" + (this.isDone ? 'X' : ' ') + "] " + taskName;
            return toDoText;
        }
    }

    private static class Deadline extends Task {
        String deadline;

        /**
         * Constructs a Deadline Task.
         * @param deadlineName Deadline Task name
         * @param deadline actual deadline
         */
        public Deadline(String deadlineName, String deadline) {
            super(deadlineName);
            this.deadline = deadline;
        }

        /**
         * Converts Deadline Task into string.
         * @return Deadline Task in string form
         */
        @Override
        public String toString() {
            String toDoText = "[D] [" + (this.isDone ? 'X' : ' ') + "] " + taskName + " (" + this.deadline + ")";
            return toDoText;
        }
    }

    private static class Event extends Task {
        private String duration;

        /**
         * Constructs an Event Task.
         * @param eventName Event Task name
         * @param duration duration of Event Task
         */
        public Event(String eventName, String duration) {
            super(eventName);
            this.duration = duration;
        }

        /**
         * Converts Event Task into string.
         * @return Event Task in string form
         */
        @Override
        public String toString() {
            String toDoText = "[E] [" + (this.isDone ? 'X' : ' ') + "] " + taskName + " (" + this.duration + ")";
            return toDoText;
        }
    }
}