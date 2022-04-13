package duke;

/**
 * A class that deals with making sense of user inputs.
 */
public class Parser {
    /**
     * Parses a user's inputs into commands that can be executed by Duke.
     *
     * @param fullCommand The string input by the user.
     * @return A command that can be executed by Duke.
     * @throws DukeException
     */
    public static Command parse(String fullCommand) throws DukeException {
        // Separate user input into words
        String firstWord;
        String remainingWords = "";
        if (fullCommand.contains(" ")) {
            String[] splitFullCommand = fullCommand.split(" ", 2);
            assert splitFullCommand.length == 2;
            firstWord = splitFullCommand[0];
            remainingWords = splitFullCommand[1];
        } else {
            firstWord = fullCommand;
        }

        switch (firstWord) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "done":
            return new DoneCommand(Integer.parseInt(remainingWords));
        case "todo":
            return new AddCommand("todo", remainingWords);
        case "deadline":
            return new AddCommand("deadline", remainingWords);
        case "event":
            return new AddCommand("event", remainingWords);
        case "delete":
            return new DeleteCommand(Integer.parseInt(remainingWords));
        case "find":
            return new FindCommand(remainingWords);
        case "tag":
            return new TagCommand(remainingWords);
        default:
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
