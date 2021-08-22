package botto.command;

import botto.BottoException;
import botto.util.Storage;
import botto.util.TaskList;
import botto.util.Ui;

public interface Command {
    public void execute(TaskList taskList, Ui ui, Storage storage) throws BottoException;

    default public boolean isExit() {
        return false;
    }
}
