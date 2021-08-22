import java.util.List;

public class TaskList {

    private List<Task> tasks;

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Task> getTasks() {
        return this.tasks;
    }

    public int getSize() {
        return this.tasks.size();
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public Task markAsDone(int index) throws DukeException {
        try {
            Task subject = this.tasks.get(index);
            subject.markAsDone();

            return subject;
        } catch (Exception e) {
            throw new DukeException("☹ OOPS!!! The task does not exist.");
        }
    }

    public Task deleteTask(int index) throws DukeException {
        try {
            Task task = this.tasks.get(index);
            this.tasks.remove(index);
            return task;
        } catch (Exception e) {
            throw new DukeException("☹ OOPS!!! The task does not exist.");
        }
    }
}
