package duke.command;

import duke.TaskList;

public class ListCommand extends Command{
    private TaskList myTasks;
    public ListCommand(TaskList myTasks) {
        super();
        this.myTasks = myTasks;
    }
    @Override
    public void execute() {
        myTasks.printTaskList();
    }
}
