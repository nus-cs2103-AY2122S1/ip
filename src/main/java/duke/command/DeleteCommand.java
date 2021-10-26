package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.exception.DukeException;
import duke.exception.InvalidIndexException;

/**
 * Represents a Command to delete a Task from the TaskList.
 */
public class DeleteCommand extends Command {

    private String[] strIndexes;
    private int validCount = 0;
    private String reply = "Noted. I've removed these tasks:\n";
    private String invalid = "The following entries are invalid: ";

    /**
     * The constructor for a DeleteCommand object.
     *
     * @param strIndexes The given indexes of the Tasks to be deleted.
     */
    public DeleteCommand(String[] strIndexes) {
        this.strIndexes = strIndexes;
    }

    /**
     * Executes the Command to delete a Task.
     *
     * @param tasks The given TaskList to be updated.
     * @param storage The given Storage to save changes to.
     * @return The response to the user.
     * @throws DukeException When the given index does not exist.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        for (int i = 0; i < strIndexes.length; i++) {
            if (!isInt(strIndexes[i])) {
                invalid += strIndexes[i] + " ";
                continue;
            }

            int index = Integer.parseInt(strIndexes[i]) - 1;
            boolean isValidInt = index >= 0 && index <= tasks.getSize();
            if (!isValidInt) {
                invalid += strIndexes[i] + " ";
                continue;
            }

            validCount++;
            reply += tasks.getTask(index) + System.lineSeparator();
            tasks.deleteTask(index);
        }

        if (validCount == 0) {
            throw new InvalidIndexException();
        }

        storage.update(tasks);
        if (validCount == strIndexes.length) {
            return reply + displayNumOfTasks(tasks.getSize());
        } else {
            return reply + invalid + System.lineSeparator() + displayNumOfTasks(tasks.getSize());
        }
    }

    private static String displayNumOfTasks(int size) {
        return "Now you have " + size + " tasks in the list.";
    }

    private static boolean isInt(String str) {
        if (str == null) {
            return false;
        }

        try {
            Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }
}
