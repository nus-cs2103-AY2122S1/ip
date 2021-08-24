package duke;
import java.util.ArrayList;

import duke.tasks.Task;

public class TaskList {
    protected ArrayList<Task> taskList;
    
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

}
