package duke.task;

public abstract class Task {
    private String content;
    private boolean status;

    Task(String content) {
        this.content = content;
        status = false;
    }

    public String getContent() {
        return content;
    }

    public boolean getStatus() {
        return status;
    }

    public void doneTask() {
        status = true;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s",
                status ? "x" : " ", content);
    }
    
    abstract public String record();
    abstract public boolean hasSchedule();
    abstract public String getType();

}
