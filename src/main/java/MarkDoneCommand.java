/**
 * This command marks a task in the task list as done.
 *
 */
public class MarkDoneCommand implements Command {

    // The index of the task to be marked as done
    int index;

    MarkDoneCommand(int index) {
        this.index = index;
    }

    public void execute(Ui ui, TaskList taskList, Storage storage) {
        Task t = taskList.getTask(this.index);
        taskList.getTask(this.index).markAsDone();
        ui.printTaskDone(t, this.index);
    }

    public boolean isQuit() {return false;}
}
