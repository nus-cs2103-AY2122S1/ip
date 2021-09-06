package duke;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class encapsulates the parsing logic for the application.
 */
public class Parser {
    /**
     * Handles input commands to the Duke application.
     *
     * @param command The input command from the user.
     * @return TaskExecution lambda which handles the corresponding command.
     */
    public static TaskExecution parse(String command) throws DukeException {
        if (command.equals("list")) {
            return (tasks, mainStorage, archiveStorage) -> tasks.list();
        } else if (command.matches("^done -?[0-9]+$")) {
            return (tasks, mainStorage, archiveStorage) -> {
                String output = tasks.done(command);
                mainStorage.save(tasks.toSaveFormat());
                return output;
            };
        } else if (command.matches("^todo( .*)?")) {
            return (tasks, mainStorage, archiveStorage) -> {
                String output = tasks.todo(command);
                mainStorage.save(tasks.toSaveFormat());
                return output;
            };
        } else if (command.matches("^deadline( .*)?")) {
            return (tasks, mainStorage, archiveStorage) -> {
                String output = tasks.deadline(command);
                mainStorage.save(tasks.toSaveFormat());
                return output;
            };
        } else if (command.matches("^event( .*)?")) {
            return (tasks, mainStorage, archiveStorage) -> {
                String output = tasks.event(command);
                mainStorage.save(tasks.toSaveFormat());
                return output;
            };
        } else if (command.matches("^delete -?[0-9]+$")) {
            return (tasks, mainStorage, archiveStorage) -> {
                String output = tasks.delete(command);
                mainStorage.save(tasks.toSaveFormat());
                return output;
            };
        } else if (command.matches("^find( .*)?")) {
            return (tasks, mainStorage, archiveStorage) -> {
                String output = tasks.find(command);
                mainStorage.save(tasks.toSaveFormat());
                return output;
            };
        } else if (command.equals("archive")) {
            String successMessage = "Successfully archived!";
            return (tasks, mainStorage, archiveStorage) -> {
                archiveStorage.save(tasks.toSaveFormat());
                tasks.clearAllTasks();
                mainStorage.save(tasks.toSaveFormat());
                return successMessage;
            };
        } else if (command.equals("loadArchive")) {
            String successMessage = "Successfully loaded archive!";
            String errorMessage = "Error while loading archive file! Does it exist?";
            return (tasks, mainStorage, archiveStorage) -> {
                List<String> fileContents;
                try {
                    fileContents = archiveStorage.load();
                } catch (IOException e) {
                    return errorMessage;
                }
                tasks.appendListOfStrings(fileContents);
                mainStorage.save(tasks.toSaveFormat());
                return successMessage;
            };
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
