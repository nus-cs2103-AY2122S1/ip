package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents how Duke responds to a "find" command.
 *
 * @author Javier Phon Zhee Kai.
 * @version CS2103T AY21/22 Sem 1.
 */
public class FindCommand extends Command {

    public FindCommand(String userInput) {
        super(userInput);
    }

    /**
     * Executes a "find" command.
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
            //first check for empty keyword
            return ui.showError("Error! Please search for a keyword.");
        } else {
            String keyword = command.substring(inputValues[0].length() + 1).strip();
            return taskList.searchAndDisplay(keyword);
        }
    }
}
