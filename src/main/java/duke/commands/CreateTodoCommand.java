package duke.commands;

import duke.data.TaskList;
import duke.data.tasks.ToDos;
import duke.ui.Ui;
import duke.storage.Storage;

/**
 * Creates and adds a ToDoTask
 */
public class CreateTodoCommand extends Command {
    private final String name;

    /**
     * @param name The raw input from the user
     */
    public CreateTodoCommand(String userInput) {
        this.name = userInput.substring(5);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printMsg(tasks.addToList(new ToDos(this.name)));
        storage.write(tasks.getSaveData());
    }
}
