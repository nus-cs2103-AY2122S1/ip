package data;

import task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task newTask) {
        tasks.add(newTask);
    }

    public void deleteTask(int oldTaskIndex) {
        tasks.remove(oldTaskIndex);
    }

    public void markTaskAsDone(Task task, int index) {
        tasks.get(index).markAsDone();
    }

    public int getSize() {
        return tasks.size();
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Filters through the TaskList and returns a new TaskList with tasks containing the keyword.
     *
     * @param keyword Keyword to be searched by
     * @return A new TaskList of tasks that contain the keyword.
     */
    public TaskList searchByKeyword(String keyword) {
        TaskList filteredTaskList = new TaskList();
        for (Task task : this.tasks) {
            if (task.toString().contains(keyword)) {
                filteredTaskList.addTask(task);
            }
        }
        return filteredTaskList;
    }
}
