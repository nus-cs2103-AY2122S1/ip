import java.util.ArrayList;
import java.util.List;

public class Tasks {
    private List<Task> tasks;

    public Tasks() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
        String plural = tasks.size() == 1 ? " task " : " tasks ";
        System.out.println("\t Got it. I've added this task:");
        System.out.println("\t \t " + task);
        System.out.println("\t Now you have " + tasks.size() + plural + "in the list.\n");
    }

    public boolean isEmpty() {
        return this.tasks.size() == 0;
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
