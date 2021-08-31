package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/** A class that handles exit-program command. */
public class ExitCommand extends Command {

    /**
     * Execute the exit-program command.
     *
     * @param tasks The list that stores all the tasks to be added/deleted.
     * @param ui The ui that deals with interactions with the user.
     * @param storage The storage that deals with loading tasks from the file and saving tasks in the file.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return "\tBye. Have a nice day!";
    }

    /**
     * Return a boolean value of whether it is a command that exit the program.
     *
     * @return The boolean value of whether it is a command that exit the program.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
