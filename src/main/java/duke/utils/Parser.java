package duke.utils;

import duke.commands.AddDeadline;
import duke.commands.AddEvent;
import duke.commands.AddTodo;
import duke.commands.Command;
import duke.commands.Delete;
import duke.commands.Done;
import duke.commands.Find;
import duke.commands.List;
import duke.exceptions.DukeException;
import duke.exceptions.EmptyTaskDescriptionException;
import duke.exceptions.InvalidCommandException;

/**
 * The Parser class is responsible for handling the user input and to perform
 * the necessary operations on the TaskList.
 */
public class Parser {

    /**
     * Returns a Command object based on the user's input
     *
     * @param input The input provided by the user.
     * @return A Command object corresponding to the action intended by the user.
     */
    private static Command createCommand(String input) throws DukeException {
        if (input.equals("list")) {
            return new List();
        } else if (input.matches("done\\s[0-9][0-9]?")) {
            int index = Integer.valueOf(input.split(" ")[1]);
            return new Done(index);
        } else if (input.matches("delete\\s[0-9][0-9]?")) {
            int index = Integer.valueOf(input.split(" ")[1]);
            return new Delete(index);
        } else if (input.matches("todo(.*?)")) {
            if (input.split(" ").length < 2) throw new EmptyTaskDescriptionException();
            String taskName = input.split(" ", 2)[1];
            return new AddTodo(taskName);
        } else if (input.matches("deadline(.*?)")) {
            if (input.split(" ").length < 2) throw new EmptyTaskDescriptionException();
            String firstCommand = input.split("/by", 2)[0];
            String taskName = firstCommand.split(" ", 2)[1];
            String dueDate = input.split("/by", 2)[1];
            return new AddDeadline(taskName.trim(), dueDate.trim());
        } else if (input.matches("event(.*?)")) {
            if (input.split(" ").length < 2) throw new EmptyTaskDescriptionException();
            String firstCommand = input.split("/at", 2)[0];
            String taskName = firstCommand.split(" ", 2)[1];
            String duration = input.split("/at", 2)[1];
            return new AddEvent(taskName.trim(), duration.trim());
        } else if (input.matches("find\\s(.*?)")) {
            return new Find(input.split(" ")[1]);
        } else {
            throw new InvalidCommandException();
        }
    }

    /**
     * Reads the user input and performs the corresponding action to the TaskList.
     *
     * @param taskList A TaskList object storing tasks.
     * @param commandInput Input provided by the user.
     * @throws DukeException If the user input is an invalid command.
     */
    public static TaskList parseInput(TaskList taskList, String commandInput) throws DukeException {
        Command userCommand = createCommand(commandInput);
        return userCommand.execute(taskList);
    }
}
