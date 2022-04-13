package bot.commands;

import static bot.constants.GlobalStringFormats.LINE_BREAK;

import java.util.List;

import bot.tasks.Task;
import bot.utility.Logger;
import bot.utility.TaskList;

/**
 * Represents a command to end the program.
 */
public class EndCommand extends Command {

    /**
     * Executes the Command and returns a String.
     *
     * @return A String to show to the user after execution of the EndCommand.
     */
    @Override
    public String execute() {
        List<Task> tasks = TaskList.showTasks();
        Logger.write(tasks);
        return LINE_BREAK + "Peace out!";
    }

    /**
     * Tells the MainWindow when IntelliBot can close.
     *
     * @return A boolean that says whether IntelliBot should close.
     */
    @Override
    public boolean canEnd() {
        return true;
    }
}
