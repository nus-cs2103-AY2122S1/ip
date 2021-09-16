package duke.task;

import java.util.ArrayList;

import duke.exception.DukeException;

/**
 * Creates an ArrayList to store tasks and provides functionality to manipulate the tasks inside the ArrayList.
 */
public class TaskList {
    private static ArrayList<Task> store;
    private String[] input;

    /**
     * Creates new TaskList object.
     *
     * @param input Input array of tasks in String representation.
     */
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
        assert !(store == null);
        return store.toString();
    }

    /**
     * Generates the TaskList upon start-up of the Duke using information stored in the text file.
     */
    private void generateStore() {
        for (int i = 0; i < input.length; i++) {
            String command = input[i];
            if (command == null) {
                return;
            }
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
            default:
                break;
            }
        }
    }

    /**
     * Adds a task to TaskList.
     *
     * @param task Task that is to be added.
     */
    public static void add(Task task) {
        assert !(store == null);
        store.add(task);
    }

    /**
     * Gets size of TaskList currently.
     *
     * @return Number of Tasks inside the TaskList.
     */
    public int size() {
        assert !(store == null);
        return store.size();
    }

    /**
     * Gets a specific Task inside TaskList.
     *
     * @param taskNumber Task Number to be taken from the TaskList.
     * @return Task at specified position in the TaskList.
     */
    public Task get(int taskNumber) {
        assert !(store == null);
        assert (taskNumber < store.size());
        return store.get(taskNumber);
    }

    /**
     * Removes Task from TaskList.
     *
     * @param taskNumber Task Number to remove from the TaskList.
     */
    public void remove(int taskNumber) {
        assert !(store == null);
        assert (taskNumber < store.size());
        store.remove(taskNumber);
    }

    /**
     * Creates String array containing contents of tasks in list.
     *
     * @param command Command given by user.
     * @return String Array of output.
     */
    public static String[] printList(String command) {
        String[] words = command.split(" ");
        assert words[0].equals("list");
        String[] output = new String[store.size() + 1];
        if (words.length > 1) {
            throw new DukeException("invalidCommand");
        } else if (store.size() == 0) {
            output[0] = "You have no tasks in your list yay!";
            return output;
        }

        output[0] = "Here are the tasks in your list:";
        for (int i = 0; i < store.size(); i++) {
            output[i + 1] = (i + 1) + ". " + store.get(i).toString();
        }
        return output;
    }

}
