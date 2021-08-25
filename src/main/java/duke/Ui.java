package duke;

import java.util.ListIterator;

public class Ui {
    private static final String SEPARATOR = "########################";

    public Ui() {

    }

    /**
     * Beautifies text output by wrapping it around a border.
     *
     * @param input Processed input from the user.
     */
    public void outputWrapper(String input) {
        System.out.println(SEPARATOR);
        System.out.println(input);
        System.out.println(SEPARATOR);
    }

    /**
     * Beautifies list of items by wrapping it around a border.
     *
     * @param elements Tasklist of all the items in the current session's tasklist.
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
     * Displays a confirmation message on the command line on addition of a Task to the task list.
     *
     * @param task Task object to be added.
     * @param listOfItems Current session's Tasklist.
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
     * Displays a confirmation message on the command line on deletion of a Task from the task list.
     *
     * @param task Task object to be deleted.
     * @param listOfItems Current session's Tasklist.
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
