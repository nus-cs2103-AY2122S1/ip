package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;

public class AddCommand extends Command{
    private TaskList myTasks;
    private String next;

    public AddCommand(TaskList myTasks, String next) {
        this.myTasks = myTasks;
        this.next = next;
    }

    @Override
    public void execute() {
        try {
            myTasks.addTask(next);
        } catch (DukeException dukeException) {
            Ui.showError(dukeException);
        }
    }
}
