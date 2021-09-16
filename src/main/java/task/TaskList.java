package task;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> t) {
        this.taskList = t;
    }

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    public void addTask(Task taskToAdd) {
        taskList.add(taskToAdd);
    }

    public Task deleteTask(int index) {
        return taskList.remove(index);
    }

    /**
     * Marks as task as completed.
     * @param index the index of the task in the list.
     */
    public void completeTask(int index) {
        Task taskToComplete = taskList.get(index);
        taskToComplete.markIsDone();
    }

    public Task getTask(int index) {
        return taskList.get(index);
    }

    public int size() {
        return taskList.size();
    }
}
