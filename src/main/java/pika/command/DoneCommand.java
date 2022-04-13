package pika.command;

import java.io.IOException;

import pika.exception.PikaException;
import pika.task.TaskList;
import pika.ui.Storage;
import pika.ui.Ui;

/**
 * DoneCommand Class to handle the done command.
 */
public class DoneCommand extends Command { //DoneCommand to handle the updating of list
    private final int index;

    /**
     * Constructor for the DoneCommand Class.
     *
     * @param input index for the task that is to be marked as done.
     * @throws PikaException Catches if the input is invalid.
     */
    public DoneCommand(String input) throws PikaException {
        super(true);
        if (input == null) {
            //Catch if there is no number after the command
            throw new PikaException("Pika pi!! done will require a task number to update.");
        }
        this.index = Integer.parseInt(input.trim());
    }

    /**
     * Executes the DoneCommand to update the task in the list as done.
     * Also updates the list and txt files accordingly.
     *
     * @param taskList The current list of tasks.
     * @param storage  The current storage class to handle the txt file.
     * @return the string that the bot will say once the done command is executed.
     * @throws IOException   If the filepath has issue.
     * @throws PikaException catches if the input/format is wrong.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws IOException, PikaException {
        if (index > taskList.getCount() || index <= 0) {
            //Catches if the number is > than the number of task or if its negative
            throw new PikaException("Pika pi!! The number is not in within the number of tasks!");
        } else {
            if (taskList.get(index - 1).isDone()) {
                throw new PikaException("Pika pi!! That task has already been completed!");
            }
            String done = taskList.get(index - 1).markAsDone();
            Storage.updateText(taskList);
            return Ui.doneMessage(done);
        }
    }
}
