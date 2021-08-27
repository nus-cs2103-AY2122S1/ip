package main.java.duke.tasklist;

import main.java.duke.Ui;
import main.java.duke.task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * TaskList stores the list of tasks.
 *
 * @author Zhen Xuan (Tutorial Group W12)
 * @version CS2103T AY21/22 S1
 */
public class TaskListDuke implements TaskList {
    private static final String ADD = "\t Got it. I've added this task:";
    private static final String LIST_INTRO = "\t Here are the tasks in your list:";
    private static final String DONE = "\t Nice! I've marked this task as done:\n\t   ";
    private static final String DELETE = "\t Noted. I've removed this task:\n\t   ";
    private final ArrayList<Task> LIST;
    private final Ui UI;

    /**
     * Constructor for TaskList.
     */
    public TaskListDuke(ArrayList<Task> list, Ui ui) {
        this.LIST = list;
        this.UI = ui;
    }

    /**
     * Adds the task into the list.
     *
     * @param task the task to be added into the list
     */
    @Override
    public void addTask(Task task) {
        this.LIST.add(task);
        UI.print(ADD);
        UI.print("\t   " + task);
        if (this.LIST.size() == 1) {
            UI.print("\t Now you have 1 task in the list.");
        } else {
            UI.print("\t Now you have " + this.LIST.size() + " tasks in the list.");
        }
    }

    /**
     * Changes the done status of the task.
     *
     * @param index the position of the task to be marked done
     */
    @Override
    public void setDone(int index) {
        Task task = this.LIST.get(index);
        task.setDone();
        UI.print(DONE + task);
    }

    /**
     * Deletes the task at a specified index.
     *
     * @param index the position of the task to be deleted
     */
    public void delete(int index) {
        Task task = this.LIST.get(index);
        this.LIST.remove(index);
        UI.print(DELETE + task);

    }

    /**
     * Prints the list.
     */
    public void printList() {
        UI.print(LIST_INTRO);
        if (this.LIST.size() == 0) {
            UI.print("\t List is empty");
        } else {
            for (int i = 0; i < this.LIST.size(); i++) {
                UI.print("\t " + (i + 1) + "." + this.LIST.get(i));
            }
        }
    }

    /**
     * Prints Tasks on the specified date.
     *
     * @param date the specified date
     */
    public void printListDate(String date) {
        LocalDate localDate = LocalDate.parse(date.replace(" ", ""), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        UI.print(LIST_INTRO);
        if (this.LIST.size() == 0) {
            UI.print("\t List is empty.");
        } else {
            int count = 0;
            for (Task t : this.LIST) {
                if (t.onDate(localDate)) {
                    UI.print("\t " + (++count) + "." + t);
                }
            }
            if (count == 0) UI.print("There are no tasks pertaining to the specified date.");
        }
    }

    /**
     * Prints Tasks with containing the specific substring.
     *
     * @param str the specified substring
     */
    public void printListSearch(String str) {
        UI.print(LIST_INTRO);
        if (this.LIST.size() == 0) {
            UI.print("\t List is empty.");
        } else {
            int count = 0;
            for (Task t : this.LIST) {
                if (t.containString(str)) {
                    UI.print("\t " + (++count) + "." + t);
                }
            }
            if (count == 0) UI.print("\t Oh no! There are no tasks containing the specified string.");
        }
    }

    /**
     * Return the number of items in the TaskList.
     *
     * @return listCount
     */
    public int count() {
        return this.LIST.size();
    }
}
