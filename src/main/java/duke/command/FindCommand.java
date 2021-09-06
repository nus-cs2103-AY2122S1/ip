package duke.command;

import duke.Archive;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class FindCommand extends Command {
    private String word;

    public FindCommand(String word) {
        this.word = word;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage, Archive archive) {
        return ui.find(taskList, word);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
