package duke.misc;

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

public class Parser {

    public Task makeTask(String input) throws DukeException {
        int idx = input.indexOf(' ');
        String name = "";
        String[] args = {};
        if (idx >= 0) {
            name = input.substring(0, idx).trim();
            args = input.substring(idx + 1).split("/");
        }
        switch(name) {
            case "todo":
                return new Todo(args[0].trim());
            case "event":
                if (!input.matches("event (.*)/(.*)")) {
                    throw new InvalidFormatException();
                }
                if (!DateTime.isValidDate(args[1].trim())) {
                    throw new InvalidDateException();
                }
                return new Event(args[0].trim(), args[1].trim());
            case "deadline":
                if (!input.matches("deadline (.*)/(.*)")) {
                    throw new InvalidFormatException();
                }
                if (!DateTime.isValidDate(args[1].trim())) {
                    throw new InvalidDateException();
                }
                return new Deadline(args[0].trim(), args[1].trim());
            default:
                throw new InvalidCommandException();
        }
    }

    // Take in command, parse it and then executes.
    public String execute(String input, TaskList tl) {
        String message = "";
        String[] args = input.split(" ");
        int idx;
        try {
            if (input.equals("bye")) {
                message = Ui.GOODBYE_MSG;
            } else if (input.equals("list")) {
                message = Ui.LIST_MSG + tl.displayList();
            } else if (input.matches("done (.*)")) {
                message = Ui.DONE_MSG;
                try {
                    idx = Integer.parseInt(args[1]);
                } catch (NumberFormatException e) {
                    throw new InvalidIndexException();
                }
                message += tl.complete(idx);
            } else if (input.matches("delete (.*)")) {
                message = Ui.DELETE_MSG;
                try {
                    idx = Integer.parseInt(args[1]);
                } catch (NumberFormatException e) {
                    throw new InvalidIndexException();
                }
                message += tl.delete(idx);
            } else {
                message = Ui.ADD_MSG;
                Task task = makeTask(input);
                message += tl.add(task);
            }
        } catch (DukeException e) {
            message = e.toString();
        }
        return message;
    }
}
