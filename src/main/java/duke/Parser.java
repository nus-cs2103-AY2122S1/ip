package duke;

import javafx.application.Platform;

/**
 * A class to analyze user input and return the relevant Commands.
 */
public class Parser {

    /**
     * Parses the user input and returns the corresponding Command.
     *
     * @param userInput the input the user keys in
     * @return a Command object
     */
    public static Command parse(String userInput) {
        if (userInput.equals("bye")) {
            Platform.exit();
            return new ExitCommand();
        } else if (userInput.equals("list")) {
            return new ListCommand();
        } else if (userInput.startsWith("done")) {
            return new DoneCommand(userInput);
        } else if (userInput.startsWith("delete")) {
            return new DeleteCommand(userInput);
        } else if (userInput.startsWith("find")) {
            return new FindCommand(userInput);
        } else if (userInput.startsWith("after")) {
            return new AfterCommand(userInput);
        } else if (userInput.startsWith("do")) {
            return new DoAfterCommand(userInput);
        } else {
            return new AddCommand(userInput);
        }
    }
}
