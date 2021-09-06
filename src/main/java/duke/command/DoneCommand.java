package duke.command;

import java.io.IOException;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents how Duke responds to a "done" command.
 *
 * @author Javier Phon Zhee Kai.
 * @version CS2103T AY21/22 Sem 1.
 */
public class DoneCommand extends Command {
    /**
     * Constructor of the DoneCommand class.
     *
     * @param userInput A string representing the user's input.
     */
    public DoneCommand(String userInput) {
        super(userInput);
    }

    /**
     * Returns a string representing Duke's response to a "Done" command.
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
        String[] inputValues = super.getUserInput().split(" ");
        try {
            int index = Integer.parseInt(inputValues[1]);
            return taskList.markAsDone(index, ui, storage);
        } catch (NumberFormatException e) {
            return ui.showInvalidFormatError(this);
        } catch (IndexOutOfBoundsException e) {
            int index = Integer.parseInt(inputValues[1]);
            return ui.showInvalidIndexError(index);
        } catch (IOException exception) {
            return ui.showSavingError();
        }
    }
}
