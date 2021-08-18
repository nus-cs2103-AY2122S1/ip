package duke;
import java.util.ArrayList;
import duke.task.*;

public class TaskList {
    private static ArrayList<Task> tl;

    public TaskList() {
        tl = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> loadedData) {
        tl = loadedData;
    }

    public int size() {
        return tl.size();
    }

    public Task get(int i) {
        return tl.get(i);
    }

    public Task delete(int i) {
        return tl.remove(i);
    }

    public void add(Task t) {
        tl.add(t);
    }
}
