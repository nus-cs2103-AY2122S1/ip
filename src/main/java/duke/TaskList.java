package duke;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private static TaskList MainList = null;
    private ArrayList<Task> taskList = new ArrayList<>();

    private TaskList() {}

    public static TaskList getTaskList() {
        if( MainList == null) {
            MainList = new TaskList();
        }

        return MainList;
    }

    public List<? extends Task> getTasks() {
        if(MainList == null) {
            MainList = new TaskList();
        }

        return this.taskList;
    }
    public int addTask(Task task) {
        if( MainList == null) {
            MainList = new TaskList();
        }
        this.taskList.add(task);
        return this.taskList.size();
    }

    public int getSize() {
        return this.taskList.size();
    }
}
