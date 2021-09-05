package duke.commands;

import java.io.IOException;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Todo;

/**
 * Adds a todo task to the task list.
 */
public class TodoCommand extends Command {
    /** The command word that identifies a DeleteCommand instance. */
    public static final String COMMAND_WORD = "todo";

    /** Length of the command word. */
    public static final int COMMAND_LENGTH = COMMAND_WORD.length();

    /** Guide on how to use this command word. */
    public static final String MESSAGE_USAGE = COMMAND_WORD + " <description> - add a todo item\n"
            + "    📍 Example: " + COMMAND_WORD + " read book";

    private static final String MISSING_DESC_ERR = "Please add a description for your todo!";

    private String userCommand;

    /**
     * Instantiates TodoCommand object.
     *
     * @param userCommand Full user input.
     */
    public TodoCommand(String userCommand) {
        super();
        this.userCommand = userCommand;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            if (userCommand.length() <= COMMAND_LENGTH) {
                throw new IllegalArgumentException(MISSING_DESC_ERR);
            }

            String description = userCommand.substring(COMMAND_LENGTH).strip();
            Todo newTodo = new Todo(description);
            tasks.addTask(newTodo);
            storage.save(tasks.getItems());

            return ui.printTaskAdded(newTodo, tasks.getSize());

        } catch (IOException | IllegalArgumentException e) {
            return ui.printError(e.getMessage());
        }
    }
}
