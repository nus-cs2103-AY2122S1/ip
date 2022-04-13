package duke.tasklist;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import duke.task.Task;

/**
 * TaskList stores the list of tasks.
 *
 * @author Zhen Xuan (Tutorial Group W12)
 * @version CS2103T AY21/22 S1
 */
public class TaskListDuke implements TaskList {
    private static final String ADD = "Got it. I've added this task:\n";
    private static final String LIST_INTRO = "Here are the tasks in your list:\n";
    private static final String DONE = "Nice! I've marked this task as done:\n";
    private static final String DELETE = "Noted. I've removed this task:\n";
    private ArrayList<Task> list;

    /**
     * Constructor for TaskList.
     */
    public TaskListDuke(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Adds the task into the list.
     *
     * @param task the task to be added into the list
     * @return the string representation of the task added
     */
    @Override
    public String addTask(Task task) {
        int count = count();
        this.list.add(task);
        String str = ADD + task.toString();
        if (this.list.size() == 1) {
            str += "\nNow you have 1 task in the list.";
        } else {
            str += "\nNow you have " + this.list.size() + " tasks in the list.";
        }
        assert count + 1 == count() : "Number of tasks should have decreased by 1.";
        return str;
    }

    /**
     * Changes the done status of the task.
     *
     * @param index the position of the task to be marked done
     * @return the string representation of setting the task as done
     */
    @Override
    public String setDone(int index) {
        int count = count();
        assert count > index : "Index should not be greater than the item to be marked done.";
        Task task = this.list.get(index);
        task.setDone();
        assert task.getDone() : "Task should have been marked done.";
        return DONE + task;
    }

    /**
     * Deletes the task at a specified index.
     *
     * @param index the position of the task to be deleted
     * @return the string representation of deleting the task
     */
    public String delete(int index) {
        int count = count();
        assert count > index : "Index should not be greater than the item to be deleted.";
        Task task = this.list.get(index);
        this.list.remove(index);
        assert count - 1 == count() : "Number of tasks should have decreased by 1.";
        return DELETE + task.toString();
    }

    /**
     * Prints the list.
     *
     * @return the string representation of the list
     */
    public String printList() {
        StringBuilder str = new StringBuilder(LIST_INTRO);
        if (this.list.size() == 0) {
            str.append("List is empty!");
        } else {
            final int[] count = {0};
            this.list.forEach(task -> str.append(++count[0]).append(".").append(task).append("\n"));
        }
        return str.toString();
    }

    /**
     * Prints Tasks on the specified date.
     *
     * @param date the specified date
     * @return the string representation of the tasks whose date falls on the specified date
     */
    public String printListDate(String date) {
        LocalDate localDate = LocalDate.parse(date.replace(" ", ""), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        StringBuilder str = new StringBuilder(LIST_INTRO);
        if (this.list.size() == 0) {
            str.append("List is empty!");
        } else {
            final int[] count = {0};
            this.list.stream()
                    .filter(task -> task.onDate(localDate))
                    .forEach(task -> str.append(++count[0]).append(".").append(task).append("\n"));
            if (count[0] == 0) {
                str.append("There are no tasks pertaining to the specified date.");
            }
        }
        return str.toString();
    }

    /**
     * Prints Tasks with containing the specific substring.
     *
     * @param searchStr the specified substring
     * @return the string representation of the task whose description matches the
     * specified substring
     */
    public String printListSearch(String searchStr) {
        StringBuilder str = new StringBuilder(LIST_INTRO);
        if (this.list.size() == 0) {
            str.append("List is empty!");
        } else {
            assert this.list.size() > 0 : "List should contain something!";
            final int[] count = {0};
            this.list.stream()
                    .filter(task -> task.containString(searchStr))
                    .forEach(task -> str.append(++count[0]).append(".").append(task).append("\n"));
            if (count[0] == 0) {
                str.append("Oh no! There are no tasks containing the specified string.");
            }
        }
        return str.toString();
    }

    /**
     * Returns the number of items in the TaskList.
     *
     * @return the number of items in the tasklist
     */
    public int count() {
        return this.list.size();
    }
}
