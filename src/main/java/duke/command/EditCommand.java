package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * EditCommand class handles the commands that edit a particular task in the tasklist.
 */
public class EditCommand extends Command {

    private EditType type;
    private int taskNumber;

    public enum EditType {DELETE, DONE}

    /**
     * Constructs the EditCommand object.
     *
     * @param type Edit type of the command.
     * @param taskNumber Task number of the task to be edited.
     */
    public EditCommand(EditType type, int taskNumber) {
        this.type = type;
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (taskNumber < 1 || taskNumber > taskList.getSize()) {
            throw new DukeException("I cannot find this task number!\n");
        } else {
            switch (type) {
            case DONE: {
                Task completedTask = taskList.getTask(taskNumber - 1);
                completedTask.markAsDone();
                String message = "Good work! duke.task.Task is now marked as done:\n" + completedTask + "\n";
                ui.printMessage(message);
                storage.saveList(taskList.getTasks());
                break;
            }
            case DELETE: {
                Task deletedTask = taskList.removeTask(taskNumber - 1);
                String message = "Alright! I've deleted this task:\n" + deletedTask + taskList.getListStatus();
                ui.printMessage(message);
                storage.saveList(taskList.getTasks());
                break;
            }
            }
        }
    }


}
