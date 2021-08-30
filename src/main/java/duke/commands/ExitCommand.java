package duke.commands;

import duke.TaskList;

/**
 * Command that exits the programme.
 */
public class ExitCommand extends Command {
    /**
     * Constructor for ExitCommand.
     *
     * @param desc
     */
    public ExitCommand(String desc) {
        super(desc);
    }

    /**
     * Returns if the command is the exit command.
     *
     * @return true since this command is the exit command.
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Executes the command. Adds deadline to task list. Updates the save file.
     *
     * @param tasks the task list.
     */
    @Override
    public void execute(TaskList tasks) {
        System.out.println("Have a pleasant day, Master Wayne.\n");
    }
}
