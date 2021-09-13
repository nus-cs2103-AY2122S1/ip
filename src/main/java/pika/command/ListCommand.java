package pika.command;

import pika.exception.PikaException;
import pika.task.TaskList;
import pika.ui.Storage;
import pika.ui.Ui;

/**
 * ListCommand Class to handle the showing of list
 */
public class ListCommand extends Command {

    /**
     * Constructor for ListCommand.
     *
     * @param details any other input by the user apart from the list command.
     * @throws PikaException if the user inputs anything extra.
     */
    public ListCommand(String details) throws PikaException {
        super(true);
        if (details != null) {
            throw new PikaException("Pika pi!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Executes the ListCommand to print the list to the user via the Ui.
     *
     * @param taskList The current list of tasks.
     * @param storage  The current storage class to handle the txt file.
     * @return the string that the bot will say once the list command is executed.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        return Ui.printList(taskList);
    }
}
