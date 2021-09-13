package pika.command;

import java.io.IOException;

import pika.exception.PikaException;
import pika.task.Task;
import pika.task.TaskList;
import pika.ui.Storage;
import pika.ui.Ui;

/**
 * DeleteCommand class to handle the deletion of task from the list.
 */
public class DeleteCommand extends Command {
    private final int index;


    /**
     * Constructor for the DeleteCommand.
     *
     * @param input The index of the task to be deleted.
     * @throws PikaException catches if the input is invalid.
     */
    public DeleteCommand(String input) throws PikaException {
        super(true);
        if (input == null) {
            //Catch if there is no number after the command
            throw new PikaException("☹ OOPS!!! delete will require a task number to update.");
        }
        this.index = Integer.parseInt(input.trim());
    }

    /**
     * Executes the DeleteCommand to delete the task from the list,
     * update the respective list and txt file.
     *
     * @param taskList The current list of tasks.
     * @param storage  The current storage class to handle the txt file.
     * @return the string that the bot will say once the delete command is executed.
     * @throws IOException   if the filepath has any issues.
     * @throws PikaException if there are any other format/input issues.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws IOException, PikaException {
        if (index > taskList.getCount() || index <= 0) {
            //Catches if the number is > than the number of task or if its negative
            throw new PikaException("Pika pi!! The number is not in within the number of tasks!");
        } else {
            Task t = taskList.delete(index - 1);
            Storage.updateText(taskList);
            return Ui.deleteMessage(t, taskList.getCount());
        }
    }
}
