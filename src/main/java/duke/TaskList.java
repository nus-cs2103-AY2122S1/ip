package duke;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task getTask(int i) {
        return tasks.get(i);
    }

    public Task removeTask(int i) {
        return tasks.remove(i);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public int getSize() {
        return tasks.size();
    }

    public String getTaskList() {
        String list = "These are the tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            list += (i + 1) + ". " + tasks.get(i).toString() + "\n";
        }
        return list;
    }

    public String getListStatus() {
        if (tasks.size() == 0) {
            return "There are no tasks in the list\n";
        } else if (tasks.size() == 1) {
            return "\nThere is currently 1 task in your list\n";
        } else {
            return String.format("\nThere are currently %d tasks in your list\n", tasks.size());
        }
    }

}
