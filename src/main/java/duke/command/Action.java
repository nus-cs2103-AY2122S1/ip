package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.UiInterface;
import duke.exception.DukeException;
import duke.exception.InvalidFormatException;
import duke.exception.InvalidIntegerException;
import duke.exception.InvalidTaskNumberException;
import duke.tasks.Task;

/**
 * Class that handles the Done and Delete command
 */
public class Action extends Command {

    private enum Type {
        DONE,
        DELETE
    }

    private final Type type;
    private final String[] words;

    /**
     * Constructs an Action object for Done and Delete commands.
     *
     * @param type 0 for Done and 1 for Delete
     * @param words User input split by whitespace
     */
    public Action(int type, String[] words) {
        assert type == 0 || type == 1;
        this.type = type == 0 ? Type.DONE : Type.DELETE;
        this.words = words;
    }

    /**
     * Validates the input for Done and Delete commands and
     * executes the command
     *
     * @param taskList Current list of tasks
     * @param ui Ui to interact with user
     * @param storage Storage that allows loading/saving
     * @throws DukeException if an error is encountered
     */
    @Override
    public void execute(TaskList taskList, UiInterface ui, Storage storage) throws DukeException {
        if (this.words.length != 2) { // Guard Clause
            throw new InvalidFormatException("`" + this.type.toString().toLowerCase() + " ${i}`");
        }
        
        int index;
        try {
            index = Integer.parseInt(this.words[1]);
        } catch (NumberFormatException ex) {
            throw new InvalidIntegerException();
        }
        
        if (index < 1 || index > taskList.getSize()) {
            throw new InvalidTaskNumberException();
        }
        
        if (this.type == Type.DONE) {
            Task t = taskList.getTask(index - 1);
            t.markAsDone();
            ui.print("Nice, I've marked this task as done!\n   "
                    + t.toString());
        } else if (this.type == Type.DELETE) {
            Task t = taskList.removeTask(index - 1);

            String plurality = " task";
            if (taskList.getSize() != 1) {
                plurality += "s";
            }

            ui.print("Noted, I've removed this task:\n   "
                    + t.toString() + "\nNow you have " + taskList.getSize()
                    + plurality + " in the list.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
