import java.util.ArrayList;

/**
 * MyList class that encapsulates the bot list object and functionalities.
 *
 * @Author Houten Teo
 * @version CS2103T week 2
 */
public class MyList {

    /**
     * An ArrayList Object to store all the items in the list.
     */
    private ArrayList<Task> myList = new ArrayList<Task>();

    /**
     * Method to add an item into the list
     * Subsequently prints out the total number of items in the list.
     * @param t the Task to be added into the list
     */
    public void addTask(Task t) {
        myList.add(t);
        System.out.println("Got it! I have added:");
        System.out.println(t.toString());
        int noOfItems = this.myList.size();
        if (noOfItems == 1) {
            System.out.printf("You now have %d item in your list \n", noOfItems);
        } else {
            System.out.printf("You now have %d items in your list \n", noOfItems);
        }

    }

    /**
     * Method to list out all the items in the list.
     */
    public void listAll() {
        int listLength = myList.size();
        if (listLength == 0) {
            System.out.println("Your list is empty.");
        } else {
            System.out.println("Your list items:");
            for (int i = 0; i < listLength; i ++) {
                Task t = myList.get(i);
                String statusIcon = t.getStatusIcon();
                String typeIcon = t.getTypeIcon();
                System.out.printf("%d. %s \n", i + 1, t.toString());
            }
        }
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
            System.out.println("Invalid index, please try again");
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
        System.out.println("Noted. I've removed this task:");
        System.out.println(removed.toString());
        System.out.printf("You now have %d items in your list \n", this.myList.size());
    }
}