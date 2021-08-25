package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents how Duke responds to a "find" command.
 *
 * @author Javier Phon Zhee Kai.
 * @version CS2103T AY21/22 Sem 1.
 */
public class FindCommand extends Command {

    /**
     * Returns a boolean that tells Duke if this is the command to exit.
     *
     * @return A boolean representing the exit condition.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes a "find" command.
     *
     * @param taskList The taskList where all tasks are stored.
     * @param ui An instance of the Ui class that is responsible for Duke's user interactions.
     * @param storage An instance of a the Storage class that saves and loads Duke's data.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        String command = ui.getCommand();
        String[] inputValues = command.split(" ");
        if (inputValues.length == 1) {
            //first check for empty keyword
            ui.showError("     Error! Please search for a keyword.");
        } else {
            String keyword = command.substring(inputValues[0].length() + 1).strip();
            taskList.searchAndDisplay(keyword);
        }
    }
}
