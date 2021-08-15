import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList = new ArrayList<>();

    public void add(String name) {
        Task task = new Task(name);
        taskList.add(task);
        Duke.printMessage("Added: " + name);
    }

    public void doTask(String taskNum) {
        int idx = Integer.parseInt(taskNum);
        Task task = getTask(idx);
        task.doTask();
        Duke.printMessage("Nice! I've marked this task as done:\n  " + task.toString());
    }

    // Returns a nicely formatted string representation of all tasks in the list
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        int size = taskList.size();
        for (int i = 1; i <= size; i++) {
            stringBuilder.append(i);
            stringBuilder.append(". ");
            stringBuilder.append(getTask(i).toString());
            stringBuilder.append("\n");
        }

        // delete the last new line character
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);

        return stringBuilder.toString();
    }

    // taskList represents its tasks 1-indexed
    private Task getTask(int idx) {
        return taskList.get(idx - 1);
    }
}
