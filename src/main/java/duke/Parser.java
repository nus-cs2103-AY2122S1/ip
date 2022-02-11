package duke;


import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.InvalidCommand;
import duke.command.ListCommand;
import duke.exception.DateNotFoundException;
import duke.exception.DescriptionNotFoundException;
import duke.exception.DukeException;
import duke.exception.QueryNotFoundException;
import duke.exception.TaskNotFoundException;
import duke.exception.TimeNotFoundException;

/**
 * Parser class that encapsulates handling of user input.
 */
public class Parser {

    /**
     * Handles user input from scanner.
     *
     * @param userInput The commands that user entered in console.
     * @return Command The action item to be executed by Duke.
     */
    public static Command parse(String userInput) throws DukeException {
        String command = getCommand(userInput);
        switch (command) {
        case "todo":
            return new AddCommand(
                    getDescription(userInput, "todo", "ignore"),
                    "todo");
        case "deadline":
            return new AddCommand(
                    getDescription(userInput, "deadline ", "/by "),
                    getDate(userInput, "/by "),
                    "deadline"
            );
        case "event":
            return new AddCommand(
                    getDescription(userInput, "event ", "/at "),
                    getDate(userInput, "/at "),
                    getTime(userInput),
                    "event"
            );
        case "find":
            return new FindCommand(getSearchQuery(userInput));
        case "done":
            return new DoneCommand(getTaskNumber(userInput));
        case "delete":
            return new DeleteCommand(getTaskNumber(userInput));
        case "list":
            return new ListCommand();
        case "bye":
            return new ExitCommand();
        default:
            return new InvalidCommand();
        }
    }

    private static String getCommand(String userInput) {
        return userInput.split(" ")[0];
    }

    private static String getDescription(String userInput, String splitText, String splitTime)
            throws DescriptionNotFoundException {
        try {
            String[] splitInput = userInput.split(splitText)[1].split(splitTime);
            return splitInput[0].trim();
        } catch (ArrayIndexOutOfBoundsException error) {
            throw new DescriptionNotFoundException();
        }
    }

    private static String getDate(String userInput, String splitTime) throws DateNotFoundException {
        try {
            String[] splitInput = userInput.split(splitTime);
            String date = splitInput[1];
            if (date.split(" ").length > 1) {
                return date.split(" ")[0];
            }
            return date;
        } catch (ArrayIndexOutOfBoundsException error) {
            throw new DateNotFoundException();
        }
    }

    private static String getTime(String userInput) throws TimeNotFoundException {
        try {
            String[] splitInput = userInput.split(" ");
            String time = splitInput[splitInput.length - 1];
            return time.substring(0, 2) + ":" + time.substring(2);
        } catch (ArrayIndexOutOfBoundsException error) {
            throw new TimeNotFoundException();
        }
    }

    private static int getTaskNumber(String userInput) throws TaskNotFoundException {
        try {
            return Integer.parseInt(userInput.split(" ")[1]);
        } catch (ArrayIndexOutOfBoundsException error) {
            throw new TaskNotFoundException();
        }
    }

    private static String getSearchQuery(String userInput) throws QueryNotFoundException {
        String[] searchArr = userInput.split("find ");
        if (searchArr.length <= 1) {
            throw new QueryNotFoundException();
        }
        return searchArr[1];
    }

}
