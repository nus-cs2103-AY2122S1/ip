package lifeline.task;

import java.util.ArrayList;

import lifeline.exception.LifelineException;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public void add(Task task) {
        taskList.add(task);
    }

    public Task get(int index) {
        return taskList.get(index);
    }

    public int size() {
        return taskList.size();
    }

    public Task deleteTask(int index) {
        Task taskToDelete = taskList.get(index);
        taskList.remove(index);
        return taskToDelete;
    }

    public Task completeTask(int index) throws LifelineException {
        Task taskToMarkAsComplete = taskList.get(index);
        if (taskToMarkAsComplete.isDone()) {
            // wrong output due to refactor
            throw new LifelineException("Task is already done!");
        }
        taskToMarkAsComplete.setDone(true);
        return taskToMarkAsComplete;
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }
}
