package eightbit.command;

import eightbit.util.Storage;
import eightbit.util.TaskList;

/**
 * Represents a command to exit the program.
 */
public class ByeCommand extends Command {

    /**
     * Terminates the program.
     *
     * @param taskList User's list of tasks.
     * @param storage  Storage responsible for reading/writing the file.
     * @return The response after executing the command.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        return "Bye. Hope to see you again soon!";
    }
}
