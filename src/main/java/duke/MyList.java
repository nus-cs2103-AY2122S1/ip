package duke;

import java.util.ArrayList;

import duke.tasktype.Task;

/**
 * duke.MyList class that encapsulates the bot list object and functionalities.
 *
 * @Author Houten Teo
 * @version CS2103T week 3
 */
public class MyList {

    /**
     * An ArrayList Object to store all the items in the list.
     */
    private ArrayList<Task> myList;
    private Ui ui;

    /**
     * Constructor for the duke.MyList class.
     * Adds the duke.tasks from the Data.txt file into the list if any.
     */
    public MyList() {
          this.myList = new ArrayList<Task>();
    }

    /**
     * Method to return the size of the list
     * @return The number of items in the list.
     */
    public int getListSize() {
        return this.myList.size();
    }

    /**
     * Method the return the task at the specified index.
     * @param index The specified index.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return this.myList.get(index);
    }

    /**
     * Method to add an item into the list
     * Subsequently prints out the total number of items in the list.
     * @param t the duke.tasks.Task to be added into the list
     */
    public void addTask(Task t) {
        myList.add(t);
        Ui.addTaskMessage(t, this);
    }

    /**
     * Method to list out all the items in the list.
     */
    public String listAll() {
        return Ui.listAllMessage(this);
    }

    /**
     * Method to mark a certain task in the list as completed.
     * @param index The index of the item in the list to be completed.
     *              If the index specified is invalid, prompt the user
     *              for another input.
     */
    public void markComplete(int index) {
        try {
            myList.get(index - 1).markComplete();
        } catch (IndexOutOfBoundsException e) {
            Ui.invalidIndexMessage();
        }
    }

    /**
     * Method to delete a certain task from the list.
     * @param index The index of the item in the list to be deleted.
     *              If the index specified is invalid, prompt the user
     *              for another input.
     */
    public void deleteTask(int index) {
        Task removed = this.myList.remove(index - 1);
        Ui.deleteTaskMessage(removed, this);
        System.out.println("Noted. I've removed this task:");
        System.out.println(removed.toString());
        int noOfItems = this.myList.size();
        if (noOfItems == 1) {
            System.out.printf("You now have %d item in your list \n", noOfItems);
        } else {
            System.out.printf("You now have %d items in your list \n", noOfItems);
        }
    }

    /**
     * Method to find all tasks with a description containing the keyword.
     * Not case-sensitive.
     * @param keyword The keyword to be looking for.
     */
    public void find(String keyword) {
        int counter = 0;
        Task[] matchingList = new Task[getListSize()];
        for (int i = 0; i < getListSize(); i++) {
            Task t = getTask(i);
            String taskDescription = t.getDescription().toLowerCase();
            if (taskDescription.contains(keyword.toLowerCase())) {
                matchingList[i] = t;
                counter++;
            }
        }
        Ui.containsKeyword(counter);
        Ui.containsKeywordTask(matchingList);
    }

    /**
     * Method to load a task into the list.
     * This method does not print anything to the screen.
     * Used in conjuntion with the load method in storage.
     * @param t The task to be laaded into the list.
     */
    public void loadTask(Task t) {
        this.myList.add(t);
    }
}