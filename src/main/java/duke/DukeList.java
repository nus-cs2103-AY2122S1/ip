package duke;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Iterator;

public class DukeList implements Iterable<Task> {
    private final ArrayList<Task> list;

    public DukeList() {
        list = new ArrayList<>();
    }

    public DukeList(ArrayList<Task> list) {
        this.list = list;
    }

    public Task get(int i) {
        return list.get(i);
    }

    public int size() {
        return list.size();
    }

    public void add(Task task) {
        list.add(task);
    }

    public void delete(int i) {
        list.remove(i);
    }

    @Override
    public String toString() {
        StringBuilder listString = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            String line = "  " + (i + 1) + "." + task.toString();
            listString.append(line);
            if (i != list.size() - 1) {
                listString.append("\n");
            }
        }
        return listString.toString();
    }

    @Override
    public Iterator<Task> iterator() {
        return list.iterator();
    }

}
