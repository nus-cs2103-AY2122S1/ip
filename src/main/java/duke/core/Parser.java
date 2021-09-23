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

import java.util.Arrays;

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
            final String REGEX = " ";
            String[] splittedInput = input.split(REGEX);
            String commandType = splittedInput[0];
            switch (commandType) {
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
        final String REGEX = " ";
        String[] splittedInput = input.split(REGEX);
        String commandType = splittedInput[0];
        if (input.length() == 0) {
            throw new DukeException(":( OOPS!!! The input cannot be empty.");
        }

        switch (commandType) {
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
            checkValidityDoneOrDelete(input, taskList, "done");
            break;
        case "delete":
            checkValidityDoneOrDelete(input, taskList, "delete");
            break;
        case "find":
            checkValidityFind(input);
            break;
        default:
            // No action needed due to the default case in parse()
        }
        return true;
    }

    private static int numOfComponentsExclCommand(String input, String regex) {
        String[] splittedInput = input.split(" ", 2);
        // This means input contains only one word, the command
        if (splittedInput.length < 2) {
            return 0;
        }
        String inputWithoutCommand = splittedInput[1];
        long numOfComponents
                = Arrays.stream(inputWithoutCommand.strip().split(regex)).filter(e -> e.length() > 0).count();
        return (int) numOfComponents;
    }

    private static void checkValidityTodo(String input) throws DukeException {
        final int NUM_OF_ARGUMENTS = 1;
        final String REGEX = " ";
        if (numOfComponentsExclCommand(input, REGEX) < 1) {
            throw new DukeException(":( OOPS!!! Description of todo cannot be empty!");
        }
    }

    private static void checkValidityDeadline(String input) throws DukeException {
        final String REGEX_FOR_DATE = "/by";
        if (numOfComponentsExclCommand(input, REGEX_FOR_DATE) < 2) {
            throw new DukeException(":( OOPS!!! A deadline task requires both name and date!");
        }
    }

    private static void checkValidityEvent(String input) throws DukeException {
        final String REGEX_FOR_DATE = "/at";
        if (numOfComponentsExclCommand(input, REGEX_FOR_DATE) < 2) {
            throw new DukeException(":( OOPS!!! An event task requires both name and date!");
        }
    }

    private static void checkValidityFind(String input) throws DukeException {
        final int NUM_OF_ARGUMENTS = 1;
        final String REGEX = " ";
        int numOfComponents = numOfComponentsExclCommand(input, REGEX);
        if (numOfComponents < 1) {
            throw new DukeException(":( OOPS!!! Please specify what you want to find.");
        } else if (numOfComponents > 1) {
            throw new DukeException(":( OOPS!!! Duke only supports finding by a single keyword.");
        }
    }

    private static void checkValidityDoneOrDelete(String input, TaskList taskList, String commandType)
            throws DukeException {
        final int NUM_OF_ARGUMENTS = 1;

        final String REGEX = " ";
        String[] splittedInput = input.split(REGEX);

        String indexArgument = splittedInput[1];
        checkIndexArgument(indexArgument, taskList);
    }

    private static void checkIndexArgument(String indexArgument, TaskList taskList) throws DukeException {
        if (HelpfulFunctions.isInteger(indexArgument)) {
            int index = Integer.parseInt(indexArgument);
            if (index < 1 || index > taskList.getSize()) {
                throw new DukeException(":( OOPS!!! Your index is out of range");
            }
        } else {
            throw new DukeException(":( OOPS!!! Your second argument must be an integer");
        }
    }

    private static void checkPresenceOfDate(String input, String regexForDate) throws DukeException {
        boolean containsRegex = false;
        int regexIndex = -1;
        String[] splittedInput = input.split(" ");
        for (int i = 0; i < splittedInput.length; i++) {
            if (splittedInput[i].equals(regexForDate)) {
                containsRegex = true;
                regexIndex = i;
                break;
            }
        }

        // If the second condition is met, it means that no date is written after the regexIndex
        if (!containsRegex || regexIndex == splittedInput.length - 1) {
            throw new DukeException(":( OOPS!!! Please specify a date");
        }
    }

    private static Command validTodoHandler(String input) {
        final String REGEX = " ";
        String[] splittedInput = input.split(REGEX, 2);
        return new TodoCommand(splittedInput[1]);
    }

    private static Command validDeadlineHandler(String input) {
        final String REGEX_FOR_INPUT = " ";
        String details = input.split(REGEX_FOR_INPUT, 2)[1];

        final String REGEX_TO_SPLIT_DATE = "/by";
        String splittedByDate[] = details.split(REGEX_TO_SPLIT_DATE);

        return new DeadlineCommand(splittedByDate[0].trim(), splittedByDate[1].trim());
    }

    private static Command validEventHandler(String input) {
        final String REGEX_FOR_INPUT = " ";
        String details = input.split(REGEX_FOR_INPUT, 2)[1];

        final String REGEX_TO_SPLIT_DATE = "/at";
        String splittedByDate[] = details.split(REGEX_TO_SPLIT_DATE);

        return new EventCommand(splittedByDate[0].trim(), splittedByDate[1].trim());
    }

    private static String trimDate(String input, String regex) {
        return input.split(regex)[0].trim();
    }

}
