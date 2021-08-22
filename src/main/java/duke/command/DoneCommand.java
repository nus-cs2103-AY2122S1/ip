package duke.command;

import duke.TaskList;
import duke.Ui;

public class DoneCommand extends Command{
    private TaskList myTasks;
    private String next;
    public DoneCommand(TaskList myTasks, String next) {
        this.myTasks = myTasks;
        this.next = next;
    }
    @Override
    public void execute() {
        if (next.length() > 5) {
            myTasks.getDone(next);
        } else {
            Ui.myPrint("☹ OOPS!!! Please enter a valid number, such as done 3");
        }
    }
}
