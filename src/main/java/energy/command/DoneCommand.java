package energy.command;

import java.io.IOException;

import javafx.util.Pair;

import energy.result.Result;
import energy.result.TaskList;
import energy.util.Storage;
import energy.util.Ui;

/**
 * A class that represents the command when the user wants to mark a task as done.
 */
public class DoneCommand extends Command {

    /**
     * Creates a DoneCommand, which relies on user inputs to function.
     *
     * @param input A string containing the user's input.
     */
    public DoneCommand(String input) {
        super(input);
    }

    /**
     * Marks a specified task as done and saves the new task list to the save file.
     *
     * @param taskList   The current list of tasks from the user.
     * @param ui      An object that handles all UI related functionality. (e.g. printing)
     * @param storage An object that handles all save/load related functionality.
     * @return A Result object containing task and alias data, as well as an output message.
     * @throws IOException If an error occurs during the save operation.
     */
    @Override
    public Result execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        // This pair contains the updated task list along with an output message
        Pair<TaskList, String> taskListPair = taskList.markTask(ui, input);
        TaskList newTaskList = taskListPair.getKey();
        storage.saveTasksToFile(newTaskList);
        String message = taskListPair.getValue();
        return new Result(newTaskList, message);
    }
}
