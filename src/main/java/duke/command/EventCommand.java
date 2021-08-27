package duke.command;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.Duke;
import duke.exception.DukeException;
import duke.task.Event;

public class EventCommand extends Command {
    public EventCommand() {
        setCommandString("event");
    }

    /**
     * Parses the user input for a name and a date,
     * then creates the event and adds it into the taskList
     *
     * @param input Full user input
     * @throws DukeException Any exception caught when executing this command
     */
    @Override
    public void parse(String input) throws DukeException {
        if (input.length() <= getCommandLength()) {
            throw new DukeException("Please input the event's name and date!");
        }

        String[] inputs = input.substring(getCommandLength()).split("/at");

        if (inputs.length < 2) {
            // /by not specified
            throw new DukeException("Please input when the event is at!");
        } else if (inputs.length > 2) {
            // more than one /by
            throw new DukeException("Please input only one timing for the event!");
        }

        String name = inputs[0].strip();

        if (name.equals("")) {
            throw new DukeException("Please input the event's name!");
        }

        LocalDate date;
        try {
            date = LocalDate.parse(inputs[1].strip());
        } catch (DateTimeParseException e) {
            throw new DukeException("Please input your date in the format YYYY-MM-DD");
        }

        Event event = new Event(name, date);
        Duke.taskList.addTask(event);
    }
}
