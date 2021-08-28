package duke.parser;

import java.util.List;

import duke.command.AddTaskCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.data.DateAndTime;
import duke.data.exceptions.DukeException;
import duke.data.exceptions.EmptyTaskDescriptionException;
import duke.data.exceptions.InvalidDateAndTimeException;
import duke.data.exceptions.InvalidInputException;

/**
 * Represents a parser that makes sense of what the user input is.
 */
public class Parser {
    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";
    private static final String EXIT = "bye";
    private static final String LIST = "list";
    private static final String DONE = "done";
    private static final String DELETE = "delete";

    public Parser() {}

    /**
     * Returns a Command corresponding to the user input.
     *
     * @param command user input
     * @return a Command object
     * @throws DukeException
     */
    public Command parse(String command) throws DukeException {
        String commandType = checkCommandType(command);

        //hardcode cases change?
        switch (commandType) {
        case TODO:
            String todoName = getTaskName(command, commandType);
            return new AddTaskCommand(TODO, todoName);
        case DEADLINE:
            String deadlineName = getTaskName(command, commandType);
            String deadline = getDateAndTime(command, DEADLINE);
            return new AddTaskCommand(DEADLINE, deadlineName, deadline);
        case EVENT:
            String eventName = getTaskName(command, commandType);
            String eventTime = getDateAndTime(command, EVENT);
            return new AddTaskCommand(EVENT, eventName, eventTime);
        case EXIT:
            if (!command.equals(EXIT)) {
                throw new InvalidInputException("invalid input");
            }
            Command exitCommand = new ExitCommand();
            exitCommand.setExit();
            return exitCommand;
        case LIST:
            if (!command.equals(LIST)) {
                throw new InvalidInputException("invalid input");
            }
            return new ListCommand();
        case DONE:
            if (command.equals(DONE)) {
                throw new InvalidInputException("invalid task number entered");
            } else {
                if (!Character.isWhitespace(command.charAt(DONE.length()))) {
                    throw new InvalidInputException("invalid input");
                } else {
                    int taskNumber = getTaskNumber(command);
                    return new DoneCommand(taskNumber);
                }
            }
        case DELETE:
            if (command.equals(DELETE)) {
                throw new InvalidInputException("invalid task number entered");
            } else {
                if (!Character.isWhitespace(command.charAt(DELETE.length()))) {
                    throw new InvalidInputException("invalid input");
                } else {
                    int taskNumber = getTaskNumber(command);
                    return new DeleteCommand(taskNumber);
                }
            }
        default:
            throw new InvalidInputException("invalid input");
        }

    }

    private String getTaskName(String command, String commandType) throws InvalidInputException, EmptyTaskDescriptionException {
        if (commandType.equals(TODO)) {
            if (command.equals(TODO)) {
                throw new EmptyTaskDescriptionException("Empty duke.task.Todo Description", TODO);
            } else {
                if (!Character.isWhitespace(command.charAt(TODO.length()))) {
                    throw new InvalidInputException("invalid input");
                } else {
                    String taskName = command.substring(TODO.length() + 1);
                    return taskName;
                }
            }
        } else {
            if (command.equals(DEADLINE)) {
                throw new EmptyTaskDescriptionException("Empty duke.task.Deadline Description", DEADLINE);
            } else {
                int startingIndex = command.indexOf(" ");
                int endingIndex = command.indexOf("/");

                if (!Character.isWhitespace(command.charAt(commandType.length())) || endingIndex < 0 ) {
                    throw new InvalidInputException("invalid input");
                }

                //todo deadline return book being invalid input rather than invalid date.

                String taskName = command.substring(startingIndex + 1, endingIndex - 1);

                if (taskName.isEmpty()) {
                    throw new EmptyTaskDescriptionException("empty task name", commandType);
                }
                return taskName;
            }
        }
    }

    private String getDateAndTime(String message, String taskType) throws InvalidDateAndTimeException, InvalidInputException {
        int startingIndex;

        switch (taskType) {
        case DEADLINE:
            startingIndex = message.indexOf("/by ");

            if (startingIndex < 0 || startingIndex + 3 == message.length() - 1) {
                throw new InvalidDateAndTimeException("missing deadline");
            }

            DateAndTime dateAndTime1 = new DateAndTime(message);
            return dateAndTime1.getReformattedDateAndTime();
        case EVENT:
            startingIndex = message.indexOf("/at ");

            if (startingIndex < 0 || startingIndex + 3 == message.length() - 1) {
                throw new InvalidDateAndTimeException("missing event time");
            }

            DateAndTime dateAndTime2 = new DateAndTime(message);
            return dateAndTime2.getReformattedDateAndTime();
        default:
            throw new InvalidDateAndTimeException("invalid date and time");
        }
    }

    private static int getTaskNumber (String message) throws InvalidInputException {
        String numberString = "";
        for (int i = 0; i < message.length(); i++) {
            char currentChar = message.charAt(i);
            if (!numberString.isEmpty() && Character.isWhitespace(currentChar)) {
                break; //task number string complete
            } else if (Character.isDigit(currentChar)) {
                numberString += message.charAt(i);
            } else {}
        }

        int number;
        if (numberString.isEmpty()) {
            throw new InvalidInputException("invalid task number entered");
        } else {
            number = Integer.parseInt(numberString);
        }
        return number;
    }

    private String checkCommandType(String command) throws InvalidInputException {
        List<String> commandTypes = Command.getCommandTypes();
        String commandType = "";
        boolean isCommandTypeComplete = false;

        for (int i = 0; i < command.length(); i++) {
            if (isCommandTypeComplete) {
                break;
            } else if (Character.isWhitespace(command.charAt(i))) {
                throw new InvalidInputException("invalid input");
            } else {
                commandType = commandType + Character.toString(command.charAt(i));
                isCommandTypeComplete = matchCommandType(commandType, commandTypes);
            }
        }

        return commandType;

    }

    private boolean matchCommandType(String commandType, List<String> commandTypes) {
        for (int i = 0; i < commandTypes.size(); i++) {
            if (commandType.equals(commandTypes.get(i))) {
                return true;
            }
        }
        return false;
    }
}
