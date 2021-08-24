import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this(new ArrayList<Task>());
    }

    Task getTask(int index) {
        return tasks.get(index);
    }

    int getNumTasks() {
        return tasks.size();
    }

    void addTask(Task task) {
        tasks.add(task);
    }

    void removeTask(int index) {
        tasks.remove(index);
    }

    boolean markDone(int index) {
        if (tasks.isEmpty()) {
            return false;
        } else {
            tasks.get(index).setDone();
            return true;
        }
    }

    String genSaveFormat() {
        StringBuilder sb = new StringBuilder();
        tasks.stream().map(Task::dataFormat).forEach(sb::append);
        return sb.toString();
    }

    @Override
    public String toString() {
        if (tasks.isEmpty()) {
            return "You do not have any task currently";
        } else {
            StringBuilder output = new StringBuilder("Here are the tasks in your list:\n");
            for (int i = 1; i <= tasks.size(); i++) {
                output.append(String.format("%d. %s\n", i, tasks.get(i - 1)));
            }
            return output.toString();
        }
    }
}
