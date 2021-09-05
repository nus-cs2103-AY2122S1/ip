package duke.commands;

import java.io.IOException;

import duke.TaskList;

/**
 * Command that marks task.
 */
public class DoneCommand extends Command {
    private int doneIndex;

    /**
     * Constructor for DoneCommand
     *
     * @param desc
     * @param doneIndex
     */
    public DoneCommand(String desc, int doneIndex) {
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
        tasks.markAsDone(doneIndex - 1);

        StringBuilder replyBuilder = new StringBuilder();

        replyBuilder.append("Very well. This task has been marked as per your request.\n");
        replyBuilder.append((doneIndex) + ". " + tasks.get(doneIndex - 1) + "\n"); //actual index is index - 1

        return replyBuilder.toString();
    }
}
