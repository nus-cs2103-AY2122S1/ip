package duke;

import duke.command.*;

import java.util.Arrays;

/**
 * A helper class that parses a command from a String.
 */
public class Parser {

    /**
     * Reads a String, and parses it into a Command. 
     * @param command A string containing a command to be parsed
     * @return The Command parsed from the input String
     */
    public static Command parse(String command) {
        String[] tokens = command.split(" ");
        try {
            if (tokens.length == 0){
                throw new IllegalArgumentException("Command cannot be empty");
            } else if (tokens[0].equals("todo")) {
                if (tokens.length == 1) {
                    throw new IllegalArgumentException("ToDo must have a name");
                } else {
                    String taskName = String.join(" ",
                            Arrays.copyOfRange(tokens, 1, tokens.length));
                    return new CommandAdd(new ToDo(taskName));
                }
            } else if (tokens[0].equals("event")) {
                if (tokens.length == 1) {
                    throw new IllegalArgumentException("ToDo must have a name");
                } else {
                    int atIdx = Arrays.asList(tokens).indexOf("/at");
                    if (atIdx == -1) {
                        throw new IllegalArgumentException("Event must be at a certain time");
                    } else if (atIdx == 1) {
                        throw new IllegalArgumentException("Event must have a name");
                    } else {
                        String taskName = String.join(" ",
                                Arrays.copyOfRange(tokens, 1, atIdx));
                        String timeStr = String.join(" ",
                                Arrays.copyOfRange(tokens, atIdx + 1, tokens.length));
                        return new CommandAdd(new Event(taskName, timeStr));
                    }
                }
            } else if (tokens[0].equals("deadline")) {
                int byIdx = Arrays.asList(tokens).indexOf("/by");
                if (byIdx == -1) {
                    throw new IllegalArgumentException("Deadline must be by a certain time");
                } else if (byIdx == 1) {
                    throw new IllegalArgumentException("Deadline must have a name");
                } else {
                    String taskName = String.join(" ",
                            Arrays.copyOfRange(tokens, 1, byIdx));
                    String timeStr = String.join(" ",
                            Arrays.copyOfRange(tokens, byIdx + 1, tokens.length));
                    return new CommandAdd(new Deadline(taskName, timeStr));
                }
            } else if (tokens[0].equals("list")) {
                return new CommandShowList();
            } else if (tokens[0].equals("delete")) {
                String taskName = String.join(" ", Arrays.copyOfRange(tokens, 1, tokens.length));
                return new CommandDelete(taskName);
            } else if (tokens[0].equals("done")) {
                String taskName = String.join(" ", Arrays.copyOfRange(tokens, 1, tokens.length));
                return new CommandDone(taskName);
            } else if (tokens[0].equals("exit")) {
                return new CommandExit();
            } else if (tokens[0].equals("find")) {
                String keyword = String.join(" ", Arrays.copyOfRange(tokens, 1, tokens.length));
                return new CommandFind(keyword);
            } else {
                throw new IllegalArgumentException("Command not recognised");
            }
        } catch (IllegalArgumentException ex) {
            return new CommandError(ex.getMessage());
        }
    }
}
