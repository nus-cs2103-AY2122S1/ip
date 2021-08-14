import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    private int taskLabel;

    public TaskList() {
        this.tasks = new ArrayList<>();
        this.taskLabel = 0;
    }

    public int length() {
        return this.tasks.size();
    }

    public void addTask(String description) {
        Task task = new Task(description);
        this.tasks.add(task);
        taskLabel++;
    }

    public void addEvent(String description, String time) {
        Event event = new Event(description, time);
        this.tasks.add(event);
        taskLabel++;
    }

    public void addDeadline(String description, String time) {
        Deadline event = new Deadline(description, time);
        this.tasks.add(event);
        taskLabel++;
    }

    public String markTaskDone(int i) {
        return tasks.get(i).markAsDone();
    }

    public String delete(int index) {
        Task taskToDelete = this.tasks.get(index);
        this.tasks.remove(index);
        taskLabel--;
        String taskQuantifier = this.tasks.size() == 1 ? "task..." : "tasks...";
        String taskCount = "\nYou now have " + tasks.size() + " " + taskQuantifier;
        return "Fine, I'll delete: " + taskToDelete.toString() + taskCount;
    }

    @Override
    public String toString() {
        StringBuilder tasksList = new StringBuilder();
        if (this.taskLabel == 0) {
            return "No tasks yet, stop checking...";
        }

        for (int i = 0; i < this.taskLabel; i++) {
            String fullTaskLine = (i + 1) + ". " + this.tasks.get(i).toString() + "\n";
            tasksList.append(fullTaskLine);
        }
        return "Fine, here are your tasks: \n" + tasksList.toString();
    }
}
