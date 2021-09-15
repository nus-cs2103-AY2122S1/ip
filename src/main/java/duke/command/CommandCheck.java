package duke.command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import duke.ContactsList;
import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * The CommandCheck class handles the command "check" that checks the task list for all
 * tasks that occur on a specified date.
 *
 * @author Loh Wen Hao Aaron
 *
 */
public class CommandCheck extends Command {
    public static final String KEYWORD = "check";
    private ArrayList<String> arguments;


    public CommandCheck(ArrayList<String> arguments) {
        this.arguments = arguments;
    }

    /**
     * This method checks if the date specified in the arguments is in a valid format.
     *
     * @return A boolean indicating if the arguments are in a valid format.
     */
    @Override
    public boolean isValidArgument() {
        try {
            if (arguments.size() == 1) {
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate date = LocalDate.parse(arguments.get(0), dateFormatter);
                return true;
            } else {
                return false;
            }
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    @Override
    public void execute(TaskList tl, Storage st, Ui ui, ContactsList cl) {
        if (isValidArgument()) {
            tl.printAllTasksOnDate(arguments.get(0));
        } else {
            throw new DukeException("Invalid argument for Command: check");
        }
    }

}
