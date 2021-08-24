package catobot.item;

import catobot.exception.BotException;
import catobot.exception.EmptyTaskListException;
import catobot.exception.OutOfBoundException;

import java.util.ArrayList;
import java.util.List;

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
     * Adds a task into the TaskList.
     *
     * @param item The task to be added.
     * @return The response message after adding the catobot.item.
     */
    public String add(Task item) {
        this.taskList.add(item);
        String front = "Got it meow! I've added this task:";
        String back = checkSize();
        return String.format("%s\n      %s\n    %s", front, item, back);
    }

    /**
     * Displays the current list of tasks.
     *
     * @return The bullet point lists of current tasks.
     */
    public String display() {
        if (taskList.size() == 0) {
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
                "Noted. I've removed this task:\n      %s\n      %s", temp, checkSize());
    }

    private void checkRange(int index) throws OutOfBoundException, EmptyTaskListException {
        if (taskList.size() == 0) {
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
            if (i == taskList.size() - 1) { break; }
            s.append("\n");
        }
        return s.toString();
    }
}
