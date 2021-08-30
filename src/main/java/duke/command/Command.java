package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public abstract class Command {
    private String type;
    private boolean isExit;

    public Command(String type) {
        this.type = type;
        this.isExit = false;
    }

    public String getType() {
        return type;
    }

    public boolean isExit() {
        return isExit;
    }

    public void setExit() {
        this.isExit = true;
    }

    public abstract void execute(TaskList taskList, Ui ui, Storage storage);
}
