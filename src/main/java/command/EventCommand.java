package main.java.command;

import java.time.format.DateTimeParseException;
import java.time.LocalDateTime;
import main.java.bot.DukeException;
import main.java.bot.Storage;
import main.java.bot.TaskList;
import main.java.bot.UserInterface;
import main.java.task.EventTask;

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
     * Executes the event command with the given input.
     *
     * @param list The list of tasks to be modified by the command.
     * @param ui The UI of Duke to be invoked by the command.
     * @throws DukeException if the input given is not of the correct format.
     */
    public void execute(TaskList list, UserInterface ui) throws DukeException {

        int position = input.indexOf("/at");
        String newTask = input.substring(6, position);
        String newTime = input.substring(position + 4);
        if (newTask.length() == 0) {
            throw new DukeException("The description of an event cannot be empty. Please try again!");
        } else if (newTime.length() == 0) {
            throw new DukeException("The date of an event cannot be empty. Please try again!");
        } else {
            try {
                LocalDateTime time = LocalDateTime.parse(newTime.trim(), inputFormatter);
                list.addTask(new EventTask(newTask, time));
                Storage.save(list);
                UserInterface.showTaskAdded(newTask, 3, list.getSize() - 1, newTime.trim());
            } catch (DateTimeParseException e) {
                throw new DukeException(
                        "Your time format is wrong. Please enter the time in the format DD/MM/YYYY HHMM and try again!");
            }
        }
    }
}