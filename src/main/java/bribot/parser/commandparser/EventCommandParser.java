package bribot.parser.commandparser;

import bribot.command.AddCommand;
import bribot.exception.DukeException;
import bribot.task.Event;

public class EventCommandParser implements CommandParser<AddCommand> {
    private static final String EVENT_HELP_MESSAGE = "Please enter in the format"
            + "[event {description} /at dd/MM/yyyy HHmm]";
    private static final String MISSING_DATETIME = "Please make sure that you provided a date and time "
            + "and is formatted as 'dd/MM/yyyy HHmm'.";

    private String[] userInputArr;
    private String description;
    private String dateString;
    private String timeString;

    @Override
    public AddCommand parse(String userInput) throws DukeException {
        userInputArr = userInput.split(" ", 2);
        String errorMessage = argumentSetter();
        if (errorMessage != null) {
            throw new DukeException(errorMessage);
        }
        Event task = new Event(description, dateString, timeString);
        return new AddCommand(task);
    }

    @Override
    public String argumentSetter() {
        if (userInputArr.length == 1) {
            return EVENT_HELP_MESSAGE;
        }
        String[] args = userInputArr[1].split(" /at ");
        description = args[0];
        if (args.length == 1 || args[1].split(" ").length == 1) {
            return MISSING_DATETIME;
        }
        dateString = args[1].split(" ")[0];
        timeString = args[1].split(" ")[1];
        return null;
    }
}
