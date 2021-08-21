package aisu.command;

import aisu.AisuException;
import aisu.Storage;
import aisu.TaskList;
import aisu.Ui;

public class FindCommand extends Command {
    private final String input;

    public FindCommand(String input) {
        this.input = input.trim();
    }

    @Override
    public void execute(TaskList tasklist, Storage storage, Ui ui) throws AisuException {
        String result = tasklist.findTasksWith(this.input);
        ui.showToUser("Here's what I found:", result);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
