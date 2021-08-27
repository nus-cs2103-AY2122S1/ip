package duke;

import java.util.ArrayList;
import duke.task.Task;

public class TaskList {
    private ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    // add
    public void addTask(Task task) {
        list.add(task);
    }

    // delete
    public void deleteTask(Task task) {
        list.remove(task);
    }

    // getSize()
    public int getSize() {
        return list.size();
    }

    // getTask()
    public Task getTask(int i) {
        return list.get(i);
    }

    public TaskList matchTasks(String keyword) {
        TaskList matches = new TaskList();
        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            if (task.toString().contains(keyword)) {
                matches.addTask(task);
            }
        }
        return matches;
    }

    public ArrayList<Task> getList() {
        return list;
    }
}
