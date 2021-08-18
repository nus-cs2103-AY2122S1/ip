package jarvis.action;

import jarvis.exception.JarvisException;
import jarvis.output.Output;
import jarvis.task.TaskList;

public class ListAction extends Action {
    @Override
    public void execute(TaskList taskList) throws JarvisException {
        if (taskList.getTaskListSize() == 0) {
            throw new JarvisException("Your list is currently empty! Try adding some tasks.");
        }
        Output.showTaskList(taskList);
    }
}
