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
    public String search(String query) {
        List<Task> results = new ArrayList<>();
        String result = "Here's what I found!: \n";

        for (Task task : taskList) {
            if (task.getDescription().contains(query)) {
                results.add(task);
            }
        }

        if (results.size() == 0) {
            return "No matches found!";
        } else {
            int counter = 1;
            for (Task resultFound : results) {
                String printResult = counter + "." + resultFound + "\n";
                counter++;
                result += printResult;
            }
        }
        return result;
    }

    /**
     * Prints past commands.
     *
     * @return returns a printout of all the past user commands.
     */
    public static String userCommands() {
        int count = 1;
        String result = "";

        if (taskList.size() == 0) {
            return "List is Empty!";
        }
        for (Task item : taskList) {
            result += count + ". " + item + "\n";
            count++;
        }
        return result;
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

                return "Nice! I've marked this task as done: \n" + "   " + taskToChange;
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
                String result = "Noted. I've removed this task: \n";

                Task taskToDelete = taskList.get(taskNumber - 1);
                taskList.remove(taskNumber - 1);

                result += "   " + taskToDelete + "\n";
                result += "Now you have " + taskList.size() + " tasks in the list.";
                return result;
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
