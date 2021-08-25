package duke.task;

import duke.exception.DukeException;
import java.util.ArrayList;

/**
 * Creates an ArrayList to store tasks and provides functionality to manipulate the tasks inside the ArrayList.
 */
public class TaskList {
    private ArrayList<Task> store;
    private String[] input;

    public TaskList(String[] input) {
        this.input = input;
        store = new ArrayList<>();
        generateStore();
    }

    public TaskList() {
        store = new ArrayList<>();
    }

    /**
     * Represents TaskList in a String format.
     *
     * @return String representation of TaskList.
     */
    @Override
    public String toString() {
        return store.toString();
    }

    /**
     * Generates the TaskList upon start-up of the Duke using information stored in the text file.
     */
    private void generateStore() {
        for (int i = 0; i < input.length; i++) {
            String command = input[i];
            if (command != null) {
                String[] words = command.split(" - ");
                boolean isCompleted = false;
                if (words[1].equals("1")) {
                    isCompleted = true;
                }
                String task = words[0];
                switch (task) {
                    case ("T"):
                        Task toAdd = new ToDo(words[2]);
                        if (isCompleted) {
                            toAdd.setCompleted();
                        }
                        store.add(toAdd);
                        break;
                    case ("E"):
                        Task temp = new Event(words[2], words[3]);
                        if (isCompleted) {
                            temp.setCompleted();
                        }
                        store.add(temp);
                        break;
                    case ("D"):
                        Task temp1 = new Deadline(words[2], words[3]);
                        if (isCompleted) {
                            temp1.setCompleted();
                        }
                        store.add(temp1);
                        break;
                }
            }
        }
    }

    /**
     * Adds a task to TaskList.
     *
     * @param task Task that is to be added.
     */
    public void add(Task task) {
        store.add(task);
    }

    /**
     * Gets size of TaskList currently.
     *
     * @return Number of Tasks inside the TaskList.
     */
    public int size() {
        return store.size();
    }

    /**
     * Gets a specific Task inside TaskList.
     *
     * @param taskNumber Task Number to be taken from the TaskList.
     * @return Task at specified position in the TaskList.
     */
    public Task get(int taskNumber) {
        return store.get(taskNumber);
    }

    /**
     * Removes Task from TaskList.
     *
     * @param taskNumber Task Number to remove from the TaskList.
     */
    public void remove(int taskNumber) {
        store.remove(taskNumber);
    }

    /**
     * Prints out the current list of tasks the user has.
     *
     * @param command Command entered by the user.
     * @throws DukeException Upon invalid commands or empty tasks list.
     */
    public void printList(String command) throws DukeException {
        String[] words = command.split(" ");
        if (words.length > 1) {
            throw new DukeException("invalidCommand");
        } else if (store.size() == 0) {
            throw new DukeException("noTasksException");
        } else {
            System.out.println("    ______________________________________");
            System.out.println("     Here are the tasks in your list:");
            for (int i = 0; i < store.size(); i++) {
                System.out.printf("     %d.%s\n", i + 1, store.get(i).toString());
            }
            System.out.println("    ______________________________________");
        }
    }

}
