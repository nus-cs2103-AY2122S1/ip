package duke.status;

public enum typeTask {
    TODO("todo"), DEADLINE("deadline"), EVENT("event");
    private final String typeOfTask;

    typeTask(String typeOfTask) {
        this.typeOfTask = typeOfTask;
    }

    public String getTask() {
        return this.typeOfTask;
    }

}