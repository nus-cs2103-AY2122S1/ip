package duke.commands;

import duke.tasks.ToDos;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

/**
 * The command indicating that the user wants to add in a ToDos into the task list.
 */
public class ToDoCommand extends Command {
    private String description;

    /**
     * Constructor for ToDoCommand.
     *
     * @param description the description of the ToDos to be added.
     */
    public ToDoCommand(String description) {
        this.description = description;
    }

    /**
     * Performs the actions that adds the ToDos to the task list.
     *
     * @param tasks the full task list containing all the tasks.
     * @param ui the ui instance.
     * @param storage the storage instance.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ToDos newToDo = new ToDos(this.description, false);
        tasks.addTask(newToDo);
        ui.showTaskAddedInteraction(newToDo, tasks);
        storage.addTaskToPersistedData(newToDo);
    }
}
