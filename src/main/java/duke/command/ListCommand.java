package duke.command;

import duke.ui.Storage;
import duke.ui.TaskList;
import duke.ui.Ui;

public class ListCommand extends Command { //ListCommand to handle the showing of list

    public ListCommand() {
        super(true);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Ui.printList(taskList);
    }
}