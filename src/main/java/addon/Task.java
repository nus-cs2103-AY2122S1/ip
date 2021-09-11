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
     * @return Boolean true if found, false otherwise.
     */
    public boolean queryIfNameContains(String query) {
        //Splits the query into words
        String[] querySplit = query.split(" ");
        int queryLength = querySplit.length; //Length of query

        //Splits the taskName into individual words
        String[] nameSplit = taskName.split(" ");
        int taskNameLength = nameSplit.length; //Length of task name

        if (taskNameLength < queryLength) { //If query length is clearly too long
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

    /**
     * Concats individual words into strings of n words.
     * @param sourceArray Array containing the original strings (individual words).
     * @param queryLength Desired length of strings.
     * @return Output array of generated strings.
     */
    public String[] concatNWords(String[] sourceArray, int queryLength) {
        int sourceArrayLength = sourceArray.length;
        int resultArrayLength = sourceArrayLength - queryLength + 1;
        String[] resultArray = new String[resultArrayLength];
        for (int i = 0; i < resultArrayLength; i++) {
            //Each string is a new one with the (i)th to (i+queryLength) word
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
            return super.toString() + " (DEADLINE due on: " + Ui.printDate(date) + ")";
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof Deadline) {
                Deadline d = (Deadline) o;
                boolean nameEquals = d.taskName.equals(taskName);
                boolean donenessEquals = d.isDone == isDone;
                boolean dateEquals = d.date.equals(date);
                return (nameEquals && donenessEquals && dateEquals);
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
            return super.toString() + " (TODO)";
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof Task) {
                Task t = (Task) o;
                boolean nameEquals = t.taskName.equals(taskName);
                boolean donenessEquals = t.isDone == isDone;
                return (nameEquals && donenessEquals);
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
            return super.toString() + " (EVENT occurs on: " + Ui.printDate(date) + ")";
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof Event) {
                Event e = (Event) o;
                boolean nameEquals = e.taskName.equals(taskName);
                boolean donenessEquals = e.isDone == isDone;
                boolean dateEquals = e.date.equals(date);
                return (nameEquals && donenessEquals && dateEquals);
            } else {
                return false;
            }
        }
    }
}
