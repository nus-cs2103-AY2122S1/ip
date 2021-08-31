package duke.command;

import duke.exception.DukeException;
import duke.exception.InvalidFormatException;
import duke.exception.InvalidIntegerException;
import duke.exception.InvalidTaskNumberException;
import duke.Storage;
import duke.TaskList;
import duke.tasks.Task;
import duke.Ui;

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

    public Action(int type, String[] words) {
        this.type = type == 0 ? Type.DONE : Type.DELETE;
        this.words = words;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (this.words.length != 2) {
            throw new InvalidFormatException("`" + this.type.toString().toLowerCase() + " ${i}`");
        } else {
            int index;
            try {
                index = Integer.parseInt(this.words[1]);
            } catch (NumberFormatException ex) {
                throw new InvalidIntegerException();
            }
            if (index < 1 || index > taskList.getSize()) {
                throw new InvalidTaskNumberException();
            } else {
                if (this.type == Type.DONE) {
                    Task t = taskList.get(index - 1);
                    t.markAsDone();
                    ui.print("Nice, I've marked this task as done!\n   " 
                            + t.toString());
                } else if (this.type == Type.DELETE) {
                    Task t = taskList.remove(index - 1);

                    String plurality = " task";
                    if (taskList.getSize() != 1) {
                        plurality += "s";
                    }

                    ui.print("Noted, I've removed this task:\n   " 
                            + t.toString() + "\nNow you have " + taskList.getSize()
                            + plurality + " in the list.");
                }
            }
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
