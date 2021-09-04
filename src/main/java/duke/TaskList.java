package duke;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class TaskList {
    private ArrayList<Task> taskList = new ArrayList<>();
    public void addTask(Task task) {
        taskList.add(task);
    }
    public void deleteTask(int taskIndex) {
        taskList.remove(taskIndex);
    }
    public int length() {
        return taskList.size();
    }
    public String printTask() {
        AtomicReference<String> output = new AtomicReference<>("Here are your tasks: \n");
        System.out.println("Here are your tasks: ");
        taskList.forEach(task -> {
            output.set(output + task.toString() + "\n");
            System.out.println(task.toString());
        });
        return output.toString();
    }
    public Task get(int taskIndex) {
        return taskList.get(taskIndex);
    }
}
