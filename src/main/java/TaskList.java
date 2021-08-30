import java.util.ArrayList;
import java.util.function.Consumer;

public class TaskList {

    private ArrayList<Task> list;

    public TaskList(ArrayList<Task> tasks) {
        list = tasks;
    }

    public Task get(int index) {
        return list.get(index);
    }

    public void add(Task task) {
        list.add(task);
    }

    public void delete(int i) throws NoSuchTaskException {
        list.remove(i);
    }

    public int size() {
        return list.size();
    }

    public void forEach(Consumer<? super Task> action) {
        list.forEach(action);
    }

}
