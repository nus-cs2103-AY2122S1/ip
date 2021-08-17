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

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void setDone() {
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done:\n"
                + this.toString());
    }

    @Override
    public String toString(){
        return "[" + getStatusIcon() + "] " + description;
    }
}