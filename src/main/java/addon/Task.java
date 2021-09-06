package addon;

import java.time.LocalDate;

/**
 * Parent class Task, contains doneness and task name
 */
public class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Task constructor.
     *
     * @param description Name of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String toString() {
        return ((isDone ? "[X] " : "[ ] ") + this.description);
    }

    /**
     * Checks if the description contains the keyword provided.
     *
     * @param str String to be queried.
     * @return true if found, false otherwise.
     */
    public boolean nameContains(String str) {

        String[] querySplit = str.split(" ");
        int queryLength = querySplit.length;
        String[] descriptionSplit = description.split(" ");
        int descriptionLength = descriptionSplit.length;
        if (descriptionLength < queryLength) {
            return false;
        } else if (descriptionLength == queryLength) {
            return description.equalsIgnoreCase(str);
        } else {
            String[] updatedDescriptionArray = concatNWords(descriptionSplit, queryLength);
            boolean result = false;
            for (String s : updatedDescriptionArray) {
                if (s.equalsIgnoreCase(str)) {
                    result = true;
                    break;
                }
            }
            return result;
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
         * @param description Name of deadline.
         * @param by Deadline of task.
         */
        public Deadline(String description, LocalDate by) {
            super(description);
            this.date = by;
        }

        @Override
        public String toString() {
            return "[D]" + super.toString() + " (by: " + Ui.printDate(date) + ")";
        }

        public boolean dateEquals(LocalDate theirDate) {
            return theirDate.equals(date);
        }
    }

    /**
     * Child class of Task
     */
    public static class Todo extends Task {

        /**
         * Todo constructor.
         *
         * @param description Name of task.
         */
        public Todo(String description) {
            super(description);
        }

        @Override
        public String toString() {
            return "[T]" + super.toString();
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
         * @param description Name of event.
         * @param at Date of event.
         */
        public Event(String description, LocalDate at) {
            super(description);
            this.date = at;
        }

        public boolean dateEquals(LocalDate theirDate) {
            return theirDate.equals(date);
        }

        @Override
        public String toString() {
            return "[E]" + super.toString() + " (at: " + Ui.printDate(date) + ")";
        }
    }
}
