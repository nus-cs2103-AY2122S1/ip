package duke.task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list that contains a <code>List</code> of tasks.
 *
 * @author Sherman Ng Wei Sheng
 */
public class TaskList {
    private final List<Task> list;

    /**
     * Constructor to initialize a new TaskList
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }
    /**
     * Constructor to initialize a new TaskList
     */
    public TaskList(String data) {
        this.list = new ArrayList<>();
        this.loadFromStorage(data);
    }

    /**
     * Adds a new task to the list.
     *
     * @param task Task to be added
     */
    public void add(Task task) {
        list.add(task);
    }

    /**
     * Returns the size of the list.
     *
     * @return The size of the TaskList.
     */
    public int size() {
        return list.size();
    }

    /**
     * Returns the Task at the given index.
     *
     * @param index The index to retrieve the Task from in the TaskList.
     * @return The Task that is located at the given index.
     */
    public Task get(int index) {
        return list.get(index);
    }

    /**
     * Marks the task at the index as done.
     * If given a valid index, returns true and mark the Task as done.
     * If given an invalid index, returns false.
     *
     * @param index The given index to mark the Task located there as done.
     * @return A boolean value, true if successfully marked as done and false if invalid index given.
     */
    public boolean markDoneAtIndex(int index) {
        if (index > size() - 1 || index < 0) {
            return false;
        } else {
            Task taskToBeMarkDone = get(index);
            taskToBeMarkDone.markAsDone();
            return true;
        }
    }

    /**
     * Removes the task at the given index and returns that Task.
     *
     * @param index The index of the task to be removed.
     * @return The task that is removed.
     */
    public Task deleteAtIndex(int index) {
        if (index > size() - 1 || index < 0) {
            return null;
        } else {
            return list.remove(index);
        }
    }

    /**
     * Returns the string that should be printed when asked to print the current task list.
     *
     * @return The string that should be printed when the print command is executed.
     */
    public String generateMessage() {
        int listSize = list.size();

        StringBuilder message = new StringBuilder("Here are the tasks in your list:");
        for (int i = 0; i < listSize; i++) {
            int index = i + 1;
            Task content = list.get(i);
            message.append("\n").append(index).append(".").append(content);
        }
        return message.toString();
    }

    /**
     * Loads the data from storage and update the task list.
     *
     * @param data The string data retrieved from storage.
     */
    private void loadFromStorage(String data) {
        String[] lines = data.split("\n");
        if (lines[0].equals("")) {
            return;
        }
        for (String line : lines) {
            String[] split = line.split(" \\| ");
            String taskType = split[0];
            boolean isDone = !split[1].equals("0");
            String description = split[2];
            String dateTimeString;
            assert taskType.equals("T")
                    || taskType.equals("D")
                    || taskType.equals("E")
                    : "taskType should only be T,D,E";
            switch (taskType) {
            case "T":
                ToDo toDoTask = new ToDo(description, isDone);
                add(toDoTask);
                break;
            case "D":
                dateTimeString = split[3];
                Deadline deadlineTask = new Deadline(description, LocalDate.parse(dateTimeString), isDone);
                add(deadlineTask);
                break;
            case "E":
                dateTimeString = split[3];
                Event eventTask = new Event(description, LocalDate.parse(dateTimeString), isDone);
                add(eventTask);
                break;
            default:
                break;
            }
        }
    }

    /**
     * Returns the string that is suitable for the storage of the entire task list in a txt file.
     *
     * @return The string with each task encoded that is suitable for storage.
     */
    public String convertToStorageString() {
        int size = list.size();
        StringBuilder finalMessage = new StringBuilder();
        for (int i = 0; i < size; i++) {
            Task currentTask = list.get(i);
            String taskMessage = currentTask.encodeTaskForStorage();
            finalMessage.append(taskMessage);
            if (i != size - 1) {
                finalMessage.append("\n");
            }
        }
        return finalMessage.toString();
    }
}
