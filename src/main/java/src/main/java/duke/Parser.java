package src.main.java.duke;

/**
 * Represents the driver of the chat bot and contains all operations to respond
 * to the user.
 */

public class Parser {

    private UI ui;

    Parser(UI ui) {
        this.ui = ui;
    }

    /**
     * driver of the chat bot that decides the response.
     *
     * @param input String input from the user.
     * @return response of the chat bot based on the input.
     * @throws DukeException
     */
    String parse(String input) throws DukeException {
        assert input != null : "input cannot be null";
        String[] split = input.split(" ", 2);
        String command = split[0];

        if (input.equals("bye")) {
            return "Bye. Hope to see you again soon!";
        } else if (input.equals("list")) {
            return ui.printList();
        } else if (command.equals("done")) {
            return markAsDone(split);
        } else if (command.equals("delete")) {
            return delete(split);
        } else if (command.equals("find")) {
            return find(split);
        } else if (command.equals("todo")) {
            return createTodo(split);
        } else if (command.equals("event")) {
            return createEvent(split);
        } else if (command.equals("deadline")) {
            return createDeadline(split);
        } else if (command.equals("update")) {
            return update(split);
        }
        return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    }

    String markAsDone(String[] split) throws DukeException {
        if (split.length < 2 || split[1].isEmpty()) {
            throw new NoNumberException("☹ OOPS!!! No task number was given in the input");
        }
        return ui.markAsDone(Integer.parseInt(split[1]));
    }

    String delete(String[] split) throws DukeException {
        if (split.length < 2 || split[1].isEmpty()) {
            throw new NoNumberException("☹ OOPS!!! No task number was given in the input");
        }
        return ui.deleteTask(Integer.parseInt(split[1]));
    }

    String find(String[] split) throws DukeException {
        if (split.length < 2) {
            throw new InvalidInputException("☹ OOPS!!! No task was given in the input");
        }
        return ui.find(split[1]);
    }

    String createTodo(String[] split) throws DukeException {
        if (split.length < 2) {
            throw new NoDescriptionException("☹ OOPS!!! The description of a " + split[0] + " cannot be empty.");
        }
        return ui.createTodo(split[1]);
    }

    String createEvent(String[] split) throws DukeException {
        if (split.length < 2) {
            throw new NoDescriptionException("☹ OOPS!!! The description of a " + split[0] + " cannot be empty.");
        }
        String time = split[1].split(" /at ", 2)[1];
        String task = split[1].split(" /at ", 2)[0];
        return ui.createEvent(task, time);
    }

    String createDeadline(String[] split) throws DukeException {
        if (split.length < 2) {
            throw new NoDescriptionException("☹ OOPS!!! The description of a " + split[0] + " cannot be empty.");
        }
        String time = split[1].split(" /by ", 2)[1];
        String task = split[1].split(" /by ", 2)[0];
        return ui.createDeadline(task, time);
    }

    String update(String[] split) throws DukeException {
        String task = split[1].split(" /to ", 2)[0];
        String time = split[1].split(" /to ", 2)[1];
        return ui.update(task, time);
    }

}
