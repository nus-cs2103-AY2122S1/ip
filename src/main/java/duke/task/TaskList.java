package duke.task;

import java.util.ArrayList;

public class TaskList {
    public ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tl) {
        tasks = tl;
    }

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int index) {
        tasks.remove(index);
    }

    public ArrayList<Task> getTaskList() {
        return tasks;
    }

    public int getSize() {
        return tasks.size();
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public TaskList searchList(String target) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task t : tasks) {
            if (t.toString().contains(target)) {
                foundTasks.add(t);
            }
        }
        return new TaskList(foundTasks);
    }
}