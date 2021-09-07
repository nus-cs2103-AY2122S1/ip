package duke.parser;

import duke.command.AddTaskCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EditCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.data.DateAndTime;
import duke.data.exceptions.DukeException;
import duke.data.exceptions.EmptyCommandInformationException;
import duke.data.exceptions.EmptyTaskNameException;
import duke.data.exceptions.InvalidDateAndTimeException;
import duke.data.exceptions.InvalidInputException;
import duke.ui.Message;
import duke.ui.Tag;

/**
 * Represents a parser that makes sense of what the user input is.
 */
public class Parser {
    public static final String TODO = "todo";
    public static final String DEADLINE = "deadline";
    public static final String EVENT = "event";
    public static final String EXIT = "exit";
    public static final String LIST = "list";
    public static final String DONE = "done";
    public static final String DELETE = "delete";
    public static final String FIND = "find";
    public static final String EDIT = "edit";

    /**
     * Initialises a Parser object.
     */
    public Parser() {}

    /**
     * Returns a Command corresponding to the user input.
     *
     * @param command user input
     * @return a Command object
     * @throws DukeException
     */
    public Command parse(String command) throws DukeException {
        Command.CommandType commandType = getCommandType(command);

        switch (commandType) {
        case TODO:
            String todoName = getTaskName(command, commandType);
            return new AddTaskCommand(Command.CommandType.TODO, todoName);
        case DEADLINE:
            String deadlineName = getTaskName(command, commandType);
            String deadline = getDateAndTime(command, Command.CommandType.DEADLINE);
            return new AddTaskCommand(Command.CommandType.DEADLINE, deadlineName, deadline);
        case EVENT:
            String eventName = getTaskName(command, commandType);
            String eventTime = getDateAndTime(command, Command.CommandType.EVENT);
            return new AddTaskCommand(Command.CommandType.EVENT, eventName, eventTime);
        case EXIT:
            return new ExitCommand();
        case LIST:
            return new ListCommand();
        case DONE:
            int markedTaskNumber = getTaskNumber(command);
            return new DoneCommand(markedTaskNumber);
        case DELETE:
            int deletedTaskNumber = getTaskNumber(command);
            return new DeleteCommand(deletedTaskNumber);
        case FIND:
            String searchTerm = getSearchTerm(command);
            return new FindCommand(searchTerm);
        case EDIT:
            int updatedTaskNumber = getTaskNumberForEdit(command);
            EditCommand.EditType editType = getEditType(command);
            String updatedField = getUpdatedField(command, editType);
            return new EditCommand(updatedTaskNumber, editType, updatedField);
        default:
            throw new InvalidInputException(Message.MESSAGE_INVALID_COMMAND);
        }

    }

    private int getTaskNumberForEdit(String command) throws EmptyCommandInformationException, InvalidInputException {
        String[] splitArray = command.split("/");

        if (splitArray.length == 1) {
            throw new EmptyCommandInformationException(Message.MESSAGE_EMPTY_EDIT);
        }
        return getTaskNumber(splitArray[0]);
    }

    private String getUpdatedField(String command, EditCommand.EditType editType) throws EmptyCommandInformationException,
            InvalidInputException, InvalidDateAndTimeException {
        switch (editType) {
        case NAME:
            int nameStartingIndex = command.indexOf(Tag.NAME) + 2;

            if (nameStartingIndex > command.length() - 1) {
                throw new EmptyCommandInformationException(Message.MESSAGE_EMPTY_EDIT);
            }

            return command.substring(nameStartingIndex);
        case DATE_AND_TIME:
            int dateStartingIndex = command.indexOf(Tag.BY) + 2; //todo change to tag.date
            DateAndTime dateAndTime = new DateAndTime(command.substring(dateStartingIndex));
            return dateAndTime.getReformattedDateAndTime();
        default:
            throw new InvalidInputException(Message.MESSAGE_ERROR_OCCURRED);
        }
    }

    private EditCommand.EditType getEditType(String command) throws InvalidInputException, EmptyCommandInformationException {
        int slashIndex = command.indexOf("/");

        if (slashIndex < 0) {
            throw new EmptyCommandInformationException(Message.MESSAGE_EMPTY_EDIT);
        }

        char editTypeChar = command.charAt(slashIndex - 1);
        switch (editTypeChar) {
        case 'n':
            return EditCommand.EditType.NAME;
        case 'd':
            return EditCommand.EditType.DATE_AND_TIME;
        default:
            throw new InvalidInputException(Message.MESSAGE_INVALID_EDIT_TYPE);
        }
    }

    private String getTaskName(String command, Command.CommandType commandType) throws InvalidInputException,
            EmptyTaskNameException, InvalidDateAndTimeException {
        String[] splitCommandArray = command.split(Tag.NAME, 2);

        switch (commandType) {
        case TODO:
            if (splitCommandArray.length == 1) {
                throw new EmptyTaskNameException(Message.MESSAGE_EMPTY_TODO_NAME);
            }
            return splitCommandArray[1].trim();
        case DEADLINE:
            if (splitCommandArray.length == 1) {
                throw new EmptyTaskNameException(Message.MESSAGE_EMPTY_DEADLINE_NAME);
            }

            String[] nameAndDateArray = splitCommandArray[1].split(Tag.BY, 2);
            if (nameAndDateArray.length == 1){
                throw new InvalidDateAndTimeException(Message.MESSAGE_MISSING_DEADLINE);
            }
            return nameAndDateArray[0].trim();
        case EVENT:
            if (splitCommandArray.length == 1) {
                throw new EmptyTaskNameException(Message.MESSAGE_EMPTY_EVENT_NAME);
            }

            String[] nameAndEventTimeArray = splitCommandArray[1].split(Tag.AT, 2);
            if (nameAndEventTimeArray.length == 1){
                throw new InvalidDateAndTimeException(Message.MESSAGE_MISSING_EVENT_TIME);
            }
            return nameAndEventTimeArray[0].trim();
        default:
            throw new InvalidInputException(Message.MESSAGE_INVALID_COMMAND);
        }
    }

    private String getDateAndTime(String message, Command.CommandType taskType) throws InvalidDateAndTimeException {
        String[] splitCommandArray;

        switch (taskType) {
        case DEADLINE:
            splitCommandArray = message.split(Tag.BY, 2);

            //Checks whether "by/" is present in the command input
            if (splitCommandArray.length == 1) {
                throw new InvalidDateAndTimeException(Message.MESSAGE_MISSING_DEADLINE);
            }

            DateAndTime deadline = new DateAndTime(splitCommandArray[1].trim());
            return deadline.getReformattedDateAndTime();
        case EVENT:
            splitCommandArray = message.split(Tag.AT, 2);

            //Checks whether "at/" is present in the command input
            if (splitCommandArray.length == 1) {
                throw new InvalidDateAndTimeException(Message.MESSAGE_MISSING_EVENT_TIME);
            }

            DateAndTime eventTime = new DateAndTime(splitCommandArray[1].trim());
            return eventTime.getReformattedDateAndTime();
        default:
            throw new InvalidDateAndTimeException(Message.MESSAGE_INVALID_DATE_AND_TIME);
        }
    }

    private int getTaskNumber (String message) throws InvalidInputException {
        String taskNumberString = "";
        for (int i = 0; i < message.length(); i++) {
            char currentChar = message.charAt(i);
            if (!taskNumberString.isEmpty() && Character.isWhitespace(currentChar)) {
                break; //task number string complete
            }

            if (Character.isDigit(currentChar)) {
                taskNumberString += message.charAt(i);
            }
        }

        if (taskNumberString.isEmpty()) {
            throw new InvalidInputException(Message.MESSAGE_INVALID_TASK_NUMBER);
        } else {
            return Integer.parseInt(taskNumberString);
        }
    }

    private String getSearchTerm(String message) {
        int spaceIndex = message.indexOf(" ");
        return message.substring(spaceIndex + 1);
    }

    private Command.CommandType getCommandType(String command) throws InvalidInputException, EmptyCommandInformationException {
        String commandTypeString = getCommandTypeString(command);

        switch (commandTypeString) {
        case TODO:
            return Command.CommandType.TODO;
        case EVENT:
            return Command.CommandType.EVENT;
        case DEADLINE:
            return Command.CommandType.DEADLINE;
        case DELETE:
            return Command.CommandType.DELETE;
        case LIST:
            return Command.CommandType.LIST;
        case EXIT:
            return Command.CommandType.EXIT;
        case FIND:
            return Command.CommandType.FIND;
        case DONE:
            return Command.CommandType.DONE;
        case EDIT:
            return Command.CommandType.EDIT;
        default:
            throw new InvalidInputException(Message.MESSAGE_INVALID_COMMAND);
        }

    }

    private String getCommandTypeString(String command) throws InvalidInputException, EmptyCommandInformationException {
        int spaceIndex = command.indexOf(" ");
        boolean isCommandList = command.equals(LIST);
        boolean isCommandExit = command.equals(EXIT);

        //Checks whether command type is LIST or EXIT
        if (isCommandList) {
            return LIST;
        }

        if (isCommandExit) {
            return EXIT;
        }

        //Checks for invalid input
        if (spaceIndex < 0) {
            checkOnlyCommandTypeStringKeyed(command);
        }
        return command.substring(0, spaceIndex);
    }

    private void checkOnlyCommandTypeStringKeyed(String command) throws EmptyCommandInformationException,
            InvalidInputException {
        switch (command) {
        case TODO:
            throw new EmptyCommandInformationException(Message.MESSAGE_EMPTY_TODO);
        case DEADLINE:
            throw new EmptyCommandInformationException(Message.MESSAGE_EMPTY_DEADLINE);
        case EVENT:
            throw new EmptyCommandInformationException(Message.MESSAGE_EMPTY_EVENT);
        case DONE:
        case DELETE:
            throw new EmptyCommandInformationException(Message.MESSAGE_INVALID_TASK_NUMBER);
        case FIND:
            throw new EmptyCommandInformationException(Message.MESSAGE_INVALID_FIND);
        case EDIT:
            throw new EmptyCommandInformationException(Message.MESSAGE_EMPTY_EDIT);
        default:
            throw new InvalidInputException(Message.MESSAGE_INVALID_COMMAND);
        }
    }

}
