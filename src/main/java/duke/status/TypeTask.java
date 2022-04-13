package duke.status;

/**
 * Enumeration to indicate task name.
 */
public enum TypeTask {

    /**
     * Indicates what type of task is it.
     */
    TODO("todo"), DEADLINE("deadline"), EVENT("event");

    /**
     * String version of each task.
     */
    private final String typeOfTask;

    TypeTask(String typeOfTask) {
        this.typeOfTask = typeOfTask;
    }

    /**
     * Returns corresponding name of task.
     * @return String of task name.
     */
    public String getTask() {
        return this.typeOfTask;
    }

}