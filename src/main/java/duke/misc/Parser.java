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
     * @throws DukeException In case of errors.
     */
    public Task makeTask(String input) throws DukeException {
        int idx = input.indexOf(' ');
        String commandType = "";
        String[] arguments = {};
        if (idx >= 0) {
            commandType = input.substring(0, idx).trim();
            arguments = input.substring(idx + 1).split("/");
            for (int i = 0; i < arguments.length; i++) {
                arguments[i] = arguments[i].trim();
            }
        }
        switch(commandType) {
        case "todo":
            return new Todo(arguments[0]);
        case "event":
            if (!input.matches("event [\\s\\S]+/[\\s\\S]+")) {
                throw new InvalidFormatException();
            }
            if (DateTime.isInvalidDate(arguments[1])) {
                throw new InvalidDateException();
            }
            return new Event(arguments[0], arguments[1]);
        case "deadline":
            if (!input.matches("deadline [\\s\\S]+/[\\s\\S]+")) {
                throw new InvalidFormatException();
            }
            if (DateTime.isInvalidDate(arguments[1])) {
                throw new InvalidDateException();
            }
            return new Deadline(arguments[0], arguments[1]);
        default:
            throw new InvalidCommandException();
        }
    }

    /**
     * Determines the action to be executed according to user command.
     *
     * @param input User's command input.
     * @param tl TaskList which action is executed on.
     * @return Message according to what action is executed.
     * @throws DukeException In case of errors.
     * @throws IOException In case of Invalid directory.
     */
    public String execute(String input, TaskList tl) throws DukeException, IOException {
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
                idx = Integer.parseInt(suffix);
            } catch (NumberFormatException e) {
                throw new InvalidIndexException();
            }
            return Ui.DONE_MSG + tl.complete(idx);
        case "delete":
            try {
                idx = Integer.parseInt(suffix);
            } catch (NumberFormatException e) {
                throw new InvalidIndexException();
            }
            return Ui.DELETE_MSG + tl.delete(idx);
        case "find":
            return Ui.FIND_MSG + tl.find(suffix);
        default:
            Task task = makeTask(input);
            return Ui.ADD_MSG + tl.add(task);
        }
    }
}
