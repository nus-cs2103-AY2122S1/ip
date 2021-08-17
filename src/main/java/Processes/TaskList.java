package Processes;

import java.util.List;
import java.util.ArrayList;

public class TaskList {

    private final List<Task> list;

    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    public void addTask(Task task) {
        this.list.add(task);
    }

    public void setDone(int index) {
        this.list.get(index).setDone();
    }

    public Task getTask(int index) {
        return this.list.get(index);
    }

    public Task getLastTask() {
        return this.getTask(this.getSize() - 1);
    }

    public int getSize() {
        return this.list.size();
    }

    @Override
    public String toString() {
        String result = "Here are the tasks in your list!\n";
        for (int i = 1; i < this.list.size() + 1; i++) {
            result += i + ". " + this.list.get(i - 1);
            if(i != this.list.size()) {
                result += "\n";
            }
        }
        return result;
    }
}