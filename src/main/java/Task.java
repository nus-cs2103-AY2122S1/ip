public class Task {
    protected String description;
    protected boolean isDone;
    protected String taskType;

    public Task(String description, String taskType) throws DukeException {
        if (description.length() == 0) {
            throw new NoDescriptionException(taskType);
        }
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
    }

    public String saveTaskFormat() {
        return String.format("|%s|%s", isDone? 1 : 0, description);
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void setDone() {
        this.isDone = true;
    }

    @Override
    public String toString(){
        return "[" + getStatusIcon() + "] " + description;
    }
}