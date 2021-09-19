import java.util.ArrayList;
public class TaskList {
    private ArrayList<Task> tasks;

    TaskList() {
        this.tasks = new ArrayList<>();
    }

    public Task getTaskById(int id) {
        return tasks.get(id);
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public void removeTaskById(int id) {
        tasks.remove(id);
    }
    @Override
    public String toString() {
        if(tasks == null) {
            return "Hello";
        }
        String tasksInString = tasks.stream().map(Task::toString).reduce("",
                (x, y) -> x + "\n" + y);
        return tasksInString;
    }
    public String formattedToString() {
        int numberOfTasks = tasks.size();
        String tasksInStringFormatted = "";
        for (int i = 1; i <= numberOfTasks; i++) {
            tasksInStringFormatted = tasksInStringFormatted + i + ". " + tasks.get(i - 1) + "\n";
        }
        return tasksInStringFormatted;
    }

}
