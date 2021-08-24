package duke.command;

import duke.task.Task;
import duke.TaskList;

public interface TaskListAddable {

    String addTaskToTaskList(TaskList taskList, Task task);

}
