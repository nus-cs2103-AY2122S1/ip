import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    private int length = 0;

    /**
     * Empty TaskList constructor.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * TaskList constructor with tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.length = tasks.size();
    }

    public boolean isValidTaskIndex(int index) {
        return index >= 0 && index < length;
    }

    public TaskList add(String item) {
        String indent = "    ";
        Task newItem = new Task(item);
        ArrayList<Task> newList = new ArrayList<>(tasks);
        newList.add(newItem);
        return new TaskList(newList);
    }

    public Task markTaskAsCompleted(int index) {
        Task task = tasks.get(index);
        task.markAsCompleted();
        return task;
    }

    @Override
    public String toString() {
        String str = "";
        int i = 1;
        for (Task item : tasks) {
            str += String.format("%4s%d. %s\n", " ", i, item);
            i++;
        }
        return str;
    }
}
