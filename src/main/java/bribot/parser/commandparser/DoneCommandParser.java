package bribot.parser.commandparser;

import bribot.command.DoneCommand;
import bribot.exception.DukeException;

public class DoneCommandParser implements CommandParser<DoneCommand> {
    private static final String HELP_MESSAGE = "Please use the format [done {task number}] to toggle the task "
            + "between done and undone.";
    private static final String NO_NUMBER_PROVIDED = "Please provide the number of the task to be marked done.";

    private String[] userInputArr;
    private int index;

    @Override
    public DoneCommand parse(String userInput) throws DukeException {
        userInputArr = userInput.split(" ", 2);
        String errorMessage = argumentSetter();
        if (errorMessage != null) {
            throw new DukeException(errorMessage);
        }
        return new DoneCommand(index);
    }

    @Override
    public String argumentSetter() {
        if (userInputArr.length == 1) {
            return HELP_MESSAGE;
        }
        String indexString = userInputArr[1];
        try {
            index = Integer.parseInt(indexString) - 1;
        } catch (NumberFormatException e) {
            return NO_NUMBER_PROVIDED;
        }
        return null;
    }
}
