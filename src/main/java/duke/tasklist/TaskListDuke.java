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
     */
    @Override
    public String addTask(Task task) {
        this.list.add(task);
        String str = ADD + task.toString();
        if (this.list.size() == 1) {
            str += "\nNow you have 1 task in the list.";
        } else {
            str += "\nNow you have " + this.list.size() + " tasks in the list.";
        }
        return str;
    }

    /**
     * Changes the done status of the task.
     *
     * @param index the position of the task to be marked done
     */
    @Override
    public String setDone(int index) {
        Task task = this.list.get(index);
        task.setDone();
        return DONE + task;
    }

    /**
     * Deletes the task at a specified index.
     *
     * @param index the position of the task to be deleted
     */
    public String delete(int index) {
        Task task = this.list.get(index);
        this.list.remove(index);
        return DELETE + task.toString();
    }

    /**
     * Prints the list.
     */
    public String printList() {
        StringBuilder str = new StringBuilder(LIST_INTRO);
        if (this.list.size() == 0) {
            str.append("List is empty!");
        } else {
            for (int i = 0; i < this.list.size(); i++) {
                str.append(i + 1).append(".").append(this.list.get(i).toString()).append("\n");
            }
        }
        return str.toString();
    }

    /**
     * Prints Tasks on the specified date.
     *
     * @param date the specified date
     */
    public String printListDate(String date) {
        LocalDate localDate = LocalDate.parse(date.replace(" ", ""), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        StringBuilder str = new StringBuilder(LIST_INTRO);
        if (this.list.size() == 0) {
            str.append("List is empty!");
        } else {
            int count = 0;
            for (Task t : this.list) {
                if (t.onDate(localDate)) {
                    str.append(++count).append(".").append(t).append("\n");
                }
            }
            if (count == 0) {
                str.append("There are no tasks pertaining to the specified date.");
            }
        }
        return str.toString();
    }

    /**
     * Prints Tasks with containing the specific substring.
     *
     * @param searchStr the specified substring
     */
    public String printListSearch(String searchStr) {
        StringBuilder str = new StringBuilder(LIST_INTRO);
        if (this.list.size() == 0) {
            str.append("List is empty!");
        } else {
            int count = 0;
            for (Task t : this.list) {
                if (t.containString(searchStr)) {
                    str.append(++count).append(".").append(t).append("\n");
                }
            }
            if (count == 0) {
                str.append("Oh no! There are no tasks containing the specified string.");
            }
        }
        return str.toString();
    }

    /**
     * Return the number of items in the TaskList.
     *
     * @return listCount
     */
    public int count() {
        return this.list.size();
    }
}
