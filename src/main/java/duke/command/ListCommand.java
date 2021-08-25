package duke.command;

import duke.ui.Storage;
import duke.ui.TaskList;
import duke.ui.Ui;

public class ListCommand extends Command { //ListCommand to handle the showing of list

    /**
     * Constructor for the ListCommand Class
     */
    public ListCommand() {
        super(true);
    }

    /**
     * Executes the ListCommand to print the list to the user via the Ui
     *
     * @param taskList The current list of tasks
     * @param ui       The current Ui
     * @param storage  The current storage class to handle the txt file
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Ui.printList(taskList);
    }
}