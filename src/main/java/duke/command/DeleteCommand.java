package duke.command;

import duke.Ui;
import duke.Storage;
import duke.notes.NotesList;
import duke.tasks.TaskList;
import duke.tasks.Task;

import java.util.ArrayList;

/**
 * Deletes a task from the task manager in response to the user's input.
 */
public class DeleteCommand extends Command {
    private String taskNumber;

    /**
     * Instantiates an object of the DeleteCommand class.
     *
     * @param command User's input.
     */
    public DeleteCommand(String command) {
        this.taskNumber = command;
    }

    /**
     * Executes the action of deleting a task from the task manager Peppa.
     *
     * @param tasks List of tasks stored in the task manager.
     * @param notes List of notes stored in the task manager.
     * @param ui User interface of the task manager.
     * @param storage Hard disk containing all the tasks and notes of the task manager.
     * @return Message to be printed on the user interface to notify the user of the outcome of the input entered.
     */
    @Override
    public String execute(TaskList tasks, NotesList notes, Ui ui, Storage storage) {
        try {
            Integer number = Integer.valueOf(this.taskNumber);
            if(number < 0 || number > tasks.getNumberOfTasks()) {
                return ui.showError("You have entered a index that does not correspond to any task.");
            }
            ArrayList<Task> originalTaskList = tasks.getTasks();
            Task task = tasks.getTask(number - 1);
            tasks.getTasks().remove(number - 1);
            storage.rewriteFile(tasks.getTasks(), notes.getNotes());
            return ui.respondToDelete(tasks.getTasks(), task);
        } catch(NumberFormatException e) {
            return ui.showError("You have entered an invalid input that is not a number.");
        }

    }

    /**
     * Returns a boolean value indicating if user wants to exit the task manager.
     *
     * @return Boolean value.
     */
    @Override
    public Boolean isExit() {
        return false;
    }
}
