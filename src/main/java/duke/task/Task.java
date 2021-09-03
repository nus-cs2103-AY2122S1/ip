package duke.task;

import duke.DukeException;

/**
 * A wrapper for a task used by DukeList.
 *
 * @author Wong Yun Rui Chris
 */
public abstract class Task {
    /**
     *
     * Enum for the different type of task
     */
    public enum TaskName {
        TODO (""),
        DEADLINE (" /by "),
        EVENT (" /at ");

        private final String split;

        TaskName(String split) {
            this.split = split;
        }

        /**
         * Returns the corresponding regex/split needed to split the string input
         * received to get correct output.
         *
         * @return The regex used to split the input String for these task type
         */
        public String getSplit() {
            return this.split;
        }

        @Override
        public String toString() {
            return this.name().toLowerCase();
        }

        /**
         * Returns the TaskName when provided with the string name of the type of task.
         *
         * @param s The string representation of the type of task
         * @return The corresponding TaskName
         * @throws DukeException Exception specific to duke.Duke
         */
        public static TaskName getTaskType(String s) throws DukeException {
            switch (s) {
            case "todo":
                // Fallthrough
            case "[T]":
                return TODO;

            case "deadline":
                // Fallthrough
            case "[D]":
                return DEADLINE;

            case "event":
                // Fallthrough
            case "[E]":
                return EVENT;

            default:
                throw new DukeException("Something has gone very wrong!");
            }
        }
    }

    protected String description;
    protected boolean isDone;

    /**
     * A public constructor to initialise a duke.task.Task.
     *
     * @param description The String description/name of the task
     * @param isDone The Boolean of if the task is done
     */
    public Task(String description, Boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }


    /**
     * Gets the corresponding status icon for this task.
     *
     * @return The String representation of the status for this task
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Marks the status of this task as done.
     *
     * @return The new String representation of this duke.task.Task after the status is marked as done
     */
    public String markDone() {
        this.isDone = true;
        return this.toString();
    }

    /**
     * Returns the task formatted as a text string to be stored as data in a text file.
     * The task is stored with 1 and 0 representing the isDone status of the task with 1
     * representing the task is done while 0 is not done separated by a vertical line
     * then followed by the description of the task.
     *
     * @return The String representation of this task
     */
    public String toData() {
        return (this.isDone ? "1" : "0") + " | " + this.description;
    }

    /**
     * Return if the provided string matches with that of the task's description.
     *
     * @param keyword The String used to check against the task's description if they match
     * @return The boolean if the keyword matches with the task's description
     */
    public boolean matchKeyword(String keyword) {
        return this.description.contains(keyword);
    }
}