package duke;

import duke.task.Task;

import java.util.ArrayList;

public class List {
    ArrayList<Task> list = new ArrayList<>(100);

    public List() {}

    public List(List list) {
        this.list = list.getList();
    }

    public void add(Task task) {
        list.add(task);
    }

    public ArrayList<Task> getList() {
        return list;
    }

    public String complete(int taskNo) {
        return list.get(taskNo).check();
    }

    public String delete(int taskNo) {
        String temp = list.get(taskNo).toString();
        list.remove(taskNo);
        return temp;
    }
}
