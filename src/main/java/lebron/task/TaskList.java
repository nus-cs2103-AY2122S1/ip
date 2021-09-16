package lebron.task;

import java.util.ArrayList;

import lebron.Lebron;
import lebron.Ui;
import lebron.exception.LebronException;

/**
 * Represents the list of tasks.
 *
 * @author Nigel Tan
 */
public class TaskList {

    private final ArrayList<Task> tasks;
    private final int position;
    private final Ui ui;

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
     * @return the reply from the bot
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
     * @return the reply from the bot
     */
    public String markDone(int pos) {
        Task task = tasks.get(pos);
        task.markAsDone();
        String reply = ui.replyMarkDone(task);
        return reply;
    }

    /**
     * This method handles the delete response.
     *
     * @param pos the position of the task in the list to delete
     * @return the reply from the bot
     */
    public String delete(int pos) throws LebronException {
        if (pos > tasks.size() - 1) {
            throw new LebronException("Invalid index!");
        }
        Task task = tasks.remove(pos);
        String reply = ui.replyDelete(task, tasks.size());
        return reply;
    }

    /**
     * Handles the undo event.
     *
     * @throws LebronException if the list is empty.
     */
    public void undo() throws LebronException {
       if (this.tasks.isEmpty()) {
           throw new LebronException("Oops! Your list is already empty.");
       }
       tasks.remove(tasks.size() - 1);
    }

    public ArrayList<Task> getLst() {
        return this.tasks;
    }

    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Retrieve the task from the list given an index.
     *
     * @param position the index.
     * @return the task.
     */
    public Task getItem(int position) {
        return this.tasks.get(position);
    }
}
