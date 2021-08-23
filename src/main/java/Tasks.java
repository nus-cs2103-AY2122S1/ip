import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Tasks {
    private List<Task> tasks;

    public Tasks() {
        this.tasks = new ArrayList<>();
    }

    public Tasks(List<Task> taskList) {
        this.tasks = taskList;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
        System.out.println("\t Got it. I've added this task:");
        System.out.println("\t \t " + task);
        System.out.println(taskSummary());
    }

    public String taskSummary() {
        int numTasks = this.tasks.size();
        String size = numTasks == 0 ? "no" : String.valueOf(numTasks);
        String maybePlural = numTasks == 1 ? " task " : " tasks ";
        return "\t You have " + size + maybePlural + "in the list.\n";
    }

    public void deleteTask(Task task) {
        this.tasks.remove(task);
        System.out.println("\t Got it. I've removed this task:");
        System.out.println("\t \t " + task);
        System.out.println(taskSummary());
    }

    public List<String> formatStorage() {
        return tasks.stream().map(Task::storageString).collect(Collectors.toList());
    }

    public boolean isEmpty() {
        return this.tasks.size() == 0;
    }

    @Override
    public String toString() {
        String toPrint = "";

        for (int i = 0; i < this.tasks.size(); i++) {
            int index = i + 1;
            toPrint += ("\t " + index + ". " + this.tasks.get(i) + "\n");
        }

        return toPrint;
    }

    public void clearTasks() {
        tasks.clear();
    }

    public Task getTask(int taskNum) {
        if (taskNum > this.tasks.size() || taskNum < 1) {
            return null;
        } else {
            return this.tasks.get(taskNum - 1);
        }
    }
}
