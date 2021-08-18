/**
 * This class implements a DukeList to be used in storing string from Duke
 *
 * @author Amos Tan
 * @version CS2103T AY21/22 Semester 1
 */

public class DukeList {
    /** The array which stores the messages by the user.*/
    private Task[] itemList;
    /** The number of elements in the itemList.*/
    private int size;

    /**
     * Constructor for DukeList
     */
    public DukeList()
    {
        itemList = new Task[100];
        size = 0;
    }

    /**
     * Adds a string to the back of the DukeList array
     * @param input the string to be added
     */
    public void add(Task input)
    {
        itemList[size++] = input;
    }

    /**
     * Prints the items in the DukeList in order
     * @return string of printed items in a list
     */
    public String printList()
    {
        String printed = "";
        for (int i = 0; i < size; i++)
        {
            printed += String.format("%d. " + itemList[i] + (i == size - 1 ? "" : "\n"), i + 1);
        }
        return printed;
    }

    /**
     * Checks if the item number is within range of the list's items
     * @param listNumber the item number (not array index) to be checked
     * @return boolean of whether the number is within the list's range
     */
    public boolean checkWithinRange(int listNumber)
    {
        return 0 < listNumber && listNumber <= size;
    }

    /**
     * Returns the item at the list number
     * @param listNumber the item number whose item is to be returned
     * @return the item at the list number
     * @throws IllegalArgumentException only accepts integers whose values are within range of the array
     */
    public Task getTask(int listNumber) throws IllegalArgumentException
    {
        if (checkWithinRange(listNumber))
            return itemList[listNumber - 1]; // -1 due to difference between item list number and array indexes
        else
            throw new IllegalArgumentException("Task index is not within range."); // Not within range
    }

    public int getSize() {
        return this.size;
    }
}
