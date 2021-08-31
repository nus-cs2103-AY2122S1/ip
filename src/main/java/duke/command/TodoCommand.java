package duke.command;

import java.io.IOException;

import duke.task.Task;
import duke.task.ToDo;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents how Duke responds to a "todo" command.
 *
 * @author Javier Phon Zhee Kai.
 * @version CS2103T AY21/22 Sem 1.
 */
public class TodoCommand extends Command {

    public TodoCommand(String userInput) {
        super(userInput);
    }

    /**
     * Executes a "todo" command.
     *
     * @param taskList The taskList where all tasks are stored.
     * @param ui An instance of the Ui class that is responsible for Duke's user interactions.
     * @param storage An instance of a the Storage class that saves and loads Duke's data.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        String command = super.getUserInput();
        String[] inputValues = command.split(" ");
        if (inputValues.length == 1) {
            //catch empty to-do
            return ui.showError("Error! Description cannot be empty.");
        } else {
            try {
                String description = command.substring(inputValues[0].length() + 1);
                Task toDo = new ToDo(description);
                return taskList.add(toDo, storage);
            } catch (IOException exception) {
                return ui.showSavingError();
            }
        }
    }
}
