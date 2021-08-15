import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList = new ArrayList<>();

    private void addTask(Task task) {
        taskList.add(task);

        Duke.printMessage("Got it. I've added this task:\n  "
                + task.toString()
                + "\nNow you have " + taskList.size() + " tasks in the list.");
    }

    public void addToDo(String input) {
        String name = input.substring(5);
        Task task = new ToDo(name);
        addTask(task);
    }

    public void addDeadline(String input) {
        String[] inputs = input.substring(9).split(" /by ");
        String name = inputs[0];
        String time = inputs[1];
        Task task = new Deadline(name, time);
        addTask(task);
    }

    public void addEvent(String input) {
        String[] inputs = input.substring(6).split(" /at ");
        String name = inputs[0];
        String time = inputs[1];
        Task task = new Event(name, time);
        addTask(task);
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
