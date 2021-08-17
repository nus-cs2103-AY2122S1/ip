import java.util.ArrayList;
import java.util.List;

public class Tasks {
    private List<Task> tasks;

    public Tasks() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(String taskName) {
        Task newTask = new Task(taskName);
        tasks.add(newTask);
        System.out.println("\t added: " + taskName + "\n");
    }

    @Override
    public String toString() {
        String toPrint = "";

        for(int i = 0; i < tasks.size(); i++) {
            int index = i + 1;
            toPrint += ("\t " + index + ". " + tasks.get(i) + "\n");
        }

        return toPrint;
    }

    public Task getTask(int taskNum) {
        if (taskNum > tasks.size() || taskNum < 1) {
            return null;
        } else {
            return tasks.get(taskNum - 1);
        }
    }
}
