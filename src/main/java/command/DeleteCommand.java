package command;

import duke.Duke;
import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class DeleteCommand extends Command {

    protected DeleteCommand(String args) {
        super(args);
    }

    @Override
    public String execute(TaskList tasklist, Ui ui, Storage store, Duke bot) {
        try {
            int index = Integer.valueOf(this.args);
            tasklist.deleteTask(index);
            return "Deleted task number " + index;
        } catch (NumberFormatException e) {
            return "please input a number";
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
