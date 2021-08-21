import java.util.List;

public class TaskList {
    private List<Task> tasks;

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Task> getTasks() {
        return this.tasks;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public void replaceTask(Task task, int index) {
        if (index >= this.tasks.size() || index < 0) {
            DukeException exception = new DukeException("Number out of range!");
            System.out.println(exception);
        }
        else {
            this.tasks.set(index, task);
        }
    }

    public void deleteTask(int index) {
        if (index >= this.tasks.size() || index < 0) {
            DukeException exception = new DukeException("Number out of range!");
            System.out.println(exception);
        }
        else {
            this.tasks.remove(index);
        }
    }
}
