package pika.command;

import pika.exception.PikaException;
import pika.ui.Storage;
import pika.ui.TaskList;
import pika.ui.Ui;

/**
 * FindCommand Class for the find commands
 */
public class FindCommand extends Command {
    private final String string;

    /**
     * FindCommand Constructor
     *
     * @param string is the string to be searched
     */
    public FindCommand(String string) throws PikaException {
        super(true);
        if (string == null) {
            throw new PikaException("Pika pi!! find will require a pattern to find.");
        } else {
            this.string = string;
        }
    }

    /**
     * Executes the Search Command to print the list of task with the given string to the user via the Ui
     *
     * @param taskList The current list of tasks
     * @param ui       The current Ui
     * @param storage  The current storage class to handle the txt file
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return Ui.searchList(taskList, this.string);
    }
}
