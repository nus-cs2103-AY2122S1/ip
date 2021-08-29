package duke;

import duke.tasks.Task;
import java.util.ArrayList;

public class TaskList {
    protected final ArrayList<Task> taskList = new ArrayList<>();

    public void addTask(Task t) {
        taskList.add(t);
    }

    public Task getTask(int idx) {
        return taskList.get(idx);
    }

    public void removeTask(int idx) {
        taskList.remove(idx);
    }



    public int size() {
        return taskList.size();
    }

    @Override
    public String toString() {
        String lst = "";
        for (int i = 1; i <= taskList.size(); i++) {
            Task task = taskList.get(i - 1);
            lst += "\n" + i + ". " + task;
        }
        return lst;
    }

}
