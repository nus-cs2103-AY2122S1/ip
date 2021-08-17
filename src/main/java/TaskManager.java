import exception.TaskManagerException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.util.ArrayList;
import java.util.List;

public class TaskManager {

    private final static int MAX_STORAGE = 100;

    private final List<Task> TASK_LIST = new ArrayList<>();

    public int getTaskListSize() {
        return TASK_LIST.size();
    }

    public Task addTask(Task newTask) throws TaskManagerException {
        if (TASK_LIST.size() == MAX_STORAGE) {
            throw new TaskManagerException("Unable to add task as list is full.");
        }
        TASK_LIST.add(newTask);
        return newTask;
    }

    public Task addToDoTask(String userInput) throws TaskManagerException {
        String description = userInput.trim();
        if (description.isEmpty()) {
            throw new TaskManagerException("Task description cannot be empty.");
        }
        Task todo = new Todo(description);
        return addTask(todo);
    }

    public Task addEventTask(String userInput) throws TaskManagerException {
        String[] parameterArray = splitUserInput("/at", userInput);
        Task event = new Event(parameterArray[0], parameterArray[1]); // desc, timing
        return addTask(event);
    }

    public Task addDeadlineTask(String userInput) throws TaskManagerException {
        String[] parameterArray = splitUserInput("/by", userInput);
        Task deadline = new Deadline(parameterArray[0], parameterArray[1]);// desc, by
        return addTask(deadline);
    }

    public String[] splitUserInput(String splitKey, String userInput)
            throws TaskManagerException {

        if (!userInput.contains(splitKey)) {
            throw new TaskManagerException("Missing key '" + splitKey + "'.");
        }

        String[] inputParts = userInput.split(splitKey);
        if (inputParts.length != 2) {
            throw new TaskManagerException("Ensure input has both parameters for task.");
        }

        String description = inputParts[0].trim();
        if (description.isEmpty()) {
            throw new TaskManagerException("Task description cannot be empty.");
        }

        String split = inputParts[1].trim();
        if (split.isEmpty()) {
            throw new TaskManagerException("Input after '" + splitKey + "' cannot be empty.");
        }
        return new String[] {description, split};
    }

    public Task markTaskAsDone(String taskNumberAsString) throws TaskManagerException {
        if (TASK_LIST.isEmpty()) {
            throw new TaskManagerException("List is empty.");
        }

        int taskNumber;
        try {
            taskNumber = Integer.parseInt(taskNumberAsString);
            if (taskNumber < 0 || TASK_LIST.size() < taskNumber) {
                throw new IllegalArgumentException();
            }
        } catch (NumberFormatException exception) {
            throw new TaskManagerException("'" + taskNumberAsString + "' is not an integer.");

        } catch (IllegalArgumentException exception) {
            throw new TaskManagerException("Task number '" + taskNumberAsString + "' doesn't exist.");
        }

        Task selectedTask = TASK_LIST.get(taskNumber - 1); // shift to 0-indexing
        selectedTask.markAsDone();
        return selectedTask;
    }

    public List<String> listTasks() throws TaskManagerException {
        if (TASK_LIST.isEmpty()) {
            throw new TaskManagerException("List is empty, add some tasks first.");
        }
        List<String> taskManagerStringList = new ArrayList<>();
        taskManagerStringList.add("Here are the tasks in your list:");
        for (int idx = 0; idx < TASK_LIST.size(); idx ++) {
            Task task = TASK_LIST.get(idx);
            int taskNumber = idx + 1; // shift to 1-indexing
            taskManagerStringList.add(String.format("%d. %s", taskNumber, task));
        }
        return taskManagerStringList;
    }
}
