package duke.commands;

import java.io.IOException;

import duke.Storage;
import duke.TaskList;

/**
 * Command that deletes a task.
 */
public class DeleteCommand extends Command {
    private int deleteIndex;

    /**
     * Constructor for DeleteCommand.
     *
     * @param desc        description of the delete command.
     * @param deleteIndex the index of the task to be deleted.
     */
    public DeleteCommand(String desc, int deleteIndex) {
        super(desc);
        this.deleteIndex = deleteIndex;

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
     * @param tasks   the task list.
     * @param storage the storage of the programme.
     */
    @Override
    public void execute(TaskList tasks, Storage storage) {
        System.out.println("-------------------------------------");
        System.out.println("Very well, Master Wayne. This task has been deleted as per your request.");
        System.out.println((deleteIndex) + ". " + tasks.get(deleteIndex - 1)); //actual index is index - 1

        tasks.remove(deleteIndex - 1);
        if (tasks.size() == 1) {
            System.out.println("Now you have 1 task in the list.");
        } else {
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        }
        System.out.println("-------------------------------------");

        try {
            storage.delete(deleteIndex);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
