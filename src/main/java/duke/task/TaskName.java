package duke.task;

import duke.DukeException;

/**
 *
 * Enum for the different type of task
 */
public enum TaskName {
    TODO ("", "[T]"),
    DEADLINE (" /by ", "[D]"),
    EVENT (" /at ", "[E]");

    private final String split;
    private final String taskIcon;

    TaskName(String split, String taskIcon) {
        this.split = split;
        this.taskIcon = taskIcon;
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

    /**
     * Returns the corresponding task icon used in the saved data text file.
     *
     * @return The String of the task icon for this task
     */
    public String getTaskIcon() {
        return this.taskIcon;
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
