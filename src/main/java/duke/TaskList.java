package duke;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages the arraylist of tasks
 */
public class TaskList {
    List<Task> listOfTasks;
    Storage storage = new Storage();

    TaskList() {
        this.listOfTasks = new ArrayList<Task>();

        try {
            storage.createFile();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Adds task to arraylist
     *
     * @param task
     */
    public void list(Task task) {
        listOfTasks.add(task);
        int counter = listOfTasks.size();
        storage.save(listOfTasks);
        System.out.println("Got it. I've added this task:\n" + "  " + task.toString() + "\nNow you have " + counter + " tasks in the list.");
    }

    /**
     * deletes task in arraylist at index-1
     *
     * @param index
     */
    public void deleteTask(int index) {
        Task item = listOfTasks.get(index - 1);
        listOfTasks.remove(index - 1);
        storage.save(listOfTasks);
        System.out.println("Noted. I've removed this task:\n  " + item + "\nNow you have " + listOfTasks.size() + " tasks left in the list");
    }

    /**
     * Prints the task in the arraylist
     */
    public void printList() {
        if (listOfTasks.size() > 0) {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < listOfTasks.size(); i++) {
                System.out.println(i + 1 + "." + listOfTasks.get(i));
            }
        } else {
            System.out.println("There are no tasks in your list");
        }
    }

    /**
     * Mark as done at index number-1
     *
     * @param number
     */
    public void changeStatus(int number) {
        if (listOfTasks.size() >= number) {
            listOfTasks.get(number - 1).markAsDone();
            storage.save(listOfTasks);
            System.out.println("Nice! I've marked this task as done:\n  " + listOfTasks.get(number - 1));
            return;
        }
    }
}
