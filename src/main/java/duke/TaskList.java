package duke;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class TaskList {
    private ArrayList<Task> taskList = new ArrayList<>();
    public void addTask(Task task) {
        int sizeBeforeAddition = taskList.size();
        taskList.add(task);
        int sizeAfterAddition = taskList.size();
        assert sizeAfterAddition - sizeBeforeAddition == 1: "Error while task addition";
    }
    public void deleteTask(int taskIndex) {
        int sizeBeforeDeletion = taskList.size();
        taskList.remove(taskIndex);
        int sizeAfterDeletion = taskList.size();
        assert sizeBeforeDeletion - sizeAfterDeletion == 1: "Error while task deletion";
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
