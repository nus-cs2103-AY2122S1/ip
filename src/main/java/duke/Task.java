package duke;

import java.time.LocalDate;

class Task {
    protected String content;
    protected boolean isDone;
    Task(String content) {
        this.content = content;
        this.isDone = false;
    }

    Task(String content, boolean isDone) {
        this.content = content;
        this.isDone = isDone;
    }

    void setDone() {
        this.isDone = true;
    }

    String encoding() {
        return (isDone ? "Done" : "InProgress") + "&&" + content;
    }

    String getContent() {
        return content;
    }

    LocalDate getDate() {
        return null;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", isDone ? "X":" ", this.content);
    }


}
