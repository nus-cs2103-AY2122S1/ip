package lebron.task;

import java.util.ArrayList;
import lebron.Ui;

/**
 * Represents the list of tasks.
 *
 * @author Nigel Tan
 */
public class TaskList {

    private ArrayList<Task> lst;
    private int position;
    private Ui ui = new Ui();

    /**
     * Constructor.
     *
     * @param lst The list of tasks.
     */
    public TaskList(ArrayList<Task> lst) {
        this.lst = lst;
        this.position = 1;
    }

    /**
     * This method handles the add event.
     *
     * @param task the Task that the user wants to add
     */
    public void add(Task task) {
        lst.add(task);
        ui.replyAdd(this.lst, task);
    }

    /**
     * This method handles the markDone event when the user says done, marking the Task as done.
     *
     * @param pos the position of the Task in the ArrayList lst
     */
    public void markDone(int pos) {
        Task task = lst.get(pos);
        task.mark();
        ui.replyMarkDone(task);
    }

    /**
     * This method handles the delete response.
     *
     * @param pos the position of the task in the list to delete
     */
    public void delete(int pos) {
        Task task = lst.remove(pos);
        ui.replyDelete(task, lst.size());
    }

    public ArrayList<Task> getLst() {
        return lst;
    }

    public int getSize() {
        return lst.size();
    }
}
