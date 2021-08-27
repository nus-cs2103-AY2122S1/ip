package duke.task;

// import duke packages
import duke.DukeException;

// import java packages
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public Task get(int i) {
        return tasks.get(i);
    }

    public int getLength() {
        return tasks.size();
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public void markDone(int i) throws DukeException {
        tasks.get(i).markDone();
    }

    public void add(Task t) throws DukeException {
        tasks.add(t);
    }

    public void remove(int i) throws DukeException {
        tasks.remove(i);
    }

}
