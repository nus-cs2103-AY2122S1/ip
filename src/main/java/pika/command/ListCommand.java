package pika.command;

import pika.ui.Storage;
import pika.ui.TaskList;
import pika.ui.Ui;

public class ListCommand extends Command { //ListCommand to handle the showing of list

    /**
     * Constructor for the ListCommand Class.
     */
    public ListCommand() {
        super(true);
    }

    /**
     * Executes the ListCommand to print the list to the user via the Ui.
     *
     * @param taskList The current list of tasks
     * @param storage  The current storage class to handle the txt file
     * @return the string that the bot will say once the list command is executed
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        return Ui.printList(taskList);
    }
}
