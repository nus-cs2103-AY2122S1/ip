package duke.commands;

import java.io.IOException;
import java.util.ArrayList;

import duke.TaskList;

/**
 * Command that marks task.
 */
public class DoneCommand extends Command {
    private ArrayList<Integer> doneIndex;

    /**
     * Constructor for DoneCommand
     *
     * @param desc
     * @param doneIndex
     */
    public DoneCommand(String desc, ArrayList<Integer> doneIndex) {
        super(desc);
        this.doneIndex = doneIndex;
    }

    /**
     * Executes the command. Adds deadline to task list. Updates the save file.
     *
     * @param tasks the task list.
     */
    @Override
    public String execute(TaskList tasks) throws IOException {
        StringBuilder replyBuilder = new StringBuilder();
        if (doneIndex.size() == 1) {
            replyBuilder.append("Very well. This task has been marked as per your request.\n");
        } else {
            replyBuilder.append("Very well. This tasks have been marked as per your request.\n");
        }

        for (Integer index : doneIndex) {
            tasks.markAsDone(index - 1);
            replyBuilder.append(index + ". " + tasks.get(index - 1) + "\n"); //actual index is index - 1
        }

        return replyBuilder.toString();
    }
}
