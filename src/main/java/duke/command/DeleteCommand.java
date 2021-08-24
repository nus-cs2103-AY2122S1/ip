package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import java.io.IOException;

public class DeleteCommand extends Command {

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        String[] inputValues = ui.getCommand().split(" ");
        try {
            taskList.delete(Integer.parseInt(inputValues[1]));
            storage.save(taskList);
        } catch (NumberFormatException e) {
            ui.showError("     Error! Please ensure a number is entered after delete (eg: delete 2)");
        } catch (IndexOutOfBoundsException e) {
            if (Integer.parseInt(inputValues[1]) <= 0) {
                ui.showError("     Error! Please specify a number greater than 0");
            } else if (Integer.parseInt(inputValues[1]) == 1) {
                ui.showError("     Error! You do not have any tasks in the list");
            } else {
                ui.showError("     Error! You do not have " + inputValues[1] + " tasks in the list");
            }
        } catch (IOException exception) {
            ui.showSavingError();
        }
    }
}
