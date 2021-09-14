package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.exception.DukeException;
import duke.exception.InvalidIndexException;

/**
 * Represents a Command to mark a Task as 'done' on the TaskList.
 */
public class DoneCommand extends Command {

    private String[] strIndexes;
    private int validCount = 0;
    private String reply = "Nice! I've marked these tasks as done:\n";
    private String invalid = "The following entries are invalid: ";
    private final String allValid = "There were no invalid entries entered.";

    /**
     * The constructor for a DoneCommand object.
     *
     * @param strIndexes The given indexes of the Tasks to be marked as 'done'.
     */
    public DoneCommand(String[] strIndexes) {
        this.strIndexes = strIndexes;
    }

    /**
     * Executes the Command to mark a Task as 'done'.
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
            tasks.doneTask(index);
            reply += tasks.getTask(index) + System.lineSeparator();
        }

        if (validCount == 0) {
            throw new InvalidIndexException();
        }

        storage.update(tasks);
        if (validCount == strIndexes.length) {
            return reply + allValid;
        } else {
            return reply + invalid;
        }
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
