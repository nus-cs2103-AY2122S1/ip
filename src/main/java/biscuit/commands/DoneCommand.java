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
 * Done command to mark task as done.
 */
public class DoneCommand extends Command {

    /**
     * Constructs DoneCommand class.
     *
     * @param userInputs User input array with this structure: [command, details].
     */
    public DoneCommand(String[] userInputs) {
        super(CommandType.DONE, userInputs);
    }

    /**
     * Marks specified task as done.
     *
     * @param taskList Task list.
     * @param storage  Storage to save to.
     * @return Response to user input.
     * @throws BiscuitException Invalid input by user.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws BiscuitException {
        if (userInputs.length < 2) {
            throw new BiscuitException("\u0ED2(\u25C9\u1D25\u25C9)\u096D OOPS!!!\n"
                    + "The done task number cannot be empty.");
        }

        try {
            String[] tasksDone = userInputs[1].split(" ");
            List<Task> doneTasks = new ArrayList<>();
            // Parse the tasks to mark as done, sort them in reverse before marking them
            Arrays.stream(tasksDone).map(indexString -> Integer.parseInt(indexString) - 1)
                    .sorted(Comparator.reverseOrder())
                    .forEach(index -> {
                        Task current = taskList.getTask(index);
                        doneTasks.add(0, current);
                        current.setDone(true);
                    });
            storage.save();

            //Generate message to display to user
            StringBuilder message = new StringBuilder("Nice! I've marked this task as done, woof!");
            for (Task task : doneTasks) {
                message.append("\n\t").append(task);
            }
            return message.toString();
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new BiscuitException("\u0ED2(\u25C9\u1D25\u25C9)\u096D OOPS!!!\nPlease enter valid numbers"
                    + (taskList.size() == 1 ? " of 1" : " from 1 to " + taskList.size()) + ".");
        }
    }
}
