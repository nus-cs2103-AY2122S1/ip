package duke;
import duke.task.Task;
import java.util.ArrayList;

/**
 * Encapsulates a list of Tasks that the user sets, with relevant processing methods.
 *
 * @author Hanif Kamal
 */
public class TaskList {
    private final ArrayList<Task> list;

    /**
     * Class constructor to initialize the TaskList instance.
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Looks up the Task at the given index in the TaskList and sets the Task as done.
     * @param index The index of the Task, to be set as done, in the TaskList.
     */
    public void completeTask(int index) {
        Task task = this.list.get(index - 1);
        task.setDone();
        System.out.println("Nice! I've marked this task as done:\n" + "  " + task);
    }

    /**
     * Adds the given Task to the TaskList.
     * @param task The Task to be added to the TaskList.
     */
    public void addToList(Task task) {
        this.list.add(task);
    }

    /**
     * Prints all Tasks in the TaskList for the user.
     *
     * @throws DukeException In the case where the TaskList is empty.
     */
    public void printTasks() throws DukeException {
        if (this.list.size() < 1) {
            throw new DukeException("You haven't added anything to the list yet! Try adding something.");
        } else {
            if (this.list.size() == 1) {
                System.out.println("Here is the sole task in your list:");
            } else {
                System.out.println("Here are the tasks in your list:");
            }
            for (int i = 1; i <= list.size(); i++) {
                System.out.println(i + ". " + list.get(i - 1));
            }
        }
    }

    /**
     * Looks up the Task at the given index in the TaskList and removes the Task from the list.
     * @param index The index of the Task, to be deleted, in the TaskList.
     * @throws DukeException In the case where the TaskList is empty, or the index is out of bounds.
     */
    public void deleteTask(int index) throws DukeException {
        if (this.list.size() < 1) {
            throw new DukeException("You haven't added anything to the list yet! Try adding something before " +
                    "deleting.");
        } else if (index <= this.list.size() && index >= 1) {
            Task toDelete = list.get(index - 1);
            list.remove(index - 1);
            System.out.println("Noted. I've removed this task:\n" + "  " + toDelete + "\n" + "Now you have " +
                    list.size() + " task" + (list.size() == 1 ? "" : "s") + " in the list.");
        } else {
            throw new DukeException("Couldn't find that task in the list! Try again.");
        }
    }


    public int size() {
        return this.list.size();
    }

    public Task getTask(int index) {
        return this.list.get(index);
    }

    public void printFilteredTasks(String searchTerm) throws DukeException {
        ArrayList<Task> filteredList = new ArrayList<>();
        for (Task task : list) {
            if (task.getTaskName().contains(searchTerm)) {
                filteredList.add(task);
            }
        }
        if (filteredList.size() < 1) {
            throw new DukeException("I couldn't find any tasks with that particular search term. Try again.");
        } else {
            if (filteredList.size() == 1) {
                System.out.println("Here is the sole matching task in your list:");
            } else {
                System.out.println("Here are the matching tasks in your list:");
            }
            for (int i = 1; i <= filteredList.size(); i++) {
                System.out.println(i + ". " + filteredList.get(i - 1));
            }
        }
    }

}
