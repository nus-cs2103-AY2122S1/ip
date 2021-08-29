package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class FindCommand extends Command {
    private String word;

    public FindCommand(String word) {
        this.word = word;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.find(taskList, word);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
