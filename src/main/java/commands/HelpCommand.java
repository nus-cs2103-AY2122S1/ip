package commands;

import exceptions.MorganException;
import storage.Storage;
import tasks.TaskList;

public class HelpCommand extends Command {
    public static final String KEYWORD = "help";

    public String execute(TaskList tasks, Storage storage) throws MorganException {
        String output = "Here are some of the commands you can use:\n"
                + "1." + AddToDoCommand.INPUT_FORMAT + "\n"
                + "2." + AddDeadlineCommand.INPUT_FORMAT + "\n"
                + "3." + AddEventCommand.INPUT_FORMAT + "\n"
                + "4." + DeleteCommand.INPUT_FORMAT + "\n"
                + "5." + FindCommand.INPUT_FORMAT + "\n"
                + "6." + MarkDoneCommand.INPUT_FORMAT + "\n"
                + "7." + ListCommand.INPUT_FORMAT;
        return output;
    }
}