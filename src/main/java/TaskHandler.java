import java.util.ArrayList;
import java.util.List;
public class TaskHandler {
    private static final String NO_TASKS_FOUND = "Nothing here... :( Type to add something. :^)\n";
    private static final String TASK_ADDED_MESSAGE = "Voila! ^_^ I've added this task:\n%s\n\nYou currently have %d task(s) in the list.\n";
    private static final String TASK_DONE_MESSAGE = "Good Job! :D I've marked this task as done:\n%s\n\nYou currently have %d undone task(s) in the list.\n";
    private static final String TASK_LIST = "Here are the task(s) in your list! n_n\n";
    private static final String NO_SUCH_TASK_ID = "Awwww, I can't seem to find this task index. Try again? U_U\n";
    private static final String INVALID_DONE_INPUT = "Please enter a number starting from 1! :~)";

    private List<Task> taskList;

    public TaskHandler() {
        taskList = new ArrayList<>();
    }

    public String addTask(Task task) {
        taskList.add(task);
        String str = String.format(TASK_ADDED_MESSAGE, task.toString(), getTotalTasksCount());
        return str;
    }

    private int getUndoneTasksCount() {
        int count = 0;
        for (Task task : taskList) {
            if (!task.isComplete()) {
                count++;
            }
        }
        return count;
    }

    private int getTotalTasksCount() {
        return taskList.size();
    }

    public String markTaskAsDone(int n) throws DukeException {
        if (n > 0 && n <= taskList.size()) {
            int index = n - 1;
            Task t = taskList.get(index);
            t.markAsDone();
            String str = String.format(TASK_DONE_MESSAGE, t, getUndoneTasksCount());
            return str;
        } else {
            throw new DukeException(NO_SUCH_TASK_ID);
        }
    }

    @Override
    public String toString() {
        String allTasks = TASK_LIST;
        if (taskList.size() == 0) {
            return NO_TASKS_FOUND;
        } else {
            for (int i  = 0; i < taskList.size(); i++) {
                int taskNumber =  i + 1;
                String taskName = taskList.get(i).toString();
                allTasks += String.format("%d. %s\n", taskNumber, taskName);
            }
        }
        return allTasks;
    }
}
