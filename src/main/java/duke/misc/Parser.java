package duke.misc;

import java.io.IOException;

import duke.exception.DukeException;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidDateException;
import duke.exception.InvalidFormatException;
import duke.exception.InvalidIndexException;
import duke.task.DateTime;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Parser class to handle user commands.
 */
public class Parser {
    /**
     * Creates a Task object according to user command.
     *
     * @param input User's command input.
     * @return Respective Task object.
     * @throws DukeException Throws DukeException if input is invalid.
     */
    public Task makeTask(String input) throws DukeException {
        int idx = input.indexOf(' ');
        String commandType = "";
        String[] args = {};
        if (idx >= 0) {
            commandType = input.substring(0, idx).trim();
            args = input.substring(idx + 1).split("/");
            for (int i = 0; i < args.length; i++) {
                args[i] = args[i].trim();
            }
        }
        switch(commandType) {
        case "todo":
            return new Todo(args[0]);
        case "event":
            if (!input.matches("event [\\s\\S]+/[\\s\\S]+")) {
                throw new InvalidFormatException();
            }
            if (DateTime.isInvalidDate(args[1])) {
                throw new InvalidDateException();
            }
            return new Event(args[0], args[1]);
        case "deadline":
            if (!input.matches("deadline [\\s\\S]+/[\\s\\S]+")) {
                throw new InvalidFormatException();
            }
            if (DateTime.isInvalidDate(args[1])) {
                throw new InvalidDateException();
            }
            return new Deadline(args[0], args[1]);
        default:
            throw new InvalidCommandException();
        }
    }

    /**
     * Executes the appropriate action according to user command.
     *
     * @param input User's command input.
     * @param tl TaskList object which action is executed on.
     * @return Message according to what action is executed.
     * @throws DukeException Throws DukeException if the input is invalid.
     * @throws IOException Throws IOException the directory to save data in is non-existent.
     */
    public String executeCommand(String input, TaskList tl) throws DukeException, IOException {
        String prefix = input;
        String suffix = "";
        int idx = input.indexOf(' ');
        if (idx >= 0) {
            prefix = input.substring(0, idx);
            suffix = input.substring(idx + 1);
        }
        switch (prefix) {
        case "bye":
            if (!suffix.isEmpty()) {
                throw new InvalidCommandException();
            }
            tl.saveData();
            return Ui.GOODBYE_MSG;
        case "list":
            if (!suffix.isEmpty()) {
                throw new InvalidCommandException();
            }
            return Ui.LIST_MSG + tl.displayList();
        case "done":
            try {
                int taskIdx = Integer.parseInt(suffix);
                return Ui.DONE_MSG + tl.completeTask(taskIdx);
            } catch (NumberFormatException e) {
                throw new InvalidIndexException();
            }
        case "delete":
            try {
                int taskIdx = Integer.parseInt(suffix);
                return Ui.DELETE_MSG + tl.deleteTask(taskIdx);
            } catch (NumberFormatException e) {
                throw new InvalidIndexException();
            }
        case "find":
            return Ui.FIND_MSG + tl.findTasks(suffix);
        default:
            Task task = makeTask(input);
            return Ui.ADD_MSG + tl.addTask(task);
        }
    }
}
