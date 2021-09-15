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
import duke.task.Event;

/**
 * The CommandEvent class handles the command "event" that adds a new Event task
 * to the task list.
 *
 * @author Loh Wen Hao Aaron
 *
 */
public class CommandEvent extends Command {
    public static final String KEYWORD = "event";
    private ArrayList<String> arguments;


    public CommandEvent(ArrayList<String> arguments) {
        this.arguments = arguments;
    }

    /**
     * This method checks if the description, date and time specified in the arguments
     * is in a valid format.
     *
     * @return A boolean indicating if the arguments are in a valid format.
     */
    @Override
    public boolean isValidArgument() {
        try {

            if (arguments.size() >= 4 && arguments.get(1).contains("/at")) {
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate date = LocalDate.parse(arguments.get(2), dateFormatter);

                String[] segments = arguments.get(3).split("-");
                if (segments.length < 2) {
                    return false;
                }
                String timeStart = segments[0];
                String timeEnd = segments[1];
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("k:mm");
                LocalTime timeS = LocalTime.parse(timeStart, timeFormatter);
                LocalTime timeE = LocalTime.parse(timeEnd, timeFormatter);
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
            Event newEvent;
            newEvent = new Event(arguments.get(0), arguments.get(2), arguments.get(3));
            tl.addTask(newEvent);
        } else {
            throw new DukeException("Invalid argument for Command: event");
        }
    }

}
