package duke;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Function;

import duke.task.Task;
import duke.util.DukeException;
import duke.util.Utility;
import duke.util.sort.DoneSorter;
import duke.util.sort.NameSorter;
import duke.util.sort.TimeSorter;

/**
 * Stores and manages the user's list of tasks.
 */
public class TaskBank {

    private final ArrayList<Task> taskList;
    private final Storage storage;

    /**
     * Initialise a new TaskBank given a storage path.
     *
     * @param filepath path to storage file
     */
    public TaskBank(String filepath) {
        this.storage = new Storage(filepath);
        this.taskList = storage.readFromDisk();
    }

    /**
     * Add a task to the list of tasks.
     * Throws a Duke Exception if format is erroneous
     *
     * @param formattedString task to add represented as a formatted string
     * @param create function to map the formatted string into a task
     */
    //TODO: change function parameter to enum
    public void addTask(String formattedString, Function<String, ? extends Task> create) {
        Task e = create.apply(formattedString);
        this.taskList.add(e);
        Ui.print(String.format("Accepted, I've added the following task\n%s", e));
        this.storage.writeToDisk(this.taskList);
    }

    /**
     * Marks a task in the task list as done.
     * Throws a Duke Exception if given id is not found.
     *
     * @param input task to mark represented as a formatted string (done [id])
     */
    public void markTask(String input) {
        int taskId = -1;
        try {
            taskId = Utility.getIdFromString(input, "done ");
            Task t = this.taskList.get(taskId - 1);
            t.markAsDone();
            Ui.print("Approved, I've marked this duke.task as done\n" + t);
        } catch (IndexOutOfBoundsException e) {
            Ui.print(String.format("Nonsense, Task.Task #%d doesn't exist\n", taskId));
        }
        this.storage.writeToDisk(this.taskList);
    }

    /**
     * Deletes a task in the task list.
     * Throws a Duke Exception if given id is not found.
     *
     * @param input task to delete represented as a formatted string (delete [id])
     */
    public void deleteTask(String input) {
        int taskId = -1;
        try {
            taskId = Utility.getIdFromString(input, "delete ");
            Task t = this.taskList.get(taskId - 1);
            this.taskList.remove(taskId - 1);
            Ui.print("Approved, I've removed this duke.task\n" + t);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(String.format("Nonsense, Task.Task #%d doesn't exist\n", taskId));
        }
        this.storage.writeToDisk(this.taskList);
    }

    /**
     * Searches for tasks that contain the given substring.
     *
     * @param query substring to search
     * @return list of matching tasks
     */
    public ArrayList<Task> searchTasks(String query) {
        query = query.split(" ", 2)[1].trim();

        ArrayList<Task> matchingTasks = new ArrayList<>();

        for (Task t : this.taskList) {
            if (t.getDescription().contains(query)) {
                matchingTasks.add(t);
            }
        }

        return matchingTasks;
    }

    private ArrayList<Task> sortTasks(Comparator<Task> cmp) {
        ArrayList<Task> taskListCopy = new ArrayList<>(taskList);

        taskListCopy.sort(cmp);

        return taskListCopy;

    }

    /**
     * Returns a copy of the list of tasks.
     * If a sort order is specified, sort the copy before returning.
     *
     * @param input input string
     */
    public ArrayList<Task> getTasks(String input) {
        String[] inputWords = input.split(" ");

        if (inputWords.length == 1) {
            return new ArrayList<>(this.taskList);
        } else if (inputWords[1].equals("-name")) {
            return sortTasks(new TimeSorter());
        } else if (inputWords[1].equals("-time")) {
            return sortTasks(new NameSorter());
        } else if (inputWords[1].equals("-done")) {
            return sortTasks(new DoneSorter());
        } else {
            throw new DukeException("I don't understand how you want me to list the tasks");
        }
    }
}
