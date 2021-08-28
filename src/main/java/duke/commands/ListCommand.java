package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.tasks.Task;

/**
 * Command that lists the tasks in the task list.
 */
public class ListCommand extends Command {
    /**
     * Constructor for ListCommand.
     *
     * @param desc
     */
    public ListCommand(String desc) {
        super(desc);
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
        if (tasks.size() == 0) {
            System.out.println("You have not added anything to the list, Master Wayne.");
        } else {
            System.out.println("-------------------------------------");
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                System.out.println((i + 1) + ". " + " " + task.toString());
            }
            System.out.println("-------------------------------------");
        }
    }
}
