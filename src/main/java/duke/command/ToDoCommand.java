package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;
import duke.task.ToDo;

/**
 * Represents the command when the user wants to add a todo task to the
 * task list.
 */
public class ToDoCommand extends Command {

    public static final String COMMAND_WORD = "todo";

    /** description of the todo task to be added. */
    private String description;

    /**
     * Constructs a ToDoCommand object.
     *
     * @param description the user's input for the description of the todo
     *                    task to be added.
     */
    public ToDoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the command to add a todo task to the tasklist.
     *
     * @param tasks The user's task list.
     * @param storage Storage object used to save the tasklist to the data file.
     * @return A message informing the user that the todo has been added and the
     * total number of tasks he has now.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        ToDo newTodo = new ToDo(description);
        tasks.addTask(newTodo);
        storage.save(tasks);
        return Ui.getAddedMessage(newTodo, tasks.size());
    }
}
