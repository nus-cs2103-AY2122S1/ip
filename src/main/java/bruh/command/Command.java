package bruh.command;

import bruh.storage.Storage;
import bruh.tasklist.TaskList;
import bruh.ui.Ui;

public abstract class Command {
    private String description;

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        description = runAndGenerateDescription(taskList, ui, storage);
    }

    abstract public String runAndGenerateDescription(TaskList taskList, Ui ui, Storage storage);

    public String getDescription() {
        return description;
    }

    public boolean isExit() {
        return false;
    }
}
