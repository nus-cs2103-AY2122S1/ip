import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }

    public void markDone(int index) {
        this.taskList.get(index).markAsDone();
    }

    public void markNotDone(int index) {
        this.taskList.get(index).markAsNotDone();
    }

    public String toString() {
        String s = "";

        for (int i = 0; i < this.taskList.size(); i++) {
            Task task = taskList.get(i);

            String doneSymbol = task.isDone() ? "[X]" : "[ ]";

            s += (i + 1) + ". " + doneSymbol + " " + task + "\n";
        }

        return s;
    }
}
