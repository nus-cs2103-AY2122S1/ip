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
    public TaskList(ArrayList<Task> lst) {
        this.tasks = lst;
        this.ui = new Ui();
        this.position = 1;
    }

    /**
     * This method handles the add event.
     *
     * @param task the Task that the user wants to add.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * This method handles the markDone event when the user says done, marking the Task as done.
     *
     * @param pos the position of the Task in the ArrayList lst.
     * @return the task that was marked done.
     */
    public Task markDone(int pos) {
        Task task = tasks.get(pos);
        task.markAsDone();
        return task;
    }

    /**
     * This method handles the delete response.
     *
     * @param pos the position of the task in the list to delete.
     * @return the task that was deleted.
     */
    public Task delete(int pos) throws LebronException {
        if (pos > tasks.size() - 1) {
            throw new LebronException("Invalid index!");
        }
        Task task = tasks.remove(pos);
        return task;
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

    /**
     * Gets a copy of the TaskList.
     *
     * @param taskList the TaskList to copy.
     * @return the copied TaskList.
     */
    public TaskList getCopy(TaskList taskList) throws LebronException {
        ArrayList<Task> lst = new ArrayList<>();
        for (int i = 0; i < taskList.getSize(); i++) {
            Task task = taskList.getItem(i);
            Task newTask = task.makeCopy();
            //if done
            if (task.getDoneValue().equals("1")) {
                newTask.markAsDone();
            }
            lst.add(newTask);
        }
        return new TaskList(lst);
    }
}
