package duke.task;

import java.time.LocalDate;

public class Task {
    protected String content;
    protected boolean isDone;

    /** Creates a task which is not done by default. */
    public Task(String content) {
        this.content = content;
        this.isDone = false;
    }

    /** Creates a task. */
    public Task(String content, boolean isDone) {
        this.content = content;
        this.isDone = isDone;
    }

    public void setDone() {
        this.isDone = true;
    }

    public String encoding() {
        return (isDone ? "Done" : "InProgress") + "&&" + content;
    }

    public String getContent() {
        return content;
    }

    public LocalDate getDate() {
        return null;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", isDone ? "X" : " ", this.content);
    }


}
