package bribot.parser.commandparser;

import bribot.command.AddCommand;
import bribot.exception.DukeException;
import bribot.task.Todo;

public class TodoCommandParser implements CommandParser<AddCommand> {

    private static final String HELP_MESSAGE = "Please use the format [todo {description}] to create "
            + "a todo.";


    private String[] userInputArr;
    private String description;

    @Override
    public AddCommand parse(String userInput) throws DukeException {
        this.userInputArr = userInput.split(" ", 2);
        String errorMessage = argumentSetter();

        if (errorMessage != null) {
            throw new DukeException(errorMessage);
        }
        Todo task = new Todo(description);
        return new AddCommand(task);
    }

    /**
     * Returns error message in a string if input is invalid else sets arguments and returns null
     */
    @Override
    public String argumentSetter() {
        if (userInputArr.length == 1) {
            return HELP_MESSAGE;
        }
        description = userInputArr[1];
        return null;
    }
}
