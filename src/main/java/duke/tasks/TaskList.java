package duke.tasks;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> taskList = new ArrayList<>();

    public void add(Task t) {
        taskList.add(t);
        return;
    }

    public void remove(int num) {
        taskList.remove(num - 1);
        return;
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }
}
