package duke.command;

import duke.task.Task;
import duke.tasklist.TaskList;

/**
 * This interface allows a contract to be created to return the result of adding a task to a TaskList.
 */
public interface TaskListAddable {

    String addTaskToTaskList(TaskList taskList, Task task);

}
