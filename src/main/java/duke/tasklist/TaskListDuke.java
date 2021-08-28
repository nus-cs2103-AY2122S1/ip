package duke.tasklist;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import duke.Ui;
import duke.task.Task;

/**
 * TaskList stores the list of tasks.
 *
 * @author Zhen Xuan (Tutorial Group W12)
 * @version CS2103T AY21/22 S1
 */
public class TaskListDuke implements TaskList {
    private static final String ADD = "\t Got it. I've added this duke.task:";
    private static final String LIST_INTRO = "\t Here are the tasks in your list:";
    private static final String DONE = "\t Nice! I've marked this duke.task as done:\n\t   ";
    private static final String DELETE = "\t Noted. I've removed this duke.task:\n\t   ";
    private ArrayList<Task> list;
    private Ui ui;

    /**
     * Constructor for TaskList.
     */
    public TaskListDuke(ArrayList<Task> list, Ui ui) {
        this.list = list;
        this.ui = ui;
    }

    /**
     * Adds the duke.task into the list.
     *
     * @param task the duke.task to be added into the list
     */
    @Override
    public void addTask(Task task) {
        this.list.add(task);
        ui.print(ADD);
        ui.print("\t   " + task);
        if (this.list.size() == 1) {
            ui.print("\t Now you have 1 duke.task in the list.");
        } else {
            ui.print("\t Now you have " + this.list.size() + " tasks in the list.");
        }
    }

    /**
     * Changes the done status of the duke.task.
     *
     * @param index the position of the duke.task to be marked done
     */
    @Override
    public void setDone(int index) {
        Task task = this.list.get(index);
        task.setDone();
        ui.print(DONE + task);
    }

    /**
     * Deletes the duke.task at a specified index.
     *
     * @param index the position of the duke.task to be deleted
     */
    public void delete(int index) {
        Task task = this.list.get(index);
        this.list.remove(index);
        ui.print(DELETE + task);

    }

    /**
     * Prints the list.
     */
    public void printList() {
        ui.print(LIST_INTRO);
        if (this.list.size() == 0) {
            ui.print("\t List is empty");
        } else {
            for (int i = 0; i < this.list.size(); i++) {
                ui.print("\t " + (i + 1) + "." + this.list.get(i));
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
        ui.print(LIST_INTRO);
        if (this.list.size() == 0) {
            ui.print("\t List is empty.");
        } else {
            int count = 0;
            for (Task t : this.list) {
                if (t.onDate(localDate)) {
                    ui.print("\t " + (++count) + "." + t);
                }
            }
            if (count == 0) {
                ui.print("There are no tasks pertaining to the specified date.");
            }
        }
    }

    /**
     * Prints Tasks with containing the specific substring.
     *
     * @param str the specified substring
     */
    public void printListSearch(String str) {
        ui.print(LIST_INTRO);
        if (this.list.size() == 0) {
            ui.print("\t List is empty.");
        } else {
            int count = 0;
            for (Task t : this.list) {
                if (t.containString(str)) {
                    ui.print("\t " + (++count) + "." + t);
                }
            }
            if (count == 0) {
                ui.print("\t Oh no! There are no tasks containing the specified string.");
            }
        }
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
