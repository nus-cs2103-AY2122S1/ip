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
 * The CommandDelete class handles the command "delete" that handles the command to
 * delete a specified task from the list.
 *
 * @author Loh Wen Hao Aaron
 *
 */
public class CommandDelete extends Command {
    public static final String KEYWORD = "delete";
    private static final String ARG_FORMAT = "\\d|\\d\\d|100";
    private ArrayList<String> arguments;


    public CommandDelete(ArrayList<String> arguments) {
        this.arguments = arguments;
    }

    /**
     * This method checks if the argument provided is a valid task number.
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
            int number = Integer.parseInt(arguments.get(0)) - 1;
            if (number + 1 <= tl.numberOfTasks() && number + 1 > 0) {
                tl.removeTask(number);
            } else {
                throw new DukeException("That task does not exist!");
            }
        } else {
            throw new DukeException("Invalid argument for command: delete");
        }
    }

}
