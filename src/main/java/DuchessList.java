import java.lang.reflect.Array;
import java.util.ArrayList;
/**
 * This class implements a DukeList to be used in storing string from Duke
 *
 * @author Amos Tan
 * @version CS2103T AY21/22 Semester 1
 */

public class DuchessList {
    /** The ArrayList which stores the messages by the user.*/
    private ArrayList<Task> itemList;

    /**
     * Constructor for DukeList
     */
    public DuchessList()
    {
        itemList = new ArrayList<>();
    }

    /**
     * Adds a string to the back of the DukeList array
     * @param input the string to be added
     */
    public void add(Task input)
    {
        itemList.add(input);
    }

    /**
     * Prints the items in the DukeList in order
     * @return string of printed items in a list
     */
    public String printList()
    {
        String printed = "";
        int size = itemList.size();
        for (int i = 0; i < size; i++)
        {
            printed += String.format("%d. " + itemList.get(i) + (i == size - 1 ? "" : "\n"), i + 1);
        }
        return printed;
    }

    /**
     * Checks if the item number is within range of the list's items
     * @param listNumber the item number (not ArrayList index) to be checked
     * @return boolean of whether the number is within the list's range
     */
    public boolean checkWithinRange(int listNumber)
    {
        return 0 < listNumber && listNumber <= itemList.size();
    }

    /**
     * Returns the item at the list number
     * @param listNumber the item number whose item is to be returned
     * @return the item at the list number
     * @throws IllegalArgumentException only accepts integers whose values are within range of the ArrayList
     */
    public Task getTask(int listNumber) throws IllegalArgumentException
    {
        if (checkWithinRange(listNumber))
            return itemList.get(listNumber - 1); // -1 due to difference between item list number and array indexes
        else
            throw new IllegalArgumentException("Task index is not within range."); // Not within range
    }

    /**
     * Getter method to obtain the size of the DuchessList
     * @return the number of elements in the DuchessList
     */
    public int getSize() {
        return itemList.size();
    }

    /**
     * Deletes the task with the given number in the list
     * @param listNumber the number in the list of the task to be deleted
     * @return the deleted task
     */
    public Task delete(int listNumber)
    {
        return itemList.remove(listNumber - 1); // -1 due to difference between item list number and indexes
    }
}
