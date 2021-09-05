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
     * @param desc          description of the delete command.
     * @param deleteIndexes the index of the task to be deleted.
     */
    public DeleteCommand(String desc, ArrayList<Integer> deleteIndexes) {
        super(desc);
        Collections.sort(deleteIndexes);
        this.deleteIndexes = deleteIndexes;

    }

    /**
     * Executes the command. Adds deadline to task list. Updates the save file.
     */
    @Override
    public String execute(TaskList tasks) throws IOException {
        StringBuilder replyBuilder = new StringBuilder();

        if (deleteIndexes.size() == 1) {
            replyBuilder.append("Very well. This task has been deleted as per your request.\n");
        } else {
            replyBuilder.append("Very well. This tasks have been deleted as per your request.\n");
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
