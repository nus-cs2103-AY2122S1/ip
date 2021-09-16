package katheryne.command;

import katheryne.KatheryneException;
import katheryne.Storage;
import katheryne.TaskList;
import katheryne.Ui;
import katheryne.task.Todo;

/**
 * A command to add Todos to the task list.
 */
public class TodoCommand extends Command {
    /**
     * The constant name to refer to this command by
     */
    public static final String COMMAND = "TODO";
    private final String description;

    /**
     * Constructs the Todo Command by putting the processedRemainingText into appropriate variables.
     *
     * @param processedRemainingText The first item is the description; the second is the time the event is
     * @throws KatheryneException If the date is in the wrong format
     */
    TodoCommand(String[] processedRemainingText) {
        this.description = processedRemainingText[0];
    }

    /**
     * Create a Todo and add it to the task list. Print a confirmation message
     *
     * @param taskList A container for tasks which contains Katheryne's tasks.
     * @param ui The Ui used for the user interface.
     * @param storage The storage object taking care of writing and reading the text file.
     * @throws KatheryneException
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws KatheryneException {
        Todo todo = new Todo(description);
        taskList.add(todo);
        ui.say("Todo '" + description + "' added to your list");
        ui.countTasksInList(taskList);
    }

}
