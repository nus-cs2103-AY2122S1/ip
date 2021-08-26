package duke;

import java.util.ArrayList;

import duke.task.Task;

public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }
    
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public void add(Task t) {
        taskList.add(t);
    }

    public Task remove(int index) {
        Task t = taskList.get(index);
        taskList.remove(index);
        return t;
    }

    public Task get(int index) {
        return taskList.get(index);
    }

    public int size() {
        return taskList.size();
    }


}
