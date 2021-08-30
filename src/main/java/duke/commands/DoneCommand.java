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
     * Returns if the command is the exit command.
     *
     * @return false since this command is not the exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command. Adds deadline to task list. Updates the save file.
     *
     * @param tasks the task list.
     */
    @Override
    public void execute(TaskList tasks) throws IOException {
        tasks.markAsDone(doneIndex - 1);

        System.out.println("-------------------------------------");
        System.out.println("Very well. This task has been marked as per your request.");
        System.out.println((doneIndex) + ". " + tasks.get(doneIndex - 1)); //actual index is index - 1
        System.out.println("-------------------------------------");
    }
}
