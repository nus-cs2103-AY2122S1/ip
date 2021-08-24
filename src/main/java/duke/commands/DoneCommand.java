package duke.commands;

import java.io.IOException;

import duke.storage.Storage;
import duke.storage.TaskList;

public class DoneCommand extends Command {
    private final String taskId;

    /**
     * Primary Constructor.
     * 
     * @param taskId is the task that will be updated as done in the list of tasks.
     */
    public DoneCommand(String taskId) {
        this.taskId = taskId;
    }

    /**
     * Helps to update the task in the list of tasks as done when this function is
     * executed.
     * 
     * @param taskList the list of Tasks which is being stored.
     * @param storage  the database where the Tasks are being saved for progression.
     */
    @Override
    public void execute(TaskList taskList, Storage storage) {
        try {
            taskList.doneItem(this.taskId);
            storage.updateDone(this.taskId);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Checks if the user wants to exit from the application.
     * 
     * @return boolean whether the user wants to exit from the application.
     */
    @Override
    public boolean getIsExit() {
        return false;
    }
}