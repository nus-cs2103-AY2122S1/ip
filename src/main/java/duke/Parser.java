package duke;

import duke.command.*;

import java.util.Arrays;

/**
 * A helper class that parses a command from a String.
 */
public class Parser {

    public static final String EMPTY_COMMAND_MESSAGE = "Command cannot be empty";
    public static final String UNKNOWN_COMMAND_MESSAGE = "Command not recognised";
    public static final String EMPTY_TASK_SUFFIX = " must have a name";
    public static final String MISSING_TIME_MESSAGE = " must be %s at a certain time";
    public static final String NO_ARGUMENTS_EXPECTED_SUFFIX = " command should not have any other arguments";

    /**
     * Reads a String, and parses it into a Command. 
     * @param fullCommand A string containing a command to be parsed
     * @return The Command parsed from the input String
     */
    public static Command parse(String fullCommand) {
        try {
            String[] tokens = fullCommand.split(" ");
            if (tokens.length == 0){
                throw new IllegalArgumentException(EMPTY_COMMAND_MESSAGE);
            }
            String commandStr = tokens[0];
            String[] otherTokens = Arrays.copyOfRange(tokens, 1, tokens.length);
            if (commandStr.equals(ToDo.TODO_NAME)) {
                return parseToDo(otherTokens);
            } else if (commandStr.equals(Event.EVENT_NAME)) {
                return parseEvent(otherTokens);
            } else if (commandStr.equals(Deadline.DEADLINE_NAME)) {
                return parseDeadline(otherTokens);
            } else if (commandStr.equals(CommandShowList.SHOW_LIST_NAME)) {
                return parseShowList(otherTokens);
            } else if (commandStr.equals(CommandDelete.DELETE_NAME)) {
                return parseDelete(otherTokens);
            } else if (commandStr.equals(CommandDone.DONE_NAME)) {
                return parseDone(otherTokens);
            } else if (commandStr.equals(CommandExit.EXIT_NAME)) {
                return parseExit(otherTokens);
            } else if (commandStr.equals(CommandFind.FIND_NAME)) {
                return parseFind(otherTokens);
            } else {
                throw new IllegalArgumentException(UNKNOWN_COMMAND_MESSAGE);
            }
        } catch (IllegalArgumentException ex) {
            return new CommandError(ex.getMessage());
        }
    }

    private static CommandAdd parseToDo(String[] tokens) {
        String taskName = String.join(" ", tokens);
        return new CommandAdd(new ToDo(taskName));
    }

    private static CommandAdd parseEvent(String[] tokens) {
        int atIdx = Arrays.asList(tokens).indexOf("/" + Event.EVENT_TIME_MARKER);
        if (atIdx == -1) {
            throw new IllegalArgumentException(Event.EVENT_NAME + String.format(
                    MISSING_TIME_MESSAGE, Event.EVENT_TIME_MARKER
            ));
        } else if (atIdx == 0) {
            throw new IllegalArgumentException(Event.EVENT_NAME + EMPTY_TASK_SUFFIX);
        } else {
            String taskName = String.join(" ",
                    Arrays.copyOfRange(tokens, 0, atIdx));
            String timeStr = String.join(" ",
                    Arrays.copyOfRange(tokens, atIdx + 1, tokens.length));
            return new CommandAdd(new Event(taskName, timeStr));
        }
    }

    private static CommandShowList parseShowList(String[] tokens) {
        if (tokens.length > 0) {
            throw new IllegalArgumentException(CommandShowList.SHOW_LIST_NAME + NO_ARGUMENTS_EXPECTED_SUFFIX);
        }
        return new CommandShowList();
    }

    private static CommandAdd parseDeadline(String[] tokens) {
        int byIdx = Arrays.asList(tokens).indexOf("/" + Deadline.DEADLINE_TIME_MARKER);
        if (byIdx == -1) {
            throw new IllegalArgumentException(Deadline.DEADLINE_NAME + EMPTY_TASK_SUFFIX);
        } else if (byIdx == 0) {
            throw new IllegalArgumentException(Deadline.DEADLINE_NAME + String.format(
                    MISSING_TIME_MESSAGE, Deadline.DEADLINE_TIME_MARKER
            ));
        } else {
            String taskName = String.join(" ",
                    Arrays.copyOfRange(tokens, 0, byIdx));
            String timeStr = String.join(" ",
                    Arrays.copyOfRange(tokens, byIdx + 1, tokens.length));
            return new CommandAdd(new Deadline(taskName, timeStr));
        }
    }

    private static CommandDelete parseDelete(String[] tokens) {
        String taskName = String.join(" ", tokens);
        return new CommandDelete(taskName);
    }

    private static CommandDone parseDone(String[] tokens) {
        String taskName = String.join(" ", tokens);
        return new CommandDone(taskName);
    }

    private static CommandExit parseExit(String[] tokens) {
        if (tokens.length > 0) {
            throw new IllegalArgumentException(CommandExit.EXIT_NAME + NO_ARGUMENTS_EXPECTED_SUFFIX);
        }
        return new CommandExit();
    }

    private static CommandFind parseFind(String[] tokens) {
        String keyword = String.join(" ", tokens);
        return new CommandFind(keyword);
    }
}
