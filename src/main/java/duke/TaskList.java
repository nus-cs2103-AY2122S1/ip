package duke;

import duke.exceptions.TaskOutOfRangeException;
import duke.tasks.Task;

import java.util.ArrayList;

/**
 * Contains the task list.
 * Includes operations to add/delete duke.tasks in the list.
 */
public class TaskList {
    ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }
    
    public Task getTask(int index) {
        return this.taskList.get(index);
    }

    public int getLength() {
        return this.taskList.size();
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void deleteTask(int taskNo) throws TaskOutOfRangeException {
        if (taskNo < 1 || taskNo > taskList.size()) {
            throw new TaskOutOfRangeException(taskList.size());
        } else {
            taskList.remove(taskNo - 1);
        }
    }

    public boolean taskDone(int taskNo) throws TaskOutOfRangeException {
        if (taskNo < 1 || taskNo > taskList.size()) {
            throw new TaskOutOfRangeException(taskList.size());
        } else {
            Task task = this.taskList.get(taskNo - 1);
            if (task.getCompletionStatus()) {
                return true;
            } else {
                task.taskDone();
                taskList.set(taskNo - 1, task);
                return false;
            }
        }

    }

}
