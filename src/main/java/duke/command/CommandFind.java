package duke.command;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.ContactsList;
import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * The CommandFind class handles the command "find" that receives a specified
 * keyword and prints out all tasks whose description contains that keyword.
 *
 * @author Loh Wen Hao Aaron
 *
 */
public class CommandFind extends Command {
    public static final String KEYWORD = "find";
    private static final String ARG_FORMAT = "\\w+";
    private ArrayList<String> arguments;


    public CommandFind(ArrayList<String> arguments) {
        this.arguments = arguments;
    }

    /**
     * This method checks if the keyword specified in the arguments is in a valid format.
     *
     * @return A boolean indicating if the arguments are in a valid format.
     */
    @Override
    public boolean isValidArgument() {
        if (arguments.size() == 1) {
            Pattern pattern = Pattern.compile(ARG_FORMAT);
            Matcher matcher = pattern.matcher(arguments.get(0));
            return matcher.matches();
        } else {
            return false;
        }
    }

    @Override
    public void execute(TaskList tl, Storage st, Ui ui, ContactsList cl) {
        if (isValidArgument()) {
            tl.printAllTasksContaining(arguments.get(0));
        } else {
            throw new DukeException("Invalid argument for Command: find");
        }
    }

}
