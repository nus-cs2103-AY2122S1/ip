import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> taskArr;

    public TaskList() {
        this.taskArr = new ArrayList<>();
    }

    public String addTask(String text) {
        Task task = new Task(text);
        this.taskArr.add(task);
        return String.format("added: %s", task);
    }

    public String markTaskAsDone(int taskIndex) {
        // Task index starts from 1
        int index = taskIndex - 1;
        Task task = taskArr.get(index);
        task.markDone();
        return task.toString();
    }

    @Override
    public String toString() {
        StringBuilder printedList = new StringBuilder();
        for (int i = 0; i < taskArr.size(); i++) {
            // Index from 1 onwards
            String index = Integer.toString(i + 1);
            printedList.append(String.format("%s. %s\n", index, this.taskArr.get(i)));
        }
        // Remove the last newline
        printedList.delete(printedList.length() - 1, printedList.length());
        return printedList.toString();
    }

}
