import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private ArrayList<Task> tasklist;
    private Ui ui;

    public TaskList() {
        this.ui = new Ui();
        this.tasklist = new ArrayList<Task>();
    }

    public void addTask(Task task) {
        ui.addTaskToList(task, tasklist.size()+1);
        this.tasklist.add(task);
    }

    public void removeTask(int index) {
        ui.removeTaskFromList(tasklist.get(index), tasklist.size()-1);
        this.tasklist.remove(index);
    }

    public Task getTask(int index) {
        return this.tasklist.get(index);
    }

    public int getSize() {
        return this.tasklist.size();
    }

}
