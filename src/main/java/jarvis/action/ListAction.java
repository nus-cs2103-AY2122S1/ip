package jarvis.action;

import jarvis.output.Output;
import jarvis.task.TaskList;

public class ListAction extends Action {
    @Override
    public void execute(TaskList taskList) {
        Output.showTaskList(taskList);
    }
}
