import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.util.ArrayList;
import java.util.List;

public class TaskManager {

    private final static int MAX_STORAGE = 100;

    private final static List<Task> TASK_LIST = new ArrayList<>();

    public static void addToList(Task newTask) {
        if (TASK_LIST.size() == MAX_STORAGE) {
            Echoer.error("Unable to add task as list is full.");
            return;
        }
        TASK_LIST.add(newTask);
        Echoer.info("Got it. I've added this task:\n\t  " + newTask + "\n\tNow you have " +
                TASK_LIST.size() + " tasks in the list.");
    }

    public static void addToDoTask(String userInput) {
        String description = userInput.trim();
        addToList(new Todo(description));
    }

    public static void addEventTask(String userInput) {
        String[] inputParts = userInput.split("/at");
        String description = inputParts[0].trim();
        String timing = inputParts[1].trim();
        addToList(new Event(description, timing));
    }

    public static void addDeadlineTask(String userInput) {
        String[] inputParts = userInput.split("/by");
        String description = inputParts[0].trim();
        String by = inputParts[1].trim();
        addToList(new Deadline(description, by));
    }

    public static void markTaskAsDone(int taskNumber) {
        if (TASK_LIST.isEmpty()) {
            Echoer.error("List is empty.");
            return;
        }
        if (taskNumber < 0 || TASK_LIST.size() < taskNumber) {
            Echoer.error("Task does not exist.");
            return;
        }
        Task selectedTask = TASK_LIST.get(taskNumber - 1); // shift to 0-indexing
        selectedTask.markAsDone();
        Echoer.info("Nice! I've marked this task as done:\n\t  " + selectedTask);
    }

    public static void listTasks() {
        if (TASK_LIST.isEmpty()) {
            Echoer.info("List is empty.");
            return;
        }
        List<String> taskManagerStringList = new ArrayList<>();
        taskManagerStringList.add("Here are the tasks in your list:");
        for (int idx = 0; idx < TASK_LIST.size(); idx ++) {
            Task task = TASK_LIST.get(idx);
            int taskNumber = idx + 1; // shift to 1-indexing
            taskManagerStringList.add(String.format("%d. %s", taskNumber, task));
        }
        Echoer.list(taskManagerStringList);
    }
}
