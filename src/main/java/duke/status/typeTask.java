package duke.status;

/**
 * Enumeration to indicate task name
 */
public enum typeTask {
    TODO("todo"), DEADLINE("deadline"), EVENT("event");
    private final String typeOfTask;

    typeTask(String typeOfTask) {
        this.typeOfTask = typeOfTask;
    }

    /**
     * returns corresponding name of task
     * 
     * @return String of task name
     */
    public String getTask() {
        return this.typeOfTask;
    }

}