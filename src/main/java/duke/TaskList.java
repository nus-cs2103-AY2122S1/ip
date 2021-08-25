package duke;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    List<Task> listOfTask;
    Storage storage = new Storage();

    TaskList() {
        this.listOfTask = new ArrayList<Task>();

        try {
            storage.createFile();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Adds task to arraylist
     * @param task
     */
    public void list(Task task) {
        listOfTask.add(task);
        int counter = listOfTask.size();
        storage.save(listOfTask);
        System.out.println("Got it. I've added this task:\n" + "  "+ task.toString() +"\nNow you have "+counter+" tasks in the list.");
    }

    /**
     * deletes task in arraylist at index-1
     * @param index
     */
    public void deleteTask(int index) {
        Task item = listOfTask.get(index-1);
        listOfTask.remove(index-1);
        storage.save(listOfTask);
        System.out.println("Noted. I've removed this task:\n  " + item + "\nNow you have " + listOfTask.size() + " tasks left in the list");
    }

    /**
     * Prints the task in the arraylist
     */
    public void printList() {
        if (listOfTask.size() > 0) {
            System.out.println("Here are the tasks in your list:");
            for(int i = 0; i < listOfTask.size(); i++) {
                System.out.println(i+1 + "." +  listOfTask.get(i));
            }
        } else {
            System.out.println("There are no tasks in your list");
        }
    }

    /**
     * Mark as done at index number-1
     * @param number
     */
    public void changeStatus(int number) {
        if(listOfTask.size() >= number) {
            listOfTask.get(number-1).markAsDone();
            storage.save(listOfTask);
            System.out.println("Nice! I've marked this task as done:\n  " + listOfTask.get(number-1) );
            return;
        }
    }
}
