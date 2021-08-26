package Duke.Tasks;

import Duke.Tool.Storage;
import Duke.Tool.TaskList;
import Duke.Ui;

import java.io.IOException;

public class Task {
    protected String description;
    protected boolean isDone;
    public boolean isExit;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
        this.isExit = false;
    }

    public String getStatusIcon() {
        return (this.isDone ? "[X] " : "[ ] "); // mark done task with X
    }

    public Task markDone() {
        this.isDone = true;
        return this;

    }

    public String formatChange() {
        String mark = isDone ? "1" : "0";
        return "|" + mark + "0" + this.description;

    }

    @Override
    public String toString() {
        return this.getStatusIcon()  + this.description;
    }


    public void excute(TaskList task, Ui ui, Storage storage) throws IOException {};

}
