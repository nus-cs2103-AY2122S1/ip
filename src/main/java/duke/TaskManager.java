package duke;

import java.io.IOException;
import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Represents a TaskManager object that stores and operates on a List of tasks.
 */
public class TaskManager {
    private List<Task> taskList;
    private final Storage storage = new Storage();

    TaskManager() throws DateTimeException {
        this.taskList = new ArrayList<>();
        load();
    }

    /**
     * Adds a task to the taskList and saves it to database.
     *
     * @param task any Task object is accepted.
     */
    public void addTask(Task task) throws DukeException {
        assert task != null : "Should not try to add a null task";
        boolean isNewTask = taskList.stream().filter(t -> t.equals(task)).findFirst().isEmpty();
        if (isNewTask) {
            taskList.add(task);
            save();
        } else {
            throw new DukeException("Task already in your list");
        }
    }

    /**
     * Deletes a task at specified index.
     * If given index is too big or negative, an IllegalArgumentException is thrown.
     *
     * @param taskId The index (0-indexed) of the task to be deleted.
     * @return The task that has just been deleted.
     * @throws IllegalArgumentException If taskId >= taskList.size() or taskId < 0.
     */
    public Task deleteTask(int taskId) throws IllegalArgumentException {
        if (taskId < taskList.size() && taskId >= 0) {
            Task ret = taskList.remove(taskId);
            save();
            return ret;
        } else {
            throw new IllegalArgumentException("☹ OOPS!!! Task Index is invalid!!");
        }
    }

    public int getNumOfTasks() {
        return taskList.size();
    }

    /**
     * Mark the task at specified index as done.
     * If given index is too big or negative, an IllegalArgumentException is thrown.
     *
     * @param taskId The index (0-indexed) of the task to be mark as done.
     * @return The task that has just been marked as done.
     * @throws IllegalArgumentException If taskId >= taskList.size() or taskId < 0.
     */
    public Task completeTask(int taskId) throws IllegalArgumentException {
        if (taskId < taskList.size() && taskId >= 0) {
            taskList.set(taskId, taskList.get(taskId).markDone());
            save();
            return taskList.get(taskId);
        } else {
            throw new IllegalArgumentException("☹ OOPS!!! Task Index is invalid!!");
        }
    }

    /**
     * Format the list of tasks in a ordered numbered list format.
     *
     * @return pretty-printable string of the list of tasks
     */
    public String listTasks() {
        return Stream.iterate(0, x -> x < taskList.size(), x -> x + 1)
                .map(x -> String.format("%d. %s", x + 1, taskList.get(x).toString()))
                .collect(Collectors.joining("\n"));
    }

    public List<Task> find(String keyword) {
        return this.taskList.stream().filter(t -> t.getDescription().contains(keyword)).collect(Collectors.toList());
    }

    private void load() throws DateTimeException {
        try {
            List<String> lines = storage.load();
            lines.forEach(line -> {
                String[] parts = line.split("[|]");
                String type = parts[0];
                boolean isDone = parts[1].equals("1");
                switch (type) {
                case "T":
                    taskList.add(ToDo.of(isDone, parts[2]));
                    break;
                case "D":
                    taskList.add(Deadline.of(isDone, parts[2], parts[3]));
                    break;
                case "E":
                    taskList.add(Event.of(isDone, parts[2], parts[3]));
                    break;
                default:
                    break;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void save() {
        try {
            StringBuilder data = new StringBuilder();
            for (Task t : taskList) {
                data.append(t.toStorageString() + "\n");
            }
            storage.save(data.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
