package duke;

import duke.command.*;

import java.util.Arrays;

/**
 * A helper class that parses a command from a String.
 */
public class Parser {

    /**
     * Reads a String, and parses it into a Command. 
     * @param fullCommand A string containing a command to be parsed
     * @return The Command parsed from the input String
     */
    public static Command parse(String fullCommand) {
        try {
            String[] tokens = fullCommand.split(" ");
            if (tokens.length == 0){
                throw new IllegalArgumentException("Command cannot be empty");
            }
            String commandStr = tokens[0];
            String[] otherTokens = Arrays.copyOfRange(tokens, 1, tokens.length);
            if (commandStr.equals("todo")) {
                return parseToDo(otherTokens);
            } else if (commandStr.equals("event")) {
                return parseEvent(otherTokens);
            } else if (commandStr.equals("deadline")) {
                return parseDeadline(otherTokens);
            } else if (commandStr.equals("list")) {
                return parseShowList(otherTokens);
            } else if (commandStr.equals("delete")) {
                return parseDelete(otherTokens);
            } else if (commandStr.equals("done")) {
                return parseDone(otherTokens);
            } else if (commandStr.equals("exit")) {
                return parseExit(otherTokens);
            } else if (commandStr.equals("find")) {
                return parseFind(otherTokens);
            } else {
                throw new IllegalArgumentException("Command not recognised");
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
        int atIdx = Arrays.asList(tokens).indexOf("/at");
        if (atIdx == -1) {
            throw new IllegalArgumentException("Event must be at a certain time");
        } else if (atIdx == 1) {
            throw new IllegalArgumentException("Event must have a name");
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
            throw new IllegalArgumentException("List command should not have any other arguments");
        }
        return new CommandShowList();
    }

    private static CommandAdd parseDeadline(String[] tokens) {
        int byIdx = Arrays.asList(tokens).indexOf("/by");
        if (byIdx == -1) {
            throw new IllegalArgumentException("Deadline must be by a certain time");
        } else if (byIdx == 1) {
            throw new IllegalArgumentException("Deadline must have a name");
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
            throw new IllegalArgumentException("Exit command should not have any other arguments");
        }
        return new CommandExit();
    }

    private static CommandFind parseFind(String[] tokens) {
        String keyword = String.join(" ", tokens);
        return new CommandFind(keyword);
    }
}
