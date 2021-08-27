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
    public static boolean parse(String input, Ui ui, Storage storage, TaskList tasks) {
        if (input.equals("bye")) {
            ui.exit();
            return true;
        } else {
            try {
                if (input.equals("list")) {
                    ui.displayList(tasks);
                } else if (input.contains("done")) {
                    tasks.done(input, storage, ui);
                } else if (input.contains("todo")) {
                    tasks.todo(input, storage, ui);
                } else if (input.contains("deadline")) {
                    tasks.deadline(input, storage, ui);
                } else if (input.contains("event")) {
                    tasks.event(input, storage, ui);
                } else if (input.contains("delete")) {
                    tasks.delete(input, storage, ui);
                } else {
                    System.out.println("can type properly pls");
                }
            } catch (DukeException err) {
                ui.showLoadingError(err);
            }
            return false;
        }
    }
}
