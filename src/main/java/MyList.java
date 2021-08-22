import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

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
     * Constructor for the MyList class.
     * Adds the tasks from the Data.txt file into the list if any.
     */
    public MyList() {
        try {
            File dataFile = new File("src/main/java/Data.txt");
            Scanner s = new Scanner(dataFile);
            while (s.hasNextLine()) {
                String input = s.nextLine();
                Task t = Task.getTaskFromString(input);
                this.myList.add(t);
            }
        } catch (FileNotFoundException e) {
            System.out.println("No data to load");
        }
    }

    /**
     * Method to add an item into the list
     * Subsequently prints out the total number of items in the list.
     * @param t the Task to be added into the list
     */
    public void addTask(Task t) {
        myList.add(t);
        writeToFile();
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
        writeToFile();
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
     * Method to update the Data.txt file with all the items in the list.
     * This would allow the saving of data even when Duke restarts
     */
    private void writeToFile() {
        try {
            int listLength = myList.size();
            String input = "";
            FileWriter fw = new FileWriter("src/main/java/Data.txt");
            for (int i = 0; i < listLength; i++) {
                Task t = myList.get(i);
                int index = i + 1;
                input += index + ". " + t.toString() + "\n";
            }
            fw.write(input);
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}