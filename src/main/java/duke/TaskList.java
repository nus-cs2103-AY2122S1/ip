package duke;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        if (tasks != null) {
            this.tasks = tasks;
        }
        else {
            this.tasks = new ArrayList<>();
        }
    }

    public int getSize() {
        return tasks.size();
    }

    public Task getTask(int taskNum) {
        return tasks.get(taskNum - 1);
    }

    public void addTask(Task newTask) {
        tasks.add(newTask);
    }

    public Task completeTask(int taskNum) {
        tasks.get(taskNum - 1).setDone(true);
        return tasks.get(taskNum - 1);
    }

    public Task deleteTask(int taskNum) {
        return tasks.remove(taskNum - 1);
    }
}
