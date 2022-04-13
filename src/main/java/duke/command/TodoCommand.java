package duke.command;

import java.io.IOException;

import duke.exception.DukeException;
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
    /**
     * Constructor of the TodoCommand class.
     *
     * @param userInput A string representing the user's input.
     */
    public TodoCommand(String userInput) {
        super(userInput);
    }

    /**
     * Returns a string representing Duke's response to a "todo" command.
     *
     * @param taskList The taskList where all tasks are stored.
     * @param ui An instance of the Ui class that is responsible for Duke's user interactions.
     * @param storage An instance of a the Storage class that saves and loads Duke's data.
     * @return A string representing Duke's reply after executing this command.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        assert taskList != null;
        assert storage != null;
        String command = super.getUserInput();
        String[] inputValues = command.split(" ");
        if (inputValues.length == 1) {
            //catch empty to-do
            return ui.showEmptyFieldError(this);
        }
        try {
            String description = command.substring(inputValues[0].length() + 1);
            Task toDo = new ToDo(description);
            return taskList.add(toDo, ui, storage);
        } catch (IOException exception) {
            return ui.showSavingError();
        } catch (DukeException exception) {
            return ui.showError(exception.getMessage());
        }
    }
}
