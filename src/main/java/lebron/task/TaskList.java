package lebron.task;

import lebron.Ui;
import lebron.Lebron;

import java.util.ArrayList;

/**
 * Represents the list of tasks.
 *
 * @author Nigel Tan
 */
public class TaskList {

    private ArrayList<Task> tasks;
    private int position;
    private Ui ui;

    /**
     * Constructor.
     *
     * @param lst The list of tasks.
     */
    public TaskList(ArrayList<Task> lst, Lebron lebron) {
        this.tasks = lst;
        this.ui = new Ui(lebron);
        this.position = 1;
    }

    /**
     * This method handles the add event.
     *
     * @param task the Task that the user wants to add
     */
    public String add(Task task) {
        tasks.add(task);
        String reply = ui.replyAdd(this.tasks, task);
        return reply;
    }

    /**
     * This method handles the markDone event when the user says done, marking the Task as done.
     *
     * @param pos the position of the Task in the ArrayList lst
     */
    public String markDone(int pos) {
        Task task = tasks.get(pos);
        task.mark();
        String reply = ui.replyMarkDone(task);
        return reply;
    }

    /**
     * This method handles the delete response.
     *
     * @param pos the position of the task in the list to delete
     */
    public String delete(int pos) {
        Task task = tasks.remove(pos);
        String reply = ui.replyDelete(task, tasks.size());
        return reply;
    }

    public ArrayList<Task> getLst() {
        return this.tasks;
    }

    public int getSize() {
        return this.tasks.size();
    }

    public Task getItem(int position) {
        return this.tasks.get(position);
    }
}
