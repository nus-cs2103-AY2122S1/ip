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
public class TaskListStub implements TaskList {
    private ArrayList<Task> list;
    private Ui ui;
    private ArrayList<Task> output;

    /**
     * Constructor for TaskList.
     */
    public TaskListStub(ArrayList<Task> list, Ui ui) {
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
    }

    /**
     * Deletes the duke.task at a specified index.
     *
     * @param index the position of the duke.task to be deleted
     */
    @Override
    public void delete(int index) {
        Task task = this.list.get(index);
        this.list.remove(index);
    }

    /**
     * Prints the list.
     */
    @Override
    public void printList() {
        this.output = this.list;
    }

    /**
     * Prints Tasks on the specified date.
     *
     * @param date the specified date
     */
    public void printListDate(String date) {
        LocalDate localDate = LocalDate.parse(date.replace(" ", ""), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.output = new ArrayList<>();
        for (Task t : this.list) {
            if (t.onDate(localDate)) {
                this.output.add(t);
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

    /**
     * Return output for testing.
     *
     * @return the output
     */
    public ArrayList<Task> output() {
        return this.output;
    }

    /**
     * Return nothing.
     *
     * @param searchString the string to be searched
     */
    @Override
    public void printListSearch(String searchString) {
        return;
    }
}
