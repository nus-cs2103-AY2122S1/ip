package angrybot.task;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import angrybot.exception.NoSuchTaskException;

/**
 * Encapsulates the operations related to managing task.
 *
 * @author Zhi Bin
 * @version AngryBot Level 10
 */
public class TaskList {
    private ArrayList<Task> list = new ArrayList<>();

    /**
     * Returns a task created from the description mentioned in the string.
     * Used to convert stored data from local directory into task.
     *
     * @param str The string with a task description.
     * @return The created task.
     */
    public static Task stringToTask(String str) {
        String[] taskData = str.split("\\|");
        boolean isDone = taskData[1].equals("1");

        switch (taskData[0]) {
        case "D":
            LocalDateTime by = LocalDateTime.parse(taskData[3]);
            return new Deadline(taskData[2], isDone, by);

        case "E":
            LocalDateTime at = LocalDateTime.parse(taskData[3]);
            return new Event(taskData[2], isDone, at);

        default:
            return new ToDo(taskData[2], isDone);
        }
    }

    /**
     * Adds the task to current list.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        list.add(task);
    }

    /**
     * Deletes a specific task corresponding to the number in the list.
     *
     * @param taskNumber The task number.
     * @return The task deleted.
     * @throws NoSuchTaskException When the task number is invalid.
     */
    public Task delete(int taskNumber) throws NoSuchTaskException {
        if (taskNumber < 0 || taskNumber > list.size() + 1) {
            throw new NoSuchTaskException();
        }
        return list.remove(taskNumber - 1);
    }

    /**
     * Marks a specific task corresponding to the number in the list as done.
     *
     * @param taskNumber The task number.
     * @return The task that was marked as done.
     * @throws NoSuchTaskException When the task number is invalid.
     */
    public Task mark(int taskNumber) throws NoSuchTaskException {
        if (taskNumber < 0 || taskNumber > list.size() + 1) {
            throw new NoSuchTaskException();
        }
        Task task = list.get(taskNumber - 1);
        task.markDone();
        return task;
    }

    /**
     * Returns the number of task in the current list.
     *
     * @return The number of task in the list.
     */
    public int size() {
        return list.size();
    }

    /**
     * Returns the current list of task.
     *
     * @return Returns an ArrayList consisting of all the Task stored.
     */
    public ArrayList<Task> getList() {
        return list;
    }

    /**
     * Stores the task from the given task list into the current task list.
     *
     * @param list The task list to be stored to the current list.
     */
    public void loadFromList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Returns an ArrayList containing task related to the keyword provided by the user.
     *
     * @param s The keyword to search for in the description of task.
     * @return The list containing the related task.
     */
    public List<Task> findRelatedTask(String s) {
        Stream<Task> taskStream = list.stream().filter(task -> task.findKeyword(s));
        return Arrays.asList(taskStream.toArray(Task[]::new));
    }

    /**
     * Sorts the task list according to the date and time.
     */
    public void sortTaskList(boolean isReverse) {
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (!isReverse) {
                    if (list.get(i).compareTo(list.get(j)) == 1) {
                        Task temp = list.get(i);
                        list.set(i, list.get(j));
                        list.set(j, temp);
                    }
                } else {
                    if (list.get(i).compareTo(list.get(j)) == -1) {
                        Task temp = list.get(i);
                        list.set(i, list.get(j));
                        list.set(j, temp);
                    }
                }

            }
        }
    }
}
