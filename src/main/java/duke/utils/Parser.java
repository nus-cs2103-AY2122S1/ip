package duke.utils;

import duke.commands.Command;
import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.EventCommand;
import duke.commands.ExitCommand;
import duke.commands.FindCommand;
import duke.commands.ListAllCommand;
import duke.commands.SortDeadlinesCommand;
import duke.commands.ToDoCommand;
import duke.exceptions.EmptyTaskDescriptionException;
import duke.exceptions.UnknownInputException;

/**
 * The Parser to parse and make sense of the user commands.
 */
public class Parser {

    /**
     * Returns a Command object containing the necessary information to be executed.
     *
     * @param fullCommand the full command input by the user.
     * @return a Command object containing the necessary information to be executed.
     * @throws UnknownInputException if the full command given is invalid.
     * @throws EmptyTaskDescriptionException if a valid command was given but there was no task description.
     */
    public static Command parse(String fullCommand) throws UnknownInputException, EmptyTaskDescriptionException {

        if (fullCommand.startsWith("bye")) {
            return new ExitCommand();
        } else if (fullCommand.startsWith("list")) {
            return new ListAllCommand();
        } else if (fullCommand.startsWith("done")) {
            int taskNum = extractTaskNumberFromCommand(fullCommand);
            return new DoneCommand(taskNum);
        } else if (fullCommand.startsWith("todo")) {
            String todoDescription = getTodoDescription(fullCommand);
            return new ToDoCommand(todoDescription);
        } else if (fullCommand.startsWith("deadline")) {
            String descPart = getDeadlineDescriptionPart(fullCommand);
            String byPart = getDeadlineByPart(fullCommand);
            return new DeadlineCommand(descPart, byPart);
        } else if (fullCommand.startsWith("event")) {
            String descPart = getEventDescriptionPart(fullCommand);
            String atPart = getEventAtPart(fullCommand);
            return new EventCommand(descPart, atPart);
        } else if (fullCommand.startsWith("delete")) {
            int taskNum = extractTaskNumberFromCommand(fullCommand);
            return new DeleteCommand(taskNum);
        } else if (fullCommand.startsWith("find")) {
            String keyword = getKeywordFromFindCommand(fullCommand);
            return new FindCommand(keyword);
        } else if (fullCommand.startsWith("chrono deadlines")) {
            return new SortDeadlinesCommand();
        } else {
            throw new UnknownInputException();
        }
    }

    private static String getKeywordFromFindCommand(String fullCommand) {
        return fullCommand.substring(5);
    }

    private static String getEventDescriptionPart(String fullCommand) throws EmptyTaskDescriptionException {
        try {
            int sep = fullCommand.indexOf('/', 6);
            String descPart = fullCommand.substring(6, sep - 1);
            return descPart;
        } catch (StringIndexOutOfBoundsException strE) {
            throw new EmptyTaskDescriptionException("event");
        }
    }

    private static String getEventAtPart(String fullCommand) throws EmptyTaskDescriptionException {
        try {
            int sep = fullCommand.indexOf('/', 6);
            String atPart = fullCommand.substring(sep + 1);
            return atPart;
        } catch (StringIndexOutOfBoundsException strE) {
            throw new EmptyTaskDescriptionException("event");
        }
    }

    private static String getDeadlineDescriptionPart(String fullCommand) throws EmptyTaskDescriptionException {
        try {
            int sep = fullCommand.indexOf('/', 9);
            String descPart = fullCommand.substring(9, sep - 1);
            return descPart;
        } catch (StringIndexOutOfBoundsException strE) {
            throw new EmptyTaskDescriptionException("deadline");
        }
    }

    private static String getDeadlineByPart(String fullCommand) throws EmptyTaskDescriptionException {
        try {
            int sep = fullCommand.indexOf('/', 9);
            String byPart = fullCommand.substring(sep + 4);
            return byPart;
        } catch (StringIndexOutOfBoundsException strE) {
            throw new EmptyTaskDescriptionException("deadline");
        }
    }
    private static String getTodoDescription(String fullCommand) throws EmptyTaskDescriptionException {
        try {
            return fullCommand.substring(5);
        } catch (StringIndexOutOfBoundsException strE) {
            throw new EmptyTaskDescriptionException("todo");
        }
    }

    /**
     * Returns the integer value of the task number input by the user, right after the command keyword.
     * This method subtracts 1 from that value because the task list starts counting from 0.
     *
     * @param fullCommand full command input by the user.
     * @return integer value of the task number to perform the command on.
     */
    private static int extractTaskNumberFromCommand(String fullCommand) throws EmptyTaskDescriptionException {
        try {
            String[] listOfWordsInCommand = fullCommand.split("\\s");
            int inputIntegerValue = Integer.parseInt(listOfWordsInCommand[1]);
            int taskNum = inputIntegerValue - 1;
            return taskNum;
        } catch (StringIndexOutOfBoundsException strE) {
            throw new EmptyTaskDescriptionException("todo");
        }
    }
}
