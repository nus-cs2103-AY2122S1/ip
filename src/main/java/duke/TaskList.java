package duke;

import java.util.ArrayList;
import java.util.function.Consumer;

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
    public void printTask() {
        System.out.println("Here are your tasks: ");
        taskList.forEach(task -> {
            System.out.println(task.toString());
        });
    }
    public Task get(int taskIndex) {
        return taskList.get(taskIndex);
    }
    public void forEach(Consumer<Task> consumer) {
        taskList.forEach(consumer);
    }
}
