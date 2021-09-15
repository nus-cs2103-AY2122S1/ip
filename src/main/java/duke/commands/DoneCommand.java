package duke.commands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import duke.TaskList;

/**
 * Command that marks task.
 */
public class DoneCommand extends Command {
    private ArrayList<Integer> doneIndexes;

    /**
     * Constructor for DoneCommand
     *
     * @param doneIndexes The indexes of tasks to be marked.
     */
    public DoneCommand(ArrayList<Integer> doneIndexes) {
        super("");
        Collections.sort(doneIndexes);
        this.doneIndexes = doneIndexes;
    }

    /**
     * Executes the command. Mark tasks as done.
     *
     * @param tasks The Task list.
     * @return The reply of Duke to the user.
     */
    @Override
    public String execute(TaskList tasks) throws IOException {
        StringBuilder replyBuilder = new StringBuilder();
        int numberOfTasksDone = doneIndexes.size();

        if (numberOfTasksDone == 1) {
            replyBuilder.append("Very well. This task has been marked.\n");
        } else {
            replyBuilder.append("Very well. These tasks have been marked.\n");
        }

        for (int index : doneIndexes) {
            tasks.markAsDone(index);
            replyBuilder.append(index + ". " + tasks.get(index) + "\n");
        }

        return replyBuilder.toString();
    }
}
