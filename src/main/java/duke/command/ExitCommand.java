package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;


/**
 * An ExitCommand class that extends from the Command class.
 * @author KelvinSoo
 * @version A-MoreOOP
 */
public class ExitCommand extends Command{

    private String EXIT_MESSAGE = "Bye!";

    /**
     * An empty constructor to initialize an exit command.
     */
    public ExitCommand() {
    }

    /**
     * a method to execute a command.
     * @param taskList The task list to execute the command on.
     * @param storage The place to store the session.
     * @return the exit message.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        return EXIT_MESSAGE;
    }

    /**
     * A boolean to notate if this is an exit command.
     * @return false.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
