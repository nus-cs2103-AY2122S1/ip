package duke.commands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import duke.TaskList;

/**
 * Command that deletes a task.
 */
public class DeleteCommand extends Command {
    private ArrayList<Integer> deleteIndexes;

    /**
     * Constructor for DeleteCommand.
     *
     * @param deleteIndexes The indexes of the tasks to be deleted.
     */
    public DeleteCommand(ArrayList<Integer> deleteIndexes) {
        super("");
        Collections.sort(deleteIndexes);
        this.deleteIndexes = deleteIndexes;

    }

    /**
     * Executes the command. Deletes the tasks.
     *
     * @param tasks The task list.
     * @return The reply of Duke to the user.
     */
    @Override
    public String execute(TaskList tasks) throws IOException {
        StringBuilder replyBuilder = new StringBuilder();

        if (deleteIndexes.size() == 1) {
            replyBuilder.append("Very well. This task has been deleted.\n");
        } else {
            replyBuilder.append("Very well. These tasks have been deleted.\n");
        }

        int buffer = 0;
        for (Integer index : deleteIndexes) {
            replyBuilder.append(index + ". " + tasks.get(index - buffer) + "\n"); //actual index is index - 1
            tasks.remove(index - buffer);
            buffer++;
        }

        if (tasks.size() == 1) {
            replyBuilder.append("Now you have 1 task in the list. \n");
        } else {
            replyBuilder.append("Now you have " + tasks.size() + " tasks in the list. \n");
        }

        return replyBuilder.toString();
    }
}
