package duke.command;

import java.io.IOException;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents how Duke responds to a "delete" command.
 *
 * @author Javier Phon Zhee Kai.
 * @version CS2103T AY21/22 Sem 1.
 */
public class DeleteCommand extends Command {
    /**
     * Constructor of the DeleteCommand class.
     *
     * @param userInput A string representing the user's input.
     */
    public DeleteCommand(String userInput) {
        super(userInput);
    }

    /**
     * Executes a "delete" command.
     *
     * @param taskList The taskList where all tasks are stored.
     * @param ui An instance of the Ui class that is responsible for Duke's user interactions.
     * @param storage An instance of a the Storage class that saves and loads Duke's data.
     * @return A string representing Duke's reply after executing this command.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        String[] inputValues = super.getUserInput().split(" ");
        try {
            return taskList.delete(Integer.parseInt(inputValues[1]), storage);
        } catch (NumberFormatException e) {
            return ui.showError("Error! Please ensure a number is entered after delete (eg: delete 2)");
        } catch (IndexOutOfBoundsException e) {
            if (Integer.parseInt(inputValues[1]) <= 0) {
                return ui.showError("Error! Please specify a number greater than 0");
            } else if (Integer.parseInt(inputValues[1]) == 1) {
                return ui.showError("Error! You do not have any tasks in the list");
            } else {
                return ui.showError("Error! You do not have " + inputValues[1] + " tasks in the list");
            }
        } catch (IOException exception) {
            return ui.showSavingError();
        }
    }
}
