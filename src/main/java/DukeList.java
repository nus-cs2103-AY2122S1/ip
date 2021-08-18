/**
 * This class implements a DukeList to be used in storing string from Duke
 *
 * @author Amos Tan
 * @version CS2103T AY21/22 Semester 1
 */

public class DukeList {
    /** The array which stores the messages by the user.*/
    private String[] itemList;
    private int size;

    /**
     * Constructor for DukeList
     */
    public DukeList()
    {
        itemList = new String[100];
        size = 0;
    }

    /**
     * Adds a string to the back of the DukeList array
     * @param input the string to be added
     */
    public void Add(String input)
    {
        itemList[size++] = input;
    }

    /**
     * Prints the items in the DukeList in order
     * @return string of printed items in a list
     */
    public String PrintList()
    {
        String printed = "";
        for (int i = 0; i < size; i++)
        {
            printed += String.format("%d. " + itemList[i] + (i == size - 1 ? "" : "\n"), i + 1);
        }
        return printed;
    }
}
