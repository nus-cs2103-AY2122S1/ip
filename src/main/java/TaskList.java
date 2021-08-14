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

    public String markTaskDone(int i) {
        this.tasks.get(i).markAsDone();
        String taskDone = tasks.get(i).toString();
        return "Nice! This task has been marked as done: \n" + taskDone;
    }

    @Override
    public String toString() {
        StringBuilder tasksList = new StringBuilder();
        if (this.taskLabel == 0) {
            return "You don't have any tasks yet, try adding something!";
        }

        for (int i = 0; i < this.taskLabel; i++) {
            String fullTaskLine = (i + 1) + ". " + this.tasks.get(i).toString() + "\n";
            tasksList.append(fullTaskLine);
        }
        return "Here are your tasks: \n" + tasksList.toString();
    }
}
