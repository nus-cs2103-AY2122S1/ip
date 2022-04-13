package duke.command;

import duke.Ui;
import duke.Storage;
import duke.notes.NotesList;
import duke.tasks.TaskList;

/**
 * Indicates a task as done in response to the user's input.
 */
public class DoneCommand extends Command {
    private String taskNumber;

    /**
     * Instantiates an object of the DoneCommand class.
     *
     * @param command User's input.
     */
    public DoneCommand(String command) {
        this.taskNumber = command;
    }

    /**
     * Executes the action of marking a task as done.
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
            Integer count = Integer.valueOf(this.taskNumber);
            if(count <= 0 || count > tasks.getNumberOfTasks()) {
                return ui.showError("You have entered a index that does not correspond to any task.");
            }
            tasks.getTask(count - 1).markAsDone();
            storage.rewriteFile(tasks.getTasks(), notes.getNotes());
            return ui.respondToDone(tasks.getTasks(), count);
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
