import java.util.ArrayList;

/**
 * This class encapsulates a List.
 *
 * @author Tan Yi Guan
 * @version CS2103T AY21/22 Semester 1
 */
public class ToDoList {
    private final ArrayList<Task> list;
    private final DataManager dataManager;

    /** Instantiates a new To do list. */
    public ToDoList(ArrayList<Task> list, DataManager dataManager) {
        this.list = list;
        this.dataManager = dataManager;
    }

    /** Prints the list of items that the user entered. */
    public void printList() {
        Ui.printList(list);
    }

    /**
     * Adds user input to list and pretty prints a visual feedback.
     *
     * @param task Task to be stored into the list.
     */
    public void addToList(Task task) {
        list.add(task);
        Ui.prettyPrint(
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
            throw new InvalidIndexException(list.size());
        } else {
            Task task = list.get(index - 1);
            task.markAsDone();
            Ui.prettyPrint(String.format("Good job on completing this task!\r\n\t  %s", task));
        }
    }

    public void removeFromList(int index) throws DukeException {
        if (index > list.size()) {
            throw new InvalidIndexException(list.size());
        } else if (index < 0) {
            throw new DukeNegativeIndexException();
        } else {
            Task task = list.get(index - 1);
            Ui.prettyPrint(
                    String.format(
                            "Noted. I've removed this task:\r\n\t  %s\r\n\tNow you have %s tasks in the list.",
                            task, list.size() - 1));
            list.remove(index - 1);
        }
    }

    public void updateData() throws DukeException {
        dataManager.updateData(list);
    }
}
