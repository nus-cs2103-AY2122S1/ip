package duke.parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Map;

import duke.command.AddCommand;
import duke.exception.DukeException;
import duke.task.Event;

public class EventParser extends CommandParser<AddCommand> {

    public EventParser() {
        super("event", "/desc", "/date");
    }

    @Override
    protected AddCommand convertToCommand(Map<String, String> argumentMap) throws DukeException {
        try {
            if (argumentMap.containsKey("/desc") && argumentMap.containsKey("/date")) {
                return new AddCommand(
                        new Event(argumentMap.get("/desc"), LocalDate.parse(argumentMap.get("/date")), false)
                );
            } else {
                throw new DukeException("Sorry Boss, event must have /desc and /date arguments");
            }
        } catch (DateTimeParseException e) {
            throw new DukeException("Sorry boss, please ensure /date is in YYYY-MM-DD format");
        }
    }
}
