package katheryne.command;

import katheryne.KatheryneException;
import katheryne.Storage;
import katheryne.TaskList;
import katheryne.Ui;
import katheryne.task.Todo;

public class TodoCommand extends Command {
    /**
     * The constant name to refer to this command by
     */
    public static final String COMMAND = "TODO";
    private final String description;

    /**
     * Constructs the EventCommand by putting the processedRemainingText into appropriate variables.
     *
     * @param processedRemainingText The first item is the description; the second is the time the event is
     * @throws KatheryneException If the date is in the wrong format
     */
    TodoCommand(String[] processedRemainingText) {
        this.description = processedRemainingText[0];
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws KatheryneException {
        Todo todo = new Todo(description);
        taskList.add(todo);
        ui.say("Todo '" + description + "' added to your list");
        ui.countTasksInList(taskList);
    }

}
