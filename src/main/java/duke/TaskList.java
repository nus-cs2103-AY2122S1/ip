package duke;

import java.util.ArrayList;

public class TaskList {
    
    public ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public void add(Task t) {
        this.taskList.add(t);
    }

    public int size() {
        return this.taskList.size();
    }

    public Task get(int i) {
        return this.taskList.get(i);
    }

    public void remove(int i) {
        this.taskList.remove(i);
    }

}
