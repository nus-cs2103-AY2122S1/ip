package duke.tasks;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> items;

    public TaskList() {
        this.items = new ArrayList<>();
    }

    public void addTask(Task task) {
        this.items.add(task);
    }

    public void deleteTask(int id) {
        this.items.remove(id);
    }

    public void markTaskAsComplete(int id) {
        this.items.get(id).markAsComplete();
    }

    public int getTaskCount() {
        return this.items.size();
    }

    public Task getTask(int id) {
        return this.items.get(id);
    }

    /**
     * Returns a TaskList containing all the tasks that contains the specified keyword.
     *
     * @param keyword The word used for the search.
     * @return A TaskList contating all the tasks that contains the specified keyword.
     */
    public TaskList searchTaskByKeyword(String keyword) {
        TaskList resultList = new TaskList();

        for (Task task : items) {
            String taskToStr = task.toString().toLowerCase();

            if (taskToStr.contains(keyword.trim().toLowerCase())) {
                resultList.addTask(task);
            }
        }

        return resultList;
    }
}

