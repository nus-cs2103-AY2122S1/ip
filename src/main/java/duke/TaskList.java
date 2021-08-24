package duke;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public Task getTask(int i) {
        return tasks.get(i);
    }

    public void setTaskAsDone(int i) {
        tasks.get(i).setDone();
    }

    public void deleteTask(int i) {
        tasks.remove(i);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public int getSize() {
        return tasks.size();
    }

    public String getAllTasksString() {
        StringBuilder result = new StringBuilder("Here are the tasks in your list:\n");
        int taskCount = tasks.size();
        for (int i = 0; i < taskCount; i++) {
            result.append((i + 1) + ". " + tasks.get(i).toString() + "\n");
        }
        return result.toString();
    }

    public String getMatchingTasksString(String keyword) {
        ArrayList<Task> matchingTaskList = new ArrayList<>(tasks);
        matchingTaskList.removeIf(task -> !task.getDescription().contains(keyword));
        StringBuilder result = new StringBuilder("Here are the tasks in your list:\n");
        int taskCount = matchingTaskList.size();
        for (int i = 0; i < taskCount; i++) {
            result.append((i + 1) + ". " + matchingTaskList.get(i).toString() + "\n");
        }
        return result.toString();
    }

}
