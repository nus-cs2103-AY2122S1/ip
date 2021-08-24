package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class FindCommand extends Command {

    @Override
    public boolean isExit() {
        return false;
    }

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
