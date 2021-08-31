package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.exception.InvalidFormatException;

/**
 * Class that handles the Find command
 */
public class Find extends Command {

    private final String[] words;
    
    public Find(String[] words) {
        this.words = words;
    }
    
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (this.words.length != 2) {
            throw new InvalidFormatException("`find ${keyword}`");
        } else {
            String keyword = this.words[1];
            ui.print(taskList.stringifyTasksForFind(keyword));
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
