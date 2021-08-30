package duke.commands;

import duke.TaskList;

/**
 * Command that is not supported by the programme.
 */
public class UnknownCommand extends Command {
    /**
     * Constructor for UnknownCommand.
     *
     * @param desc description of the UnknownCommand.
     */
    public UnknownCommand(String desc) {
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
     * @param tasks the task list.
     */
    @Override
    public void execute(TaskList tasks) {
        System.out.println("*** Apologies, Master Wayne. But I don't know what that means ***");
    }
}
