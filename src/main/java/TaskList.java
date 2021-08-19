import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private static final String OUT_OF_BOUNDS_TASK = "Could not find task. Check the task number again?";
    private static final String NUMBER_OF_TASKS_MESSAGE = "Now you have %d %s in the list.";
    private static final String ADD_TASK_MESSAGE = "Got it. I've added this task:\n %s\n" + NUMBER_OF_TASKS_MESSAGE;
    private static final String REMOVE_TASK_MESSAGE = "Noted. I've removed this task:\n %s\n" + NUMBER_OF_TASKS_MESSAGE;

    private final List<Task> taskArr;

    public TaskList() {
        this.taskArr = new ArrayList<>();
    }

    public int size() {
        return taskArr.size();
    }

    public String addTask(Task task) {
        this.taskArr.add(task);
        return String.format(ADD_TASK_MESSAGE, task, this.size(), this.size() <= 1 ? "task" : "tasks");
    }

    public String markTaskAsDone(int taskIndex) throws DukeException {
        // Task index starts from 1
        int index = taskIndex - 1;
        try {
            Task task = taskArr.get(index);
            task.markDone();
            return task.toString();
        } catch (IndexOutOfBoundsException err) {
            throw new DukeException(OUT_OF_BOUNDS_TASK);
        }
    }

    public String deleteTask(int taskIndex) throws DukeException {
        // Task index starts from 1
        int index = taskIndex - 1;
        try {
            Task task = taskArr.remove(index);
            return String.format(REMOVE_TASK_MESSAGE, task, this.size(), this.size() <= 1 ? "task" : "tasks");
        } catch (IndexOutOfBoundsException err) {
            throw new DukeException(OUT_OF_BOUNDS_TASK);
        }
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
        return printedList.toString().trim();
    }

}
