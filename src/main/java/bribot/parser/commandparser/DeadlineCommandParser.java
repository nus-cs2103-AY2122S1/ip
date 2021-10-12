package bribot.parser.commandparser;

import bribot.command.AddCommand;
import bribot.exception.DukeException;
import bribot.task.Deadline;

public class DeadlineCommandParser implements CommandParser<AddCommand> {
    private static final String DEADLINE_HELP_MESSAGE = "Please enter in the format"
            + "[deadline {description} /by dd/MM/yyyy HHmm]";
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
        Deadline task = new Deadline(description, dateString, timeString);
        return new AddCommand(task);
    }

    @Override
    public String argumentSetter() {
        if (userInputArr.length == 1) {
            return DEADLINE_HELP_MESSAGE;
        }
        String[] args = userInputArr[1].split(" /by ");
        description = args[0];
        if (args.length == 1 || args[1].split(" ").length == 1) {
            System.out.println(userInputArr.toString());
            System.out.println(args.length);
            return MISSING_DATETIME;
        }
        dateString = args[1].split(" ")[0];
        timeString = args[1].split(" ")[1];
        return null;
    }
}
