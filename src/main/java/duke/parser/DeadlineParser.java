package duke.parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Map;

import duke.command.AddCommand;
import duke.exception.DukeException;
import duke.task.Deadline;

public class DeadlineParser extends CommandParser<AddCommand> {

    public DeadlineParser() {
        super("deadline", "/desc", "/date");
    }

    @Override
    protected AddCommand convertToCommand(Map<String, String> argumentMap) throws DukeException {
        try {
            if (argumentMap.containsKey("/desc") && argumentMap.containsKey("/date")) {
                return new AddCommand(
                        new Deadline(argumentMap.get("/desc"), LocalDate.parse(argumentMap.get("/date")), false)
                );
            } else {
                throw new DukeException("Sorry Boss, deadline must have /desc and /date arguments");
            }
        } catch (DateTimeParseException e) {
            throw new DukeException("Sorry boss, please ensure that /date is in YYYY-MM-DD format");
        }
    }
}
