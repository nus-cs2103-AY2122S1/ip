package biscuit.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import biscuit.exceptions.BiscuitException;
import biscuit.storage.Storage;
import biscuit.task.Task;
import biscuit.task.TaskList;

/**
 * Delete command to delete task.
 */
public class DeleteCommand extends Command {

    /**
     * Constructs DeleteCommand class.
     *
     * @param userInputs User input array with this structure: [command, details].
     */
    public DeleteCommand(String[] userInputs) {
        super(CommandType.DELETE, userInputs);
    }

    /**
     * Deletes specified task from list.
     *
     * @param taskList Task list.
     * @param storage  Storage to save to.
     * @return Response to user input.
     * @throws BiscuitException Invalid input by user.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws BiscuitException {
        if (userInputs.length < 2) {
            throw new BiscuitException("\u0ED2(\u25C9\u1D25\u25C9)\u096D "
                    + "OOPS!!! The delete task number cannot be empty.");
        }

        try {
            String[] tasksToDelete = userInputs[1].split(" ");
            List<Task> deletedTasks = new ArrayList<>();
            // Parse the tasks to delete, sort them in reverse before deleting them
            Arrays.stream(tasksToDelete).map(indexString -> Integer.parseInt(indexString) - 1)
                    .sorted(Comparator.reverseOrder())
                    .forEach(index -> {
                        deletedTasks.add(0, taskList.getTask(index));
                        taskList.removeTask(index);
                    });
            storage.save();

            // Generate message to display to user
            StringBuilder message = new StringBuilder("Noted. I've removed the following task:");
            for (Task task : deletedTasks) {
                message.append("\n\t").append(task);
            }
            return message.append("\nNow you have ").append(taskList.size()).append(" tasks in the list.").toString();
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new BiscuitException("\u0ED2(\u25C9\u1D25\u25C9)\u096D OOPS!!!\n Please enter valid numbers"
                    + (taskList.size() == 1 ? " of 1" : " from 1 to " + taskList.size()) + ".");
        }
    }
}
