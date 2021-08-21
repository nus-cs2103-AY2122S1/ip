package duke;

import java.util.ArrayList;
import java.util.List;

/**
 *  This class represnts the list of all tasks.
 *  It also houses some list operations.
 *
 * @author Ryan Tian Jun.
 */
public class TaskList {
    private static List<Task> taskList = new ArrayList<Task>();

    public TaskList() {

    }

    public void add(Task task) {
        taskList.add(task);
    }

    public int size() {
        return taskList.size();
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
            System.out.println(count + ". "  + item);
            count++;
        }
        newLine();
        return taskList.toString();
    }

    /**
     * Marks tasks as done.
     *
     * @throws DukeException Handles out of range errors.
     * @return returns a String message.
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
     * @throws DukeException Handles out of range errors.
     * @return returns a String message.
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

    @Override
    public String toString() {
        return Integer.toString(taskList.size());
    }

}
