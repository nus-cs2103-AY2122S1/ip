package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;
import duke.task.Task;
import duke.task.ToDo;
import java.io.IOException;

/**
 * Represents how Duke responds to a "todo" command.
 *
 * @author Javier Phon Zhee Kai.
 * @version CS2103T AY21/22 Sem 1.
 */
public class TodoCommand extends Command {

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
     * Executes a "todo" command.
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
            //catch empty to-do
            ui.showError("     Error! Description cannot be empty.");
        } else {
            try {
                String description = command.substring(inputValues[0].length() + 1);
                Task toDo = new ToDo(description);
                taskList.add(toDo);
                storage.save(taskList);
            } catch (IOException exception) {
                ui.showSavingError();
            }
        }
    }
}
