package duke.command;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.exception.DukeException;
import duke.task.Event;
import duke.task.TaskList;

/**
 * Command to add an event into the task list.
 */
public class EventCommand extends Command {
    public EventCommand() {
        setMainCommand("event");
    }

    /**
     * Parses the user input for a name and a date.
     * Then creates the event and adds it into the taskList.
     *
     * @param input Full user input.
     * @param taskList The list of tasks.
     * @return The response telling the user that an event has been created.
     * @throws DukeException Any exception caught when executing this command.
     */
    @Override
    public String parse(String input, TaskList taskList) throws DukeException {
        assert taskList != null : "taskList should not be null";

        int firstSpace = input.indexOf(' ');

        // No space after the command
        if (firstSpace == -1) {
            throw new DukeException("Please input the event's name and date.");
        }

        String data = input.substring(firstSpace).strip();
        String[] inputs = data.split("/at");

        if (inputs.length < 2) {
            // /by not specified
            throw new DukeException("Please input when the event is at.");
        } else if (inputs.length > 2) {
            // more than one /by
            throw new DukeException("Please input only one timing for the event.");
        }

        String name = inputs[0].strip();
        String dateString = inputs[1].strip();

        if (name.equals("")) {
            throw new DukeException("Please input the event's name.");
        }

        LocalDate date;
        try {
            date = LocalDate.parse(dateString);
        } catch (DateTimeParseException e) {
            throw new DukeException("Please input your date in the format YYYY-MM-DD.");
        }

        Event event = new Event(name, date);
        return taskList.addTask(event);
    }
}
