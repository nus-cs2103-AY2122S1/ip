package duke;

/**
 * Implements Parser object that deals with making sense of the user command.
 */
public class Parser {
    /**
     * Handles the logic of the user command.
     * @param input The user input.
     * @param ui The Ui object.
     * @param storage The Storage object.
     * @param tasks The list of tasks.
     * @return A boolean deciding whether to exit Duke.
     */
    public static String parse(String input, Ui ui, Storage storage, TaskList tasks) {
        if (input.equals("bye")) {
            return ui.exit();
        }
        try {
            if (input.equals("list")) {
                return ui.displayList(tasks);
            } else if (input.contains("done")) {
                return tasks.handleDone(input, storage, ui);
            } else if (input.contains("todo")) {
                return tasks.handleTodo(input, storage, ui);
            } else if (input.contains("deadline")) {
                return tasks.handleDeadline(input, storage, ui);
            } else if (input.contains("event")) {
                return tasks.handleEvent(input, storage, ui);
            } else if (input.contains("delete")) {
                return tasks.handleDelete(input, storage, ui);
            } else if (input.contains("find")) {
                return tasks.handleFind(input, ui);
            } else {
                System.out.println("can type properly pls?");
                return "can type properly pls?";
            }
        } catch (DukeException err) {
            ui.showLoadingError(err);
        }
        return "say again?";
    }
}
