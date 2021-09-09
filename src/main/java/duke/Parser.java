package duke;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DisplayCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.HelpWindowCommand;
import duke.task.TaskList;

/**
 * Represents a parser that makes sense of user input commands.
 */
public class Parser {
    /**
     * Parses the user's input command.
     *
     * @param fullCommand The user's input command.
     * @param tasks       The user's task list.
     * @return A Command corresponding to the user's input.
     * @throws DukeException
     */
    public static Command parse(String fullCommand, TaskList tasks) throws DukeException {
        String[] cmdWordDetails = fullCommand.split(" ", 2); // split into command and details
        String cmdWord = cmdWordDetails[0]; // get first word as command
        String description = cmdWordDetails.length > 1 ? cmdWordDetails[1] : "";

        switch (cmdWord) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new DisplayCommand();
        case "todo":
            return new AddCommand(TaskList.TaskType.TODO, description);
        case "deadline":
            return new AddCommand(TaskList.TaskType.DEADLINE, description);
        case "event":
            return new AddCommand(TaskList.TaskType.EVENT, description);
        case "done":
            return new DoneCommand(validateTaskNumber(description, tasks));
        case "delete":
            return new DeleteCommand(validateTaskNumber(description, tasks));
        case "find":
            return new FindCommand(description);
        case "help":
            return new HelpCommand();
        case ":help":
            return new HelpWindowCommand();
        default:
            throw new DukeException("Sorry, I don't know what that means.");
        }
    }

    /**
     * Checks if input is a valid task number and returns task number if valid.
     * If valid the task number is returned. Else a DukeException is thrown.
     *
     * @param input The user's input string.
     * @param tasks The user's list of tasks.
     * @return The task number as Integer if valid.
     * @throws DukeException
     */
    private static Integer validateTaskNumber(String input, TaskList tasks) throws DukeException {
        Integer taskNum;
        try {
            taskNum = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new DukeException("You did not specify the task number.");
        }
        int listLength = tasks.getListSize();
        int taskIndex = taskNum - 1;
        if (listLength == 0) {
            throw new DukeException("The operation cannot be performed as you have 0 tasks in your list.");
        }
        if (taskIndex < 0 || taskIndex >= tasks.getListSize()) {
            throw new DukeException("The task number must be from 1 to " + listLength + ".");
        }
        return taskNum;
    }
}
