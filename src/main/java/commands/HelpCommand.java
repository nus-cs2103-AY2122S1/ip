package commands;

import storage.Storage;
import tasks.TaskList;

/**
 * This is a HelpCommand Class, which inherits from Command.
 * The execution of this command will output the available list
 * of commands to the user.
 */
public class HelpCommand extends Command {
    public static final String KEYWORD = "help";
    public static final String INPUT_FORMAT = String.format("\t%s", KEYWORD);

    /**
     * Return list of available commands.
     * @param tasks The existing list of tasks
     * @param storage The storage object to store task data.
     * @return List of available commands.
     */
    public String execute(TaskList tasks, Storage storage) {
        String output = "Here are some of the commands you can use:\n"
                + "1." + AddToDoCommand.INPUT_FORMAT + "\n"
                + "2." + AddDeadlineCommand.INPUT_FORMAT + "\n"
                + "3." + AddEventCommand.INPUT_FORMAT + "\n"
                + "4." + DeleteCommand.INPUT_FORMAT + "\n"
                + "5." + FindCommand.INPUT_FORMAT + "\n"
                + "6." + MarkDoneCommand.INPUT_FORMAT + "\n"
                + "7." + ListCommand.INPUT_FORMAT
                + "8. " + HelpCommand.INPUT_FORMAT;
        return output;
    }
}
