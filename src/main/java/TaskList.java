import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    // For ToDo, event, deadline
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    // For done command
    public void markTaskAsDone(int index) {
        this.tasks.get(index).markAsDone();
    }

    // For delete command
    public void deleteTask(int index) {
        this.tasks.remove(index);
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    // For List command
    @Override
    public String toString() {
        String str = "";

        for (int i = 0; i < tasks.size(); i++) {
            str += "\t " + (i + 1) + ". " + this.tasks.get(i);
        }

        return str;
    }

    public int getSize() {
        return this.tasks.size();
    }
}
