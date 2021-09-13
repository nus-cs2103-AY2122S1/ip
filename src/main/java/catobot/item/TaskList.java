package catobot.item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import catobot.exception.EmptyTaskListException;
import catobot.exception.OutOfBoundException;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    /** List of tasks. */
    private final List<Task> taskList;

    /**
     * Constructor for TaskList.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Constructor for TaskList.
     *
     * @param taskList The lists of tasks.
     */
    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Constructor for TaskList.
     *
     * @param tasks Various number of tasks.
     */
    public TaskList(Task ...tasks) {
        this.taskList = new ArrayList<>();
        taskList.addAll(Arrays.asList(tasks));
        assert taskList.size() != 0 : "taskList cannot be empty";
    }

    /**
     * Adds a task into the TaskList.
     *
     * @param item The task to be added.
     * @return The response message after adding the catobot.item.
     */
    public String add(Task item) {
        this.taskList.add(item);
        String front = "Got it meow! I've added this task:";
        String back = checkSize();
        return String.format("%s\n      %s\n%s", front, item, back);
    }

    /**
     * Displays the current list of tasks.
     *
     * @return The bullet point lists of current tasks.
     */
    public String display() {
        if (taskList.isEmpty()) {
            return "Meow currently no tasks!";
        }
        return String.format("Here are the tasks in your list:%s", this);
    }

    /**
     * Marks a specific task as completed.
     *
     * @param index The index of the task in the TaskList.
     * @return The response message after completing the task.
     * @throws OutOfBoundException If the index is out of valid range of TaskList.
     * @throws EmptyTaskListException If the TaskList is empty.
     */
    public String completeTask(int index) throws OutOfBoundException, EmptyTaskListException {
        checkRange(index);
        Task t = taskList.get(index - 1);
        t.markAsDone();
        return String.format("Nice! I've marked this task as done:\n      %s", t);
    }

    /**
     * Deletes a task from current TaskList.
     *
     * @param index The index of the task in the TaskList.
     * @return The response message after deleting the task.
     * @throws OutOfBoundException If the index is out of valid range of TaskList.
     * @throws EmptyTaskListException If the TaskList is empty.
     */
    public String deleteTask(int index) throws OutOfBoundException, EmptyTaskListException {
        checkRange(index);
        Task temp = taskList.get(index - 1);
        taskList.remove(temp);
        return String.format(
                "Noted. I've removed this task:\n      %s\n%s", temp, checkSize());
    }

    /**
     * Searches the list of tasks.
     *
     * @param keyword The keyword used for searching.
     * @return A list of matching tasks.
     */
    public String search(String keyword) {
        TaskList filtered = new TaskList();
        this.taskList
                .stream()
                .filter((s) -> s.contains(keyword))
                .forEach(filtered::add);

        if (filtered.taskList.isEmpty()) {
            return "Meow! No tasks found!";
        }

        return String.format(
                "Here are the matching tasks in your list:%s",
                filtered);
    }

    /**
     * Sorts filtered tasks.
     *
     * @param taskType The type of tasks to work on.
     * @param condition The conditions for filtering relevant tasks to sort.
     * @return The filtered list of tasks.
     */
    public String sort(TaskType taskType, Predicate<Task> condition) {
        TaskList filtered = new TaskList();
        this.taskList
                .stream()
                .filter(condition)
                .sorted()
                .forEach(filtered::add);

        if (filtered.taskList.isEmpty()) {
            return "Meow! No tasks found!";
        }

        return String.format(
                "Here are the sorted %s in your list:%s",
                taskType.getName(),
                filtered);
    }

    private void checkRange(int index) throws OutOfBoundException, EmptyTaskListException {
        if (taskList.isEmpty()) {
            throw new EmptyTaskListException();
        }

        if (index < 1 || index > taskList.size()) {
            String expected = String.format("%d - %d", 1, taskList.size());
            String actual = String.format("%d", index);
            throw new OutOfBoundException(expected, actual);
        }
    }

    private String checkSize() {
        return String.format("Now you have %d tasks in the list.", taskList.size());
    }

    /**
     * Returns a string representing the TaskList.
     *
     * @return The string representation of the TaskList in bullet points.
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("");

        for (int i = 0; i < taskList.size(); i++) {
            s.append("\n    ");
            s.append(String.format("%d. %s", i + 1, this.taskList.get(i)));
        }
        return s.toString();
    }

    /**
     * Represents the format of TaskList in storage.
     *
     * @return The string representation of TaskList in storage.
     */
    public String toStringInDoc() {
        StringBuilder s = new StringBuilder("");

        for (int i = 0; i < taskList.size(); i++) {
            s.append(this.taskList.get(i).toStringInDoc());
            if (i == taskList.size() - 1) {
                break;
            }
            s.append("\n");
        }
        return s.toString();
    }
}
