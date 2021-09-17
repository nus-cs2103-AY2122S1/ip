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
public class TaskListStub implements TaskList {
    private ArrayList<Task> list;
    private ArrayList<Task> output;

    /**
     * Constructor for TaskList.
     */
    public TaskListStub(ArrayList<Task> list) {
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
        return "";
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
        return "";
    }

    /**
     * Deletes the task at a specified index.
     *
     * @param index the position of the task to be deleted
     */
    @Override
    public String delete(int index) {
        Task task = this.list.get(index);
        this.list.remove(index);
        return "";
    }

    /**
     * Prints the list.
     */
    @Override
    public String printList() {
        this.output = this.list;
        return "";
    }

    /**
     * Prints Tasks on the specified date.
     *
     * @param date the specified date
     */
    public String printListDate(String date) {
        LocalDate localDate = LocalDate.parse(date.replace(" ", ""), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.output = new ArrayList<>();
        for (Task t : this.list) {
            if (t.onDate(localDate)) {
                this.output.add(t);
            }
        }
        return "";
    }

    /**
     * Returns the number of items in the TaskList.
     *
     * @return listCount
     */
    public int count() {
        return this.list.size();
    }

    /**
     * Returns output for testing.
     *
     * @return the output
     */
    public ArrayList<Task> output() {
        return this.output;
    }

    /**
     * Returns nothing.
     *
     * @param searchString the string to be searched
     */
    @Override
    public String printListSearch(String searchString) {
        return "";
    }
}
