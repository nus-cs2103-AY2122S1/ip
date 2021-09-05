package duke.command;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import duke.ContactsList;
import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Deadline;

/**
 * The CommandDeadline class handles the command "deadline" that adds a new Deadline task
 * to the task list.
 *
 * @author Loh Wen Hao Aaron
 *
 */
public class CommandDeadline extends Command {
    public static final String KEYWORD = "deadline";
    private ArrayList<String> arguments;


    public CommandDeadline(ArrayList<String> arguments) {
        this.arguments = arguments;
    }

    /**
     * This method checks if the description, date (and time if provided) specified in the arguments
     * is in a valid format.
     *
     * @return A boolean indicating if the arguments are in a valid format.
     */
    @Override
    public boolean isValidArgument() {
        try {

            if (arguments.size() >= 3 && arguments.get(1).contains("/by")) {
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate date = LocalDate.parse(arguments.get(2), dateFormatter);
                if (arguments.size() >= 4) {
                    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("k:mm");
                    LocalTime time = LocalTime.parse(arguments.get(3), timeFormatter);
                }
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
            Deadline newDeadline;
            if (arguments.size() >= 4) {
                newDeadline = new Deadline(arguments.get(0), arguments.get(2), arguments.get(3));
            } else {
                newDeadline = new Deadline(arguments.get(0), arguments.get(2), "");
            }
            tl.addTask(newDeadline);
        } else {
            throw new DukeException("Invalid argument for Command: deadline");
        }
    }


}
