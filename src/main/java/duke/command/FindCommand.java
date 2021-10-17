package duke.command;

import duke.IncompleteCommandException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class FindCommand extends Command {

    public FindCommand(String input) {
        super(input);
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws Exception {
        if(input.length() <= 5) {
            throw new IncompleteCommandException("OOPS incomplete command! Your find command "
                    + "should have a text after the find like: find book");
        }
        this.input = input.substring(5);
        return taskList.findAndDisplay(super.input);
    }
}
