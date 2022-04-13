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

    /**
     * method to mark the given task to done.
     *
     * @param split String array that contains the index of hte task entered by the user.
     * @return the string form of the task marked as done.
     * @throws DukeException
     */
    String markAsDone(String[] split) throws DukeException {
        if (split.length < 2 || split[1].isEmpty()) {
            throw new NoNumberException("☹ OOPS!!! No task number was given in the input");
        }
        return ui.markAsDone(Integer.parseInt(split[1]));
    }

    /**
     * method to delete the given task from the list.
     *
     * @param split String array which contains the index of the task to be deleted.
     * @return the string form of the task deleted.
     * @throws DukeException
     */
    String delete(String[] split) throws DukeException {
        if (split.length < 2 || split[1].isEmpty()) {
            throw new NoNumberException("☹ OOPS!!! No task number was given in the input");
        }
        return ui.deleteTask(Integer.parseInt(split[1]));
    }

    /**
     * method to search for similar tasks in the list.
     *
     * @param split String array containing the expression to be matched.
     * @return list of matching tasks in String form.
     * @throws DukeException
     */
    String find(String[] split) throws DukeException {
        if (split.length < 2) {
            throw new InvalidInputException("☹ OOPS!!! No task was given in the input");
        }
        return ui.find(split[1]);
    }

    /**
     * mehtod to create a task of type todo.
     *
     * @param split String array containing the todo task details.
     * @return the String form of the task added.
     * @throws DukeException
     */
    String createTodo(String[] split) throws DukeException {
        if (split.length < 2) {
            throw new NoDescriptionException("☹ OOPS!!! The description of a " + split[0] + " cannot be empty.");
        }
        return ui.createTodo(split[1]);
    }

    /**
     * method to create a task of type event.
     *
     * @param split String array containing the event task details.
     * @return the String form of the task added.
     * @throws DukeException
     */
    String createEvent(String[] split) throws DukeException {
        if (split.length < 2) {
            throw new NoDescriptionException("☹ OOPS!!! The description of a " + split[0] + " cannot be empty.");
        }
        String time = split[1].split(" /at ", 2)[1];
        String task = split[1].split(" /at ", 2)[0];
        return ui.createEvent(task, time);
    }

    /**
     * method to create a task of type deadline.
     *
     * @param split String array containing the deadline task details
     * @return the String form of the task added.
     * @throws DukeException
     */
    String createDeadline(String[] split) throws DukeException {
        if (split.length < 2) {
            throw new NoDescriptionException("☹ OOPS!!! The description of a " + split[0] + " cannot be empty.");
        }
        String time = split[1].split(" /by ", 2)[1];
        String task = split[1].split(" /by ", 2)[0];
        return ui.createDeadline(task, time);
    }

    /**
     * method to update the given task.
     *
     * @param split String array containing the index and new details of the task to be changed.
     * @return the String form of the task updated.
     * @throws DukeException
     */
    String update(String[] split) throws DukeException {
        String task = split[1].split(" /to ", 2)[0];
        String time = split[1].split(" /to ", 2)[1];
        return ui.update(task, time);
    }

}
