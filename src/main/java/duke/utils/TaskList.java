package duke.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import duke.task.Task;

public class TaskList {
    // Level-6 -> A-Collections: Task List
    private final List<Task> taskList;

    public TaskList(File storageFile) {
        this();
    }

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public void add(Task t){
        taskList.add(t);
    }

    public void remove(int index){
        taskList.remove(index);
    }

    public int size(){
        return taskList.size();
    }

    public Task get(int index){
        return taskList.get(index);
    }
}
