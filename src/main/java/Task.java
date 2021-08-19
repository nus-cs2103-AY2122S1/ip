public class Task {
    private final String description;
    private boolean isDone;
    private String isDoneBox;
    private String taskType;
    static int noOfTask = 0;

    public Task(String description, String taskType) throws TaskDescriptionException {
        if (description.equals("")) {
            throw new TaskDescriptionException();
        } else {
            this.description = description;
            this.isDone = false;
            this.isDoneBox = "[ ]";
            this.taskType = taskType;
            noOfTask++;
        }
    }

    public String checkIsDone() {
        return this.isDoneBox;
    }

    public String getDescription() {
        return this.description;
    }

    public void markAsDone() {
        this.isDone = true;
        this.isDoneBox = "[X]";
    }

    public String getTaskType() {
        return this.taskType;
    }
}
