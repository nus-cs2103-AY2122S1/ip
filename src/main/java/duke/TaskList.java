package duke;

import java.util.ArrayList;

import duke.task.Task;

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
    public String completeTask(int index) {
        Task task = this.list.get(index - 1);
        task.setDone();
        return ("Nice! I've marked this task as done:\n" + "  " + task);
    }

    /**
     * Adds the given Task to the TaskList.
     * @param task The Task to be added to the TaskList.
     */
    public String addToList(Task task) {
        this.list.add(task);
        return "Got it. I've added this task:\n" + "  " + task.toString() + "\n"
                + "Now you have " + list.size() + " task" + (list.size() == 1 ? "" : "s")
                + " in the list.";
    }

    /**
     * Returns, as a String, all Tasks in the TaskList for the user.
     *
     * @throws DukeException In the case where the TaskList is empty.
     */
    public String printTasks() throws DukeException {
        if (this.list.size() < 1) {
            throw new DukeException("You haven't added anything to the list yet! Try adding something.");
        } else {
            String taskQuantifier;
            if (this.list.size() == 1) {
                taskQuantifier = "Here is the sole task in your list:";
            } else {
                taskQuantifier = "Here are the tasks in your list:";
            }
            String tasks = "";
            for (int i = 1; i <= list.size(); i++) {
                tasks = tasks + ("\n" + i + ". " + list.get(i - 1));
            }
            return taskQuantifier + tasks;
        }
    }

    /**
     * Looks up the Task at the given index in the TaskList and removes the Task from the list.
     * @param index The index of the Task, to be deleted, in the TaskList.
     * @throws DukeException In the case where the TaskList is empty, or the index is out of bounds.
     */
    public String deleteTask(int index) throws DukeException {
        if (this.list.size() < 1) {
            throw new DukeException("You haven't added anything to the list yet! Try adding something before "
                    + "deleting.");
        } else if (index <= this.list.size() && index >= 1) {
            Task toDelete = list.get(index - 1);
            list.remove(index - 1);
            return ("Noted. I've removed this task:\n" + "  " + toDelete + "\n" + "Now you have "
                    + list.size() + " task" + (list.size() == 1 ? "" : "s") + " in the list.");
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

    /**
     * Prints a Task List that is filtered such that it only consists of Tasks that contain the given search term.
     * @param searchTerm The given search term to narrow down the Tasks in the Task List.
     * @throws DukeException In the case where no Tasks matching the search term can be found.
     */
    public String printFilteredTasks(String searchTerm) throws DukeException {
        ArrayList<Task> filteredList = new ArrayList<>();
        for (Task task : list) {
            if (task.getTaskName().contains(searchTerm)) {
                filteredList.add(task);
            }
        }
        if (filteredList.size() < 1) {
            throw new DukeException("I couldn't find any tasks with that particular search term. Try again.");
        } else {
            String filteredTaskQuantifier;
            if (filteredList.size() == 1) {
                filteredTaskQuantifier = "Here is the sole matching task in your list:";
            } else {
                filteredTaskQuantifier = "Here are the matching tasks in your list:";
            }
            String filteredTasks = "";
            for (int i = 1; i <= list.size(); i++) {
                filteredTasks = filteredTasks + ("\n" + i + ". " + filteredList.get(i - 1));
            }
            return filteredTaskQuantifier + filteredTasks;
        }
    }

}
