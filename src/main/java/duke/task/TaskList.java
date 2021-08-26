package duke.task;

import java.util.ArrayList;
import java.util.Arrays;

public class TaskList {
    private ArrayList<Task> list = new ArrayList<>();

    public TaskList(Task... tasks) {
        this.list.addAll(Arrays.asList(tasks));
    }


    public void add(Task task) {
        this.list.add(task);
    }


    public void delete(int taskNum) {
        this.list.remove(taskNum - 1);
    }


    public int getLength() {
        return this.list.size();
    }


    public Task get(int taskNum) {
        return this.list.get(taskNum - 1);
    }


    public boolean isEmpty() {
        return this.list.size() == 0;
    }
}