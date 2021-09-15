package duke.parser;

import java.util.ArrayList;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.ExpenseCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.TodoCommand;
import duke.exception.DukeException;
import duke.exception.EmptyCommandException;
import duke.exception.IncorrectFormatException;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidDurationException;
import duke.exception.InvalidExpenseFormatException;
import duke.exception.MessageEmptyException;
import duke.exception.TaskNotFoundException;

/**
 * Handles and interprets user commands for Duke.
 */
public class Parser {

    /** Represents an empty command word to call. */
    private static final String EMPTY_COMMAND = "";

    /**
     * Parses a string task list index to an integer.
     *
     * @param words Entire user input.
     * @return Index as an integer.
     * @throws NumberFormatException If the string cannot be converted into an integer.
     */
    private int parseIndex(String[] words) throws NumberFormatException, MessageEmptyException,
            TaskNotFoundException {
        try {
            String index = words[1];
            return Integer.parseInt(index) - 1;
        } catch (NumberFormatException e) {
            throw new TaskNotFoundException();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MessageEmptyException();
        }
    }

    /**
     * Parses relevant information from deadline.
     *
     * @param deadline user inputted deadline.
     * @return ArrayList of relevant information extracted from user input.
     * @throws MessageEmptyException if the user does not include the "/by" keyword.
     * @throws IncorrectFormatException if the user input contains an incorrect duration format.
     */
    private ArrayList<String> parseAddDeadline(String deadline) throws MessageEmptyException,
            IncorrectFormatException {

        ArrayList<String> parsedDeadline = new ArrayList<>();

        try {
            deadline = deadline.substring(9).trim();
        } catch (StringIndexOutOfBoundsException e) {
            throw new MessageEmptyException();
        }

        String[] result = deadline.split("/by");

        if (result.length == 0) {
            throw new MessageEmptyException();
        } else if (result.length == 1) {
            // throws an error if "/by" is not present in the message
            throw new IncorrectFormatException("deadline", "/by");
        } else if (result[0].trim().equals("")) {
            throw new MessageEmptyException();
        }

        String description = result[0].trim();
        String by = result[1].trim();

        parsedDeadline.add(description);
        parsedDeadline.add(by);
        return parsedDeadline;
    }

    /**
     * Parses relevant information from event.
     *
     * @param event user inputted event.
     * @return ArrayList of relevant information extracted from user input.
     * @throws MessageEmptyException if the user input is lacking a message.
     * @throws IncorrectFormatException if the user input does not include the "/at" keyword.
     * @throws InvalidDurationException if the user input contains an incorrect duration format.
     */
    private ArrayList<String> parseAddEvent(String event) throws MessageEmptyException, IncorrectFormatException,
            InvalidDurationException {

        ArrayList<String> parsedEvent = new ArrayList<>();

        try {
            event = event.substring(6).trim();
        } catch (StringIndexOutOfBoundsException e) {
            throw new MessageEmptyException();
        }

        String[] result = event.split("/at");

        if (result.length == 0) {
            throw new MessageEmptyException();
        } else if (result.length == 1) {
            // throws an error if "/at" is not present in the message
            throw new IncorrectFormatException("event", "/at");
        } else if (result[0].trim().equals("")) {
            throw new MessageEmptyException();
        }

        String description = result[0].trim();
        String at = result[1].trim();

        // throws error if it doesn't even contain sufficient number of characters for correct format
        if (at.replaceAll("\\s", "").length() < 19) { // YYYY/MM/DD HHMM - HHMM
            throw new InvalidDurationException();
        }

        String date = at.substring(0, 10).trim(); // at this point, date contains 10 chars YYYY/MM/DD
        String eventDuration = at.substring(11).trim();
        String[] eventTimes = eventDuration.split("-");

        // if no "-" present
        if (eventTimes.length != 2) {
            throw new InvalidDurationException();
        }

        parsedEvent.add(description);
        parsedEvent.add(date);
        parsedEvent.add(eventTimes[0]);
        parsedEvent.add(eventTimes[1]);
        return parsedEvent;
    }

    /**
     * Parses the user input for the todo command.
     *
     * @param todo entire user input.
     * @return the actual task to be added as a todo.
     * @throws MessageEmptyException if no message follows the todo command.
     */
    private String parseTodo(String todo) throws MessageEmptyException {
        try {
            return todo.substring(5).trim();
        } catch (StringIndexOutOfBoundsException e) {
            throw new MessageEmptyException();
        }
    }

    /**
     * Chooses and return the appropriate expense command from the user input.
     *
     * @param expense the user input.
     * @return appropriate expense command given the user input.
     * @throws MessageEmptyException if the message is missing from the user input.
     * @throws InvalidExpenseFormatException if the format of entering an expense command is invalid.
     */
    private Command chooseCorrectExpenseCommand(String expense) throws MessageEmptyException,
            InvalidExpenseFormatException {

        try {
            expense = expense.trim().substring(8).trim();
        } catch (StringIndexOutOfBoundsException e) {
            throw new MessageEmptyException();
        }
        boolean isAllPresent = expense.matches("^(?i)(/listall)");
        boolean isSumAll = expense.matches("^(?i)(/sumall)");

        if (isAllPresent) {
            return new ExpenseCommand(-1, "", 0, true, false);
        }
        if (isSumAll) {
            return new ExpenseCommand(true);
        }

        String[] parsedExpense = expense.split(" ");
        String indexAsString = parsedExpense[0];

        boolean isDelete = expense.matches("([0-9]+)\\s+(?i)/delete\\s+([0-9]+)");
        boolean isDisplay = expense.matches("([0-9]+)");
        boolean isAdd = expense.matches("([0-9]+)([\\s+(\\w+)\\s+]+)(\\$)([0-9]+)");
        boolean isSum = expense.matches("([0-9]+)\\s(?i)/sum$");

        try {
            int endingIndexOfNumber = expense.indexOf(indexAsString) + indexAsString.length();
            int index = Integer.parseInt(indexAsString) - 1;

            if (isAdd) {
                ArrayList<String> parsedInfo = separatePurposeAndAmount(expense.substring(endingIndexOfNumber));
                String purpose = parsedInfo.get(0);
                float amount = Float.parseFloat(parsedInfo.get(1));
                return new ExpenseCommand(index, purpose, amount);
            } else if (isDisplay) {
                return new ExpenseCommand(index, "", 0, false, true);
            } else if (isDelete) {
                int deleteIndex = extractExpenseDelete(expense.substring(endingIndexOfNumber));
                return new ExpenseCommand(index, deleteIndex);
            } else if (isSum) {
                return new ExpenseCommand(index, true);
            } else {
                throw new InvalidExpenseFormatException();
            }
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new InvalidExpenseFormatException();
        }
    }

    /**
     * Extracts the index of expense to be deleted.
     *
     * @param input the user input without the expense command.
     * @return the index of expense to be deleted.
     * @throws InvalidExpenseFormatException if the format of expense command is wrong.
     */
    private int extractExpenseDelete(String input) throws InvalidExpenseFormatException {
        input = input.trim();
        String[] parsedInfo = input.split("/delete");
        if (parsedInfo.length > 2) {
            throw new InvalidExpenseFormatException();
        }
        try {
            return Integer.parseInt(parsedInfo[1].trim()) - 1;
        } catch (NumberFormatException e) {
            throw new InvalidExpenseFormatException();
        }
    }

    /**
     * Separates the purpose and amount field from the user input for expense.
     *
     * @param input user input without expense keyword.
     * @return an arraylist with the purpose and amount spent separated.
     * @throws InvalidExpenseFormatException if the format of entering an expense command is invalid.
     */
    private ArrayList<String> separatePurposeAndAmount(String input) throws InvalidExpenseFormatException {
        input = input.trim();
        String[] parsedInfo = input.split("\\$");
        ArrayList<String> purpose = new ArrayList<>();
        for (int i = 0; i < parsedInfo.length - 1; i++) {
            purpose.add(parsedInfo[i].trim());
        }
        ArrayList<String> result = new ArrayList<>();
        result.add(String.join(" ", purpose));
        result.add(parsedInfo[parsedInfo.length - 1].trim());
        return result;
    }


    /**
     * Logic for handling different commands and executing the appropriate methods for the inputted command.
     * Throws appropriate exceptions for its respective error.
     *
     * @param input user input.
     * @return respective Command according to command word in user input.
     * @throws DukeException if any violations of commands in user input occurs.
     */
    public Command handleCommands(String input) throws DukeException {

        String[] words = parseInput(input);
        String command = getCommand(words);

        switch (command) {
        case ByeCommand.COMMAND: // only applicable to GUI Duke
            return new ByeCommand();
        case DeadlineCommand.COMMAND:
            return new DeadlineCommand(parseAddDeadline(input));
        case DeleteCommand.COMMAND:
            return new DeleteCommand(parseIndex(words));
        case DoneCommand.COMMAND:
            return new DoneCommand(parseIndex(words));
        case EventCommand.COMMAND:
            return new EventCommand(parseAddEvent(input));
        case ExpenseCommand.COMMAND:
            return chooseCorrectExpenseCommand(input);
        case FindCommand.COMMAND:
            return new FindCommand(words);
        case ListCommand.COMMAND:
            return new ListCommand();
        case TodoCommand.COMMAND:
            return new TodoCommand(parseTodo(input));
        case EMPTY_COMMAND:
            throw new EmptyCommandException();
        default: // all other inputs that are not supported
            throw new InvalidCommandException();
        }
    }

    /**
     * Trims the user input and splits it by white spaces.
     *
     * @param input entire user input.
     * @return an array of Strings of the user input split by white spaces.
     */
    private String[] parseInput(String input) {
        String trimmedInput = input.trim();
        return trimmedInput.split(" ");
    }

    /**
     * Isolates the command word.
     *
     * @param parsedInput parsed user input.
     * @return command word for the relevant task.
     */
    private String getCommand(String[] parsedInput) {
        return parsedInput[0].trim().toLowerCase();
    }
}
