package duke;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    /**
     * Handles input commands to the Duke application.
     *
     * @param command The input command from the user.
     * @return TaskExecution lambda which handles the corresponding command.
     */
    public static TaskExecution parse(String command) throws DukeException {
        if (command.equals("list")) {
            return TaskList::list;
        } else if (command.matches("^done -?[0-9]+$")) {
            return tasks -> tasks.done(command);
        } else if (command.matches("^todo( .*)?")) {
            return tasks -> tasks.todo(command);
        } else if (command.matches("^deadline( .*)?")) {
            return tasks -> tasks.deadline(command);
        } else if (command.matches("^event( .*)?")) {
            return tasks -> tasks.event(command);
        } else if (command.matches("^delete -?[0-9]+$")) {
            return tasks -> tasks.delete(command);
        } else if (command.matches("^find( .*)?")) {
            return tasks -> tasks.find(command);
        } else {
            throw new UnknownCommandException();
        }
    }

    /**
     * Gets the task index from the command, which is the second argument of the command.
     *
     * @param command The command inputted by the user.
     * @return The task index in the command.
     * @throws InvalidFormatException When the second argument in the command is not an integer.
     */
    public static int getIndexFromCommand(String command) throws InvalidFormatException {
        String[] split = command.split(" ");
        try {
            // Task index is the second argument of commands that require an index
            return Integer.parseInt(split[1]);
        } catch (NumberFormatException e) {
            throw new InvalidFormatException("Index must be an integer!");
        }
    }

    /**
     * Validates the given string against the provided regex. Also validates that the given string
     * does not contain commas.
     *
     * @param str         The given string.
     * @param regex       The given regex.
     * @param validFormat The correct input format to be displayed to the user when this regex fails.
     * @throws InvalidFormatException When the given string contains commas or when there are no matches.
     */
    public static String[] validateRegexAndMatch(String str, String regex, String validFormat)
            throws InvalidFormatException {
        if (str.contains(",")) {
            throw new InvalidFormatException("Please do not include commas!");
        }

        Pattern r = Pattern.compile(regex);
        Matcher m = r.matcher(str);

        if (m.find()) {
            int matchCount = m.groupCount() + 1;
            String[] groups = new String[matchCount];
            for (int i = 0; i < matchCount; i++) {
                groups[i] = m.group(i);
            }
            return groups;
        } else {
            // No matches
            throw new InvalidFormatException(validFormat);
        }
    }
}
