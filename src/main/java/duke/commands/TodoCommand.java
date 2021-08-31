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
    /**
     * The command word that identifies a DeleteCommand instance.
     */
    public static final String COMMAND_WORD = "todo";

    /**
     * Guide on how to use this command word.
     */
    public static final String MESSAGE_USAGE =
            COMMAND_WORD + " <description> - add a todo item\n" + "   Example: " + COMMAND_WORD + " read book";

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
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            if (userCommand.length() < 5) {
                throw new IllegalArgumentException("Please add a description for your todo!");
            }

            Todo newTodo = new Todo(userCommand.substring(5));
            tasks.addTask(newTodo);
            storage.save(tasks.getItems());

            ui.printTaskAdded(newTodo, tasks.getSize());
        } catch (IOException | IllegalArgumentException e) {
            ui.printError(e.getMessage());
        }

    }
}
