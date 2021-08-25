import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public void deleteTask(int index) {
        String taskString = taskList.get(index).toString();
        this.taskList.remove(index);

        System.out.println("Noted. I've removed this task:\n"
                + "  "
                + taskString + "\n"
                + "Now you have " + taskList.size()
                + " task" + (taskList.size() > 1 ? "s" : "") + " in the list.");
    }

    public void addTask(Task task) {
        this.taskList.add(task);

        System.out.println("Got it. I've added this task:\n"
                + "  " + task.toString() + "\n"
                + "Now you have " + taskList.size()
                + " task" + (taskList.size() > 1 ? "s" : "") + " in the list.");
    }

    public void markAsDone(int index) {
        Task task = taskList.get(index);
        task.markAsDone();

        System.out.println("Nice! I've marked this task as done:\n"
                + "  "
                + task.toString());
    }

    public String toStorageString() {
        String result = "";
        for (int i = 0; i < taskList.size(); i++) {
            Task nextTask = taskList.get(i);
            String nextString = nextTask.toStorageString() + "\n";
            result += nextString;
        }
        return result.trim();
    }

    @Override
    public String toString() {
        String result = "";

        for (int i = 0; i < taskList.size(); i++) {
            Task nextTask = taskList.get(i);
            String nextTaskString = (i + 1) + "." + nextTask.toString() + "\n";
            result += nextTaskString;
        }

        result = result.trim();
        return result;
    }
}
