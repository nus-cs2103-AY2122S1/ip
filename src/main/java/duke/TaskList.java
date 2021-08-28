package duke;

import java.util.ArrayList;
import java.util.List;

/**
 *  This class represents the list of all tasks.
 *  It also houses some list operations.
 *
 * @author Ryan Tian Jun.
 */
public class TaskList {
    private static List<Task> taskList = new ArrayList<Task>();

    public TaskList() {

    }

    /**
     * Adds a Task to the List.
    */
    public void add(Task task) {
        taskList.add(task);
    }

    /**
     * Searches for Tasks containing a search query.
     *
     * @return returns List of all Tasks that contain the Query.
     */
    public List<Task> search(String query) {
        List<Task> results = new ArrayList<>();
        for (Task task : taskList) {
            if (task.getDescription().contains(query)) {
                results.add(task);
            }
        }
        if (results.size() == 0) {
            System.out.println("No matches found!");
        } else {
            System.out.println("Here's what I found!:");
            int counter = 1;
            for (Task result : results) {
                String printResult = counter + "." + result;
                System.out.println(printResult);
            }
            newLine();
        }
        return results;
    }

    /**
     * Prints past commands.
     *
     * @return returns a printout of all the past user commands.
     */
    public static String userCommands() {
        int count = 1;

        if (taskList.size() == 0) {
            System.out.println("List is empty!");
        }
        for (Task item : taskList) {
            System.out.println(count + ". " + item);
            count++;
        }
        newLine();
        return taskList.toString();
    }

    /**
     * Marks tasks as done.
     *
     * @return returns a String message.
     * @throws DukeException Handles out of range errors.
     */
    public String markDone(int taskNumber) throws DukeException {
        if (taskNumber <= 0) {
            throw new DukeException("Mark Done:", 2);
        } else {
            if (taskNumber <= taskList.size()) {
                Task taskToChange = taskList.get(taskNumber - 1);
                taskToChange.markAsDone();

                System.out.println("Nice! I've marked this task as done: ");
                System.out.println("   " + taskToChange);
                newLine();

                return "   " + taskToChange;
            } else {
                throw new DukeException("Task does not exist");
            }
        }
    }

    /**
     * Deletes Tasks from the list.
     *
     * @return returns a String message.
     * @throws DukeException Handles out of range errors.
     */
    public String deleteTask(int taskNumber) throws DukeException {
        if (taskNumber <= 0) {
            throw new DukeException("Delete Task:", 2);
        } else {
            if (taskNumber <= taskList.size()) {
                System.out.println("Noted. I've removed this task: ");

                Task taskToDelete = taskList.get(taskNumber - 1);
                taskList.remove(taskNumber - 1);

                System.out.println("   " + taskToDelete);
                System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                newLine();
                return "   " + taskToDelete;
            } else {
                throw new DukeException("Unable to Delete: Task does not exist");
            }
        }
    }


    /**
     * Saves list of commands to hard drive upon program exit.
     */
    public static void saveList() {
        Storage.wipeOldSave();
        for (Task task : taskList) {
            Storage.writeTask(task);
        }
    }

    // Simply creates a new line in the terminal
    private static String newLine() {
        System.out.println("\n");
        return "\n";
    }

    /**
     * Returns the size of the List.
     *
     * @return The String representation of the size of the list.
     */
    @Override
    public String toString() {
        return Integer.toString(taskList.size());
    }

}
