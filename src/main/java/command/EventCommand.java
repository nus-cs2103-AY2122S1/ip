package command;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import bot.DukeException;
import bot.Storage;
import bot.TaskList;
import bot.UserInterface;
import task.DeadlineTask;
import task.EventTask;

/**
 * A class that encapsulates an Event Command given to Duke.
 */
public class EventCommand extends Command {

    /**
     * Constructor for the EventCommand class.
     *
     * @param input The input given by the user.
     */
    public EventCommand(String input) {
        super(input);
    }

    /**
     * Returns the proper response according to the given input.
     *
     * @param list The list of tasks to be modified by the command.
     * @param ui The UI of Duke to be invoked by the command.
     * @return A String representation of Duke's response according to the input given by the user.
     * @throws DukeException if the input given is not of the correct format.
     */
    @Override
    public String execute(TaskList list, UserInterface ui) throws DukeException {
        if (input.trim().length() == 5) {
            throw new DukeException("The description of an event cannot be empty. Please try again!");
        } else if (!input.contains(" /at ")) {
            throw new DukeException(
                    "The date of an event must be given in the format \"/at DD/MM/YYYY HHMM\". Please try again!");
        }

        int position = input.indexOf(" /at");
        String newEvent = input.substring(6, position);

        if (input.substring(position).length() <= 5) {
            throw new DukeException("The date of an event cannot be empty. Please try again!");
        }

        try {
            String newTime = input.substring(position + 5);
            LocalDateTime time = LocalDateTime.parse(newTime.trim(), INPUT_FORMATTER);
            EventTask newTask;
            if (!tags.isEmpty()) {
                newTask = new EventTask(newEvent, time, tags);
            } else {
                newTask = new EventTask(newEvent, time);
            }
            list.addTask(newTask);
            Storage.save(list);
            return ui.showTaskAdded(newTask, list.getSize() - 1);
        } catch (DateTimeParseException e) {
            throw new DukeException(
                    "Your time format is wrong.\n"
                    + "Please enter the time in the format \"/at DD/MM/YYYY HHMM\" and try again!");
        }
    }
}
