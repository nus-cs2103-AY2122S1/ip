import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> tasks;
    protected Save save;
    protected Load load;

    public TaskList() throws DukeException {
        this.save = new Save();
        this.load = new Load();

        tasks = load.loadTasks();
    }

    public Task get(int i) {
        return tasks.get(i);
    }

    public int getLength() {
        return tasks.size();
    }

    public void markDone(int i) throws DukeException {
        tasks.get(i).markDone();
        save.saveTasks(tasks);
    }

    public void add(Task t) throws DukeException {
        tasks.add(t);
        save.saveTasks(tasks);
    }

    public void remove(int i) throws DukeException {
        tasks.remove(i);
        save.saveTasks(tasks);
    }

}
