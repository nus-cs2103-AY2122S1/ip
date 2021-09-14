package duke.parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Map;

import duke.command.UpdateCommand;
import duke.exception.DukeException;

public class UpdateParser extends CommandParser<UpdateCommand> {

    public UpdateParser() {
        super("update", "/sn", "/desc", "/date");
    }

    @Override
    protected UpdateCommand convertToCommand(Map<String, String> argumentMap) throws DukeException {
        int serialNo;
        String desc = null;
        LocalDate date = null;
        try {
            if (!argumentMap.containsKey("/sn")) {
                throw new DukeException("Sorry boss, update must have /sn argument");
            } else if (!argumentMap.containsKey("/desc") && !argumentMap.containsKey("/date")) {
                throw new DukeException("Sorry boss, update must have either /desc or /date argument");
            } else {
                serialNo = Integer.parseInt(argumentMap.get("/sn"));
                if (argumentMap.containsKey("/desc")) {
                    desc = argumentMap.get("/desc");
                }
                if (argumentMap.containsKey("/date")) {
                    date = LocalDate.parse(argumentMap.get("/date"));
                }
                return new UpdateCommand(serialNo, desc, date);
            }
        } catch (DateTimeParseException e) {
            throw new DukeException("Sorry Boss, please ensure /date is in YYYY-MM-DD format");
        } catch (NumberFormatException e) {
            throw new DukeException("Sorry Boss, please pass a valid number for /sn argument");
        }
    }
}
