package duke;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task delete(int index) {
        return tasks.remove(index);
    }

    public int getSize() {
        return tasks.size();
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public TaskList findMatchingTasks(String keyword) {
        ArrayList<Task> result = new ArrayList<>();
        for(int i = 0; i < tasks.size(); i++) {
            Task temp = tasks.get(i);
            if (tasks.get(i).filterByKeyword(keyword)) {
                result.add(temp);
            }
        }
        return new TaskList(result);
    }
}
