package nyx.command;

import java.io.IOException;

import nyx.NyxException;
import nyx.Storage;
import nyx.task.TaskList;

/**
 * Represents a command to mark task as done.
 */
public class DoneCommand extends Command {
    /**
     * Constructs a DoneCommand object.
     *
     * @param information Information needed to mark a task as done.
     */
    public DoneCommand(String information) {
        super(information);
    }

    /**
     * Performs operations needed to mark a task as done.
     *
     * @param taskList TaskList object containing all the tasks.
     * @param storage Storage object to deal with hard disk related operations.
     * @return String representation of the output for the user.
     * @throws NyxException If an error is encountered while marking a task.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws NyxException {
        try {
            int index = Integer.parseInt(information) - 1;
            if (taskList.markDone(index)) {
                storage.overwriteData(taskList);
                return String.format("Nice! I've marked this task as done:\n  %s",
                        taskList.getTask(index));
            } else {
                return ("No task to mark!");
            }
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new NyxException("Invalid task index!");
        } catch (IOException e) {
            throw new NyxException("Unable to save the changes...");
        }
    }

    public static void throwEmptyException() throws NyxException {
        throw new NyxException("Please specify the index to mark!");
    }
}

