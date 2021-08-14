import java.util.ArrayList;

/**
 * This class encapsulates a List.
 *
 * @author Tan Yi Guan
 * @version CS2103T AY21/22 Semester 1
 */
public class ToDoList {
    private final ArrayList<Task> list;

    /** Public constructor of List. */
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
            for (int i = 1; i <= list.size(); i++) {
                System.out.printf("\t%s. %s%n", i, list.get(i-1));
            }
        }
        System.out.println(Duke.divider);
    }

    /**
     * Adds user input to list and pretty prints a visual feedback.
     *
     * @param input Item to be stored into the list.
     */
    public void addToList(String input) {
        Task task = new Task(input);
        list.add(task);
        Duke.prettyPrint("Task added: " + input);
    }

    /**
     * Marks the task at index specified to be done.
     *
     * @param index Index of task to be marked as done.
     */
    public void markTaskAsDone(int index) {
        if (index > list.size()) {
            Duke.prettyPrint("Hmm.. you don't seem to have that many task on hand.");
        } else if (index < 0) {
            Duke.prettyPrint("Negative task..?! Are you a TA??");
        } else {
            Task task = list.get(index - 1);
            task.markAsDone();
            Duke.prettyPrint("Good job on completing this task!\n\t  " + task);
        }
    }
}
