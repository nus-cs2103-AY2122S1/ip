import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

public class Ui {
    private static final String SEPARATOR = "########################";

    public Ui() {

    }

    /**
     * Beautifies text output by wrapping it around a border.
     *
     * @param input
     */
    public void outputWrapper(String input) {
        System.out.println(SEPARATOR);
        System.out.println(input);
        System.out.println(SEPARATOR);
    }

    /**
     * Beautifies list of items by wrapping it around a border.
     *
     * @param elements
     */
    public void outputWrapper(Tasklist elements) {
        ListIterator<Task> it = elements.toIterable();
        System.out.println(SEPARATOR);
        if (elements.size() == 0) {
            System.out.println("You have no items in your list. Add some with [todo], [deadline] or [event]!");
        } else {
            while (it.hasNext()) {
                Integer number = it.nextIndex() + 1;
                System.out.println(number + ". " + it.next());
            }
        }

        System.out.println(SEPARATOR);
    }

    /**
     * Prints the confirmation message for any item added to list.
     *
     * @param task
     */
    public void printAdditionConfirmation(Task task, Tasklist listOfItems) {
        String confirmationMessage = "You have successfully added an item:\n" + task + "\nto the list.\n";

        String numberOfItems = String.format("There %s %s %s in the list right now",
                listOfItems.size() != 1 ? "are" : "is",
                listOfItems.size(),
                listOfItems.size() != 1 ? "items" : "item");
        outputWrapper(confirmationMessage + numberOfItems);
    }

    /**
     * Prints the confirmations message for any item deleted from the list.
     *
     * @param task
     */
    public void printDeletionConfirmation(Task task, Tasklist listOfItems) {
        String confirmationMessage = "You have successfully deleted an item:\n" + task + "\nfrom the list.\n";

        String numberOfItems = String.format("There %s %s %s in the list right now",
                listOfItems.size() != 1 ? "are" : "is",
                listOfItems.size(),
                listOfItems.size() != 1 ? "items" : "item");
        outputWrapper(confirmationMessage + numberOfItems);
    }
}
