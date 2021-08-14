import java.util.ArrayList;

/**
 * This class encapsulates a List.
 *
 * @author Tan Yi Guan
 * @version CS2103T AY21/22 Semester 1
 */
public class List {
    private ArrayList<String> list;

  /**
   * Public constructor of List.
   */
  public List() {
        list = new ArrayList<>();
    }

    /**
     * Prints the list of items that the user entered.
     */
    public void printList() {
        System.out.println(Duke.divider);
        // Custom message for when user types 'list' when nothing is added.
        if (list.size() == 0) {
            System.out.println("\tYou have not added anything to your list yet :(");
        } else {
            for (int i = 1; i <= list.size(); i++) {
                System.out.printf("\t%s. %s%n", i, list.get(i - 1));
            }
        }
        System.out.println(Duke.divider);
    }

    /**
     * Adds user input to list and pretty prints a visual feedback.
     *
     * @param input Item to be stored into the list. */
    public void addToList(String input) {
        list.add(input);
        Duke.prettyPrint("added: " + input);
    }
}
