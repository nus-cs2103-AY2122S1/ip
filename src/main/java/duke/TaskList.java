package duke;
import java.util.ArrayList;
import duke.tasks.Task;
import duke.status.Status;
import duke.exceptions.DoneOutOfBoundsException;
import duke.exceptions.DeleteOutOfBoundsException;


/**
 * Class that keeps track of all the tasks given by the user
 */
public class TaskList {
    private final ArrayList<Task> allTasks;

    /**
     * Initializes the TaskList class with arraylist to store tasks in future
     * 
     * @param allTasks arraylist of tasks to be stored
     */
    public TaskList(ArrayList<Task> allTasks) {
        this.allTasks = allTasks;
    }

    /**
     * Overloaded constructor that returns a TaskList class
     * with no prior tasks stored, which may due to reading error from storage class
     */
    public TaskList() {
        this.allTasks = new ArrayList<>();
    }

    /**
     * Adds a newly input task by user into the tasklist arraylist for keeping track
     * 
     * @param newTask Task object of the new task to be added
     * @return boolean whether a new task has been added to the task arraylist
     */
    public boolean addNewTask(Task newTask) {
        return allTasks.add(newTask);
    }

    /**
     * Returns all the tasks that users have input thus far.
     * 
     * @return ArrayList of all the tasks 
     */
    public ArrayList<Task> getTaskList() {
        return allTasks;
    }

    /**
     * Returns the task that user wishes to mark as completed.
     * 
     * @param taskNumber Integer of the position of task to be marked as completed
     * @return Task that is marked as completed
     * @throws DoneOutOfBoundsException if user specify the task number to be marked done
     * is greater than the total tasks in the tracking arraylist.
     */
    public Task markTaskAsDone(int taskNumber) throws DoneOutOfBoundsException {
        if (taskNumber > allTasks.size()) {
            throw new DoneOutOfBoundsException(allTasks.size());
        }
        Task updatedTask = allTasks.
        get(taskNumber - 1).
        updateStatus(Status.COMPLETED.getStatus());
        allTasks.set(taskNumber - 1, updatedTask);
        return updatedTask;
    }

    /**
     * Returns the task that user wishes to remove.
     * 
     * @param taskNumber Integer of the position of 
     * task to be removed from tracking arraylist.
     * @return Task that is going to be removed from tracking arraylist
     * @throws DeleteOutOfBoundsException if user specify the task number to be deleted
     * is greater than the total tasks in the tracking arraylist
     */
    public Task deleteTask(int taskNumber) throws DeleteOutOfBoundsException {
        if (taskNumber > allTasks.size()){ 
            throw new DeleteOutOfBoundsException(allTasks.size());
        }
        return allTasks.remove(taskNumber - 1);
    }

    /**
     * returns length of arraylist of tasks that are stored.
     * 
     * @return Integer of the length of all the tasks user has input
     */
    public int getTaskListLength() {
        return allTasks.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= allTasks.size(); i++) {
            if (i == allTasks.size()) {
                sb.append(i + ". " + allTasks.get(i - 1) + "\n");
                continue;
            }
            sb.append(i + ". " + allTasks.get(i - 1) + "\n");
        }
        return sb.toString();
    }
}
