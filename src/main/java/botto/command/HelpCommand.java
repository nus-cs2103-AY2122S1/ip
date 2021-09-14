package botto.command;

import botto.BottoException;
import botto.util.Dialog;
import botto.util.Storage;
import botto.util.TaskList;

public class HelpCommand implements Command {
    @Override
    public void execute(TaskList taskList, Dialog dialog, Storage storage) throws BottoException {
        dialog.showHelp();
    }
}
