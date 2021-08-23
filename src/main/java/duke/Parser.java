package duke;

import java.util.List;

import duke.task.Task;

/**
 * Encapsulates the parsing-related functionality of Iris
 */
public class Parser {
    private static String getMetadata(String command) throws IrisException {
        String[] splitted = command.split(" ", 2);
        if (splitted.length == 1 || splitted[1].equals("")) {
            // TODO: make this error message more specific?
            throw new IrisException("The description cannot be empty");
        } else {
            return splitted[1];
        }
    }

    private static int parseInt(String text) throws IrisException {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException exception) {
            throw new IrisException("Please enter a valid integer");
        }
    }

    /**
     * Handles a given command
     *
     * @param command  raw text representing command
     * @param tasks TaskList object representing current list of tasks
     * @param ui       Ui object for current Duke instance
     * @throws IrisException for invalid commands
     */
    public static void handleCommand(String command, TaskList tasks, Ui ui) throws IrisException {
        handleCommand(command, tasks, ui, false);
    }

    /**
     * Handles a given command
     *
     * @param command  raw text representing command
     * @param tasks TaskList object representing current list of tasks
     * @param ui       Ui object for current Iris instance
     * @param silent   disables Ui if true
     * @throws IrisException for invalid commands
     */
    public static void handleCommand(String command, TaskList tasks, Ui ui, boolean silent) throws IrisException {
        if (command.equals("list")) {
            ui.listTasks(tasks);
        } else if (command.startsWith("done")) {
            Task task = tasks.done(parseInt(getMetadata(command)));
            if (!silent) ui.say(String.format("Good job! I've marked this task as done: %s", task));
        } else if (command.startsWith("delete")) {
            Task task = tasks.delete(parseInt(getMetadata(command)));
            if (!silent) {
                ui.say("Noted. I've removed this task:");
                ui.say(task.toString(), false);
                int count = tasks.getCount();
                ui.say(String.format("Now you have %d %s in the list.",
                        count, count == 1 ? "task" : "tasks"), false);
            }
        } else if (command.startsWith("todo")) {
            tasks.addTodo(getMetadata(command));
            if (!silent) {
                ui.sayTaskAdded(tasks);
            }
        } else if (command.startsWith("deadline")) {
            String[] splitted = getMetadata(command).split(" /by ");
            if (splitted.length != 2) {
                throw new IrisException("deadline should have 2 arguments: a name and a time");
            }
            tasks.addDeadline(splitted[0], splitted[1]);
            if (!silent) {
                ui.sayTaskAdded(tasks);
            }
        } else if (command.startsWith("event")) {
            String[] splitted = getMetadata(command).split(" /at ");
            if (splitted.length != 2) {
                throw new IrisException("event should have 2 arguments: a name and a time");
            }
            tasks.addEvent(splitted[0], splitted[1]);
            if (!silent) {
                ui.sayTaskAdded(tasks);
            }
        } else if (command.startsWith("find")) {
            String searchTerm = getMetadata(command);
            List<Task> searchResults = tasks.find(searchTerm);
            ui.listTasks(searchResults);
        } else {
            throw new IrisException("I'm sorry, but I don't know what that means.");
        }
    }
}
