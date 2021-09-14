package duke.command;

import duke.Ui;
import duke.Storage;
import duke.notes.NotesList;
import duke.tasks.TaskList;


public class ExitCommand extends Command {
    String command;
    public ExitCommand(String command) {
        this.command = command;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.respondToBye();
    }

    @Override
    public String execute(NotesList notes, Ui ui, Storage storage) {
        return "";
    };

    @Override
    public Boolean isTaskRelatedCommand() {
        return true;
    };

    @Override
    public Boolean isExit() {
        return true;
    }
}
