import java.util.ArrayList;

/**
 * This class encapsulates a List.
 *
 * @author Tan Yi Guan
 * @version CS2103T AY21/22 Semester 1
 */
public class ToDoList {
    private final ArrayList<Task> list;

    /** Instantiates a new To do list. */
    public ToDoList() {
        list = new ArrayList<>();
    }

    /** Prints the list of items that the user entered. */
    public void printList() {
        System.out.println(Duke.divider);
        // Custom message for when user types 'list' when nothing is added.
        if (list.size() == 0) {
            System.out.println("\tYou are all done for the day :-)");
        } else {
            System.out.println("\tHere are the tasks in your list:");
            for (int i = 1; i <= list.size(); i++) {
                System.out.printf("\t%s. %s\r\n", i, list.get(i - 1));
            }
        }
        System.out.println(Duke.divider);
    }

    /**
     * Adds user input to list and pretty prints a visual feedback.
     *
     * @param task Task to be stored into the list.
     */
    public void addToList(Task task) {
        list.add(task);
        Duke.prettyPrint(
                String.format(
                        "Got it. I've added this task:\r\n\t  %s\r\n\tNow you have %s tasks in the list.",
                        task, list.size()));
    }

    /**
     * Marks the task at index specified to be done.
     *
     * @param index Index of task to be marked as done.
     */
    public void markTaskAsDone(int index) throws DukeException {
        if (index > list.size()) {
            throw new DukeException(
                    "IndexOutOfBoundsError: Please enter an index that is shown in 'list'.");
        } else if (index < 0) {
            throw new DukeException("NegativeIndexError: I can't handle negative indexing.");
        } else {
            Task task = list.get(index - 1);
            task.markAsDone();
            Duke.prettyPrint(String.format("Good job on completing this task!\r\n\t  %s", task));
        }
    }

    public void removeFromList(int index) throws DukeException {
        if (index > list.size()) {
            throw new DukeException(
                    "IndexOutOfBoundsError: Please enter an index that is shown in 'list'.");
        } else if (index < 0) {
            throw new DukeException("NegativeIndexError: I can't handle negative indexing.");
        } else {
            Task task = list.get(index - 1);
            Duke.prettyPrint(
                    String.format(
                            "Noted. I've removed this task:\r\n\t  %s\r\n\tNow you have %s tasks in the list.",
                            task, list.size() - 1));
            list.remove(index - 1);
        }
    }
}
