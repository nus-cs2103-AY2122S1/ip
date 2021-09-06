package addon;

import java.time.LocalDate;

/**
 * Parent class Task, contains doneness and task name
 */
public class Task {

    protected String taskName;
    protected boolean isDone;

    /**
     * Task constructor.
     *
     * @param taskName Name of task.
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public String toString() {
        return ((isDone ? "[X] " : "[ ] ") + taskName);
    }

    /**
     * Checks if the taskName contains the keyword provided.
     *
     * @param query String to be queried.
     * @return true if found, false otherwise.
     */
    public boolean queryIfNameContains(String query) {
        String[] querySplit = query.split(" ");
        int queryLength = querySplit.length;

        String[] nameSplit = taskName.split(" ");
        int taskNameLength = nameSplit.length;

        if (taskNameLength < queryLength) {
            return false;
        } else if (taskNameLength == queryLength) {
            return taskName.equalsIgnoreCase(query);
        } else {
            String[] updatedNameArray = concatNWords(nameSplit, queryLength);
            boolean resultBoolean = false;
            for (String s : updatedNameArray) {
                if (s.equalsIgnoreCase(query)) {
                    resultBoolean = true;
                    break;
                }
            }
            return resultBoolean;
        }
    }

    public String[] concatNWords(String[] sourceArray, int queryLength) {
        int sourceArrayLength = sourceArray.length;
        int resultArrayLength = sourceArrayLength - queryLength + 1;
        String[] resultArray = new String[resultArrayLength];
        for (int i = 0; i < resultArrayLength; i++) {
            StringBuilder resultString = new StringBuilder();
            resultString.append(sourceArray[i]);
            for (int j = 1; j < queryLength; j++) {
                resultString.append(" ").append(sourceArray[i + j]);
            }
            resultArray[i] = resultString.toString();
        }
        return resultArray;
    }

    /**
     * Marks this task as done.
     */
    public void markDone() {
        isDone = !isDone;
    }

    /**
     * Child class of Task, adds deadline field.
     */
    public static class Deadline extends Task {
        protected LocalDate date;

        /**
         * Deadline constructor.
         *
         * @param taskName Name of deadline.
         * @param by Deadline of task.
         */
        public Deadline(String taskName, LocalDate by) {
            super(taskName);
            this.date = by;
        }

        public boolean queryIfDateEquals(LocalDate theirDate) {
            return theirDate.equals(date);
        }

        @Override
        public String toString() {
            return "[D]" + super.toString() + " (by: " + Ui.printDate(date) + ")";
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof Deadline) {
                Deadline d = (Deadline) o;
                return (d.taskName.equals(taskName) && d.date.equals(date));
            } else {
                return false;
            }
        }
    }

    /**
     * Child class of Task
     */
    public static class Todo extends Task {

        /**
         * Todo constructor.
         *
         * @param taskName Name of task.
         */
        public Todo(String taskName) {
            super(taskName);
        }

        @Override
        public String toString() {
            return "[T]" + super.toString();
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof Task) {
                Task t = (Task) o;
                return t.taskName.equals(taskName);
            } else {
                return false;
            }
        }
    }

    /**
     * Child class of Task, adds date field.
     */
    public static class Event extends Task {

        protected LocalDate date;

        /**
         * Event constructor.
         *
         * @param taskName Name of event.
         * @param at Date of event.
         */
        public Event(String taskName, LocalDate at) {
            super(taskName);
            this.date = at;
        }

        public boolean queryIfDateEquals(LocalDate theirDate) {
            return theirDate.equals(date);
        }

        @Override
        public String toString() {
            return "[E]" + super.toString() + " (at: " + Ui.printDate(date) + ")";
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof Event) {
                Event e = (Event) o;
                return (e.taskName.equals(taskName) && e.date.equals(date));
            } else {
                return false;
            }
        }
    }
}
