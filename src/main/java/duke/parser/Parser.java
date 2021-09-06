package duke.parser;

import java.util.List;
import java.util.stream.Collectors;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.EditCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.constant.EditType;
import duke.constant.TaskType;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * The Parser class encapsulates parsing operations that involve reading in data and
 * transforming it into a useful format to be utilised by other classes.
 */
public class Parser {
    private static final String SAVE_CORRUPTED_ERROR_MESSAGE = "Your save file is corrupted and has an invalid format.";

    private static final String NO_COMMAND_ERROR_MESSAGE = "Please tell Duke what to do.";

    private static final String INVALID_COMMAND_ERROR_MESSAGE = "Invalid command. List of valid commands include:\n"
            + "help | list | todo | deadline | event | done | delete | find | bye";

    /**
     * Parses a list of strings representing the contents of a save file and
     * transforms it into a list of task objects.
     *
     * @param saveFileContents A list of strings, each representing a task.
     * @return A list of task objects.
     * @throws DukeException If strings passed to the parser are not in the expected format.
     */
    public static List<Task> parseSaveFile(List<String> saveFileContents) throws DukeException {
        return saveFileContents.stream().map(task -> {
            try {
                String[] taskInfo = task.split("\\|");
                String taskType = taskInfo[0];
                boolean isDone = taskInfo[1].equals("1");
                String taskDescription = taskInfo[2];
                switch (taskType) {
                case "T":
                    return new ToDo(taskDescription, isDone);
                case "D":
                    return new Deadline(taskDescription, isDone, taskInfo[3]);
                case "E":
                    return new Event(taskDescription, isDone, taskInfo[3]);
                default:
                    throw new DukeException(SAVE_CORRUPTED_ERROR_MESSAGE);
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException(SAVE_CORRUPTED_ERROR_MESSAGE);
            }
        }).collect(Collectors.toList());
    }

    /**
     * Parses a string representing the user's input and creates a
     * command object with relevant data extracted from the user input.
     *
     * @param userInput The user's input.
     * @return A command object with relevant data.
     * @throws DukeException If the string passed to the parser does not contain a command recognized by Duke.
     */
    public static Command parseUserInput(String userInput) throws DukeException {
        if (userInput.isBlank()) {
            throw new DukeException(NO_COMMAND_ERROR_MESSAGE);
        } else if (userInput.equals("bye")) {
            return new ExitCommand();
        } else if (userInput.equals("list")) {
            return new ListCommand();
        } else if (userInput.startsWith("todo")) {
            return new AddCommand(TaskType.TODO, userInput);
        } else if (userInput.startsWith("deadline")) {
            return new AddCommand(TaskType.DEADLINE, userInput);
        } else if (userInput.startsWith("event")) {
            return new AddCommand(TaskType.EVENT, userInput);
        } else if (userInput.startsWith("done")) {
            return new EditCommand(EditType.DONE, userInput);
        } else if (userInput.startsWith("delete")) {
            return new EditCommand(EditType.DELETE, userInput);
        } else if (userInput.startsWith("find")) {
            return new FindCommand(userInput);
        } else if (userInput.startsWith("help")) {
            return new HelpCommand(userInput);
        } else {
            throw new DukeException(INVALID_COMMAND_ERROR_MESSAGE);
        }
    }
}
