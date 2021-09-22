package duke.core;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.ExceptionalCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.TodoCommand;
import duke.helpful_functions.HelpfulFunctions;

/**
 * Parser is a class that provides useful method(s) to make sense of and translate user input into Command.
 */
public class Parser {
    /**
     * parse is a static method that makes sense of and translates user input into a Command.
     *
     * @param input The user's input in String format.
     * @param taskList The TaskList object storing all the tasks.
     * @return A Command object that will be executed by Duke to carry out the instructions of the user.
     */
    public static Command parse(String input, TaskList taskList) {
        try {
            // Check if the input is in a valid format, and throw a DukeException if it is not
            checkValiditiy(input, taskList);

            // Return a Command based on the input type (determined by first word of input)
            String[] splittedInput = input.split(" ");
            switch (splittedInput[0]) {
            case "list":
                return new ListCommand();
            case "done":
                return new DoneCommand(Integer.valueOf(splittedInput[1]));
            case "delete":
                return new DeleteCommand(Integer.valueOf(splittedInput[1]));
            case "find":
                return new FindCommand(splittedInput[1]);
            case "bye":
                return new ByeCommand();
            case "todo":
                return validTodoHandler(input);
            case "deadline":
                return validDeadlineHandler(input);
            case "event":
                return validEventHandler(input);
            default:
                // This is for any other erroneous input we did not catch from checkValidity
                DukeException e = new DukeException(":( OOPS!!! I'm sorry, but I don't know what that means");
                return new ExceptionalCommand(e);
            }
        } catch (DukeException e) {
            return new ExceptionalCommand(e);
        }

    }

    private static boolean checkValiditiy(String input, TaskList taskList) throws DukeException {
        String[] splittedInput = input.split(" ");
        if (input.length() == 0) {
            throw new DukeException(":( OOPS!!! The input cannot be empty.");
        }

        switch (splittedInput[0]) {
        case "todo":
            checkValidityTodo(input);
            break;
        case "deadline":
            checkValidityDeadline(input);
            break;
        case "event":
            checkValidityEvent(input);
            break;
        case "done":
            checkValidityDone(input, taskList);
            break;
        case "delete":
            checkValidityDelete(input, taskList);
            break;
        }
        return true;
    }

    private static void checkValidityTodo(String input) throws DukeException {
        String[] splittedInput = input.split(" ");
        if (splittedInput.length == 1) {
            throw new DukeException(":( OOPS!!! The description of a todo cannot be empty.");
        }
    }

    private static void checkValidityDeadline(String input) throws DukeException {
        String[] splittedInput = input.split(" ");
        if (splittedInput.length == 1) {
            throw new DukeException(":( OOPS!!! The description of a deadline cannot be empty.");
        }
    }

    private static void checkValidityEvent(String input) throws DukeException {
        String[] splittedInput = input.split(" ");
        if (splittedInput.length == 1) {
            throw new DukeException(":( OOPS!!! The description of an event cannot be empty.");
        }
    }

    private static void checkValidityDone(String input, TaskList taskList) throws DukeException {
        String[] splittedInput = input.split(" ");
        if (splittedInput.length == 1) {
            throw new DukeException(":( OOPS!!! You must specify an index");
        } else {
            if (HelpfulFunctions.isInteger(splittedInput[1])) {
                int index = Integer.parseInt(splittedInput[1]);
                if (index < 1 || index > taskList.getSize()) {
                    throw new DukeException(":( OOPS!!! Your index is out of range");
                }
            } else {
                throw new DukeException(":( OOPS!!! Your second argument must be an integer");
            }
        }
    }

    private static void checkValidityDelete(String input, TaskList taskList) throws DukeException {
        String[] splittedInput = input.split(" ");
        if (splittedInput.length == 1) {
            throw new DukeException(":( OOPS!!! You must specify an index");
        } else {
            if (HelpfulFunctions.isInteger(splittedInput[1])) {
                int index = Integer.parseInt(splittedInput[1]);
                if (index < 1 || index > taskList.getSize()) {
                    throw new DukeException(":( OOPS!!! Your index is out of range");
                }
            } else {
                throw new DukeException(":( OOPS!!! Your second argument must be an integer");
            }
        }
    }

    private static Command validTodoHandler(String input) {
        String[] splittedInput = input.split(" ", 2);
        return new TodoCommand(splittedInput[1]);
    }

    private static Command validDeadlineHandler(String input) {
        String details = input.split(" ", 2)[1];
        String regexForDetails = "/by";
        String splittedDetails[] = details.split(regexForDetails);
        return new DeadlineCommand(splittedDetails[0].trim(), splittedDetails[1].trim());
    }

    private static Command validEventHandler(String input) {
        String details = input.split(" ", 2)[1];
        String regexForDetails = "/at";
        String splittedDetails[] = details.split(regexForDetails);
        return new EventCommand(splittedDetails[0].trim(), splittedDetails[1].trim());
    }

}
