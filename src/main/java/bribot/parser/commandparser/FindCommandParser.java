package bribot.parser.commandparser;

import bribot.command.FindCommand;
import bribot.exception.DukeException;

public class FindCommandParser implements CommandParser<FindCommand> {
    private static final String HELP_MESSAGE = "Please use the format [find {description}] to find a task "
            + "that matches the description";

    private String[] userInputArr;
    private String description;

    @Override
    public FindCommand parse(String userInput) throws DukeException {
        this.userInputArr = userInput.split(" ", 2);
        String errorMessage = argumentSetter();
        if (errorMessage != null) {
            throw new DukeException(errorMessage);
        }
        return new FindCommand(description);
    }

    @Override
    public String argumentSetter() {
        if (userInputArr.length == 1) {
            return HELP_MESSAGE;
        }
        description = userInputArr[1];
        return null;
    }
}
