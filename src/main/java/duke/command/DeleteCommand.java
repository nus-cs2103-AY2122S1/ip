package duke.command;

import duke.data.DukeException;
import duke.data.Storage;
import duke.data.InformationList;
import duke.data.Ui;

/**
 * Command that deletes a Task from InformationList when executed.
 */
public class DeleteCommand extends Command {
    /** Index of the information to be deleted. */
    private int index;
    /** Integer to choose which list to delete from, 0 to delete a task, 1 to delete a contact*/
    private int listToDeleteFrom;

    /**
     * Constructs DeleteCommand class.
     *
     * @param index Index of task in InformationList that is to be deleted.
     * @param listToDeleteFrom Integer to specify which list of InformationList to delete from
     */
    public DeleteCommand(int index, int listToDeleteFrom) {
        this.index = index;
        this.listToDeleteFrom = listToDeleteFrom;
    }

    /**
     * Deletes a task from the InformationList.
     *
     * @param tasks The list of tasks that a user has.
     * @param ui The ui that sends a message to the user once the task is deleted.
     * @param storage Saves the updated InformationList to disk.
     * @return The message produced by ui.
     */
    @Override
    public String execute(InformationList tasks, Ui ui, Storage storage) {
        if (this.listToDeleteFrom == 0) {
            if (tasks.getTasksSize() == 0) {
                throw new DukeException("There are no Tasks to be deleted!");
            }
            if (index > tasks.getTasksSize() || index < 0) {
                throw new DukeException("Please insert a valid Task Number!");
            }
            tasks.deleteTask(index);
        } else {
            if (tasks.getContactsSize() == 0) {
                throw new DukeException("There are no Contacts to be deleted!");
            }
            if (index > tasks.getContactsSize() || index < 0) {
                throw new DukeException("Please insert a valid Contact Number!");
            }
            tasks.deleteContact(index);
        }
        storage.save(tasks);
        return ui.showDeletedInformation();
    }
}
