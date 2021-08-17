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

    @Override
    public String toString() {
        String result = "";
        for (int i = 1; i < this.list.size() + 1; i++) {
            result += i + ". " + this.list.get(i - 1);
            if(i != this.list.size()) {
                result += "\n";
            }
        }
        return result;
    }
}