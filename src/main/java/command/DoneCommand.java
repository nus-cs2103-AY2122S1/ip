package command;

import duke.Duke;
import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class DoneCommand extends Command {

    protected DoneCommand(String args) {
        super(args);
    }

    @Override
    public String execute(TaskList tasklist, Ui ui, Storage store, Duke bot) {
        try {
            tasklist.doneTask(Integer.valueOf(this.args));
            return tasklist.printTasks();
        } catch (NumberFormatException e) {
            return "please input a number";
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
