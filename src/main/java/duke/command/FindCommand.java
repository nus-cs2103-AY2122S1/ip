package duke.command;
import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.Find;
import duke.task.TaskList;

public class FindCommand extends Command {
    String keyword;

    public FindCommand(String input) {
        this.keyword = input.substring(5);
    }

    @Override
    public void execute(TaskList ls, Ui ui, Storage storage) throws DukeException {
        Find f = new Find(keyword, ls);
        f.findWord();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
