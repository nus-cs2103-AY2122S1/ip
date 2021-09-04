package virushade.tasks;

import virushade.Storage;
import virushade.StringManipulator;
import virushade.VirushadeException;

import java.util.ArrayList;

public class TaskList {
    /**
     * This is the array that Virushade keeps the tasks in.
     */
    private static final ArrayList<Task> TASKS = new ArrayList<>();

    /**
     * This is the file name of our file that stores data on TaskList.
     */
    private static Storage TASK_LIST_STORAGE;

    /**
     * This variable keeps track of the size of the TaskList.
     */
    private static int listCount = 0;

    /**
     * Constructor for the TaskList class.
     * @param storage The storage for classList to load data from.
     * @throws VirushadeException Thrown when there is trouble parsing the data file in storage.
     */
    public TaskList(Storage storage) throws VirushadeException {
        TASK_LIST_STORAGE = storage;
        storage.load(TASKS);
        listCount = TASKS.size();
    }

    /**
     * A function that writes an input string to TASK_LIST_STORAGE.
     *
     * @param text The input string to write into the file.
     */
    private static void updateFile(String text) throws VirushadeException {
        TASK_LIST_STORAGE.update(text);
    }

    /**
     * Adds a Task to taskList.
     * @param addedTaskDescription Name of the added Task.
     * @param taskType The type of task to be added.
     * @return A string to represent the addition.
     * @throws VirushadeException Thrown when there is trouble adding the task.
     */
    public static String add(String addedTaskDescription, String taskType) throws VirushadeException {
        if (listCount < 100) {
            Task addedTask;
            String[] strings = StringManipulator.slashPartition(addedTaskDescription);

            // Checking if the Task Name is empty (Or filled with Spacebars):
            if (strings[0].replace(" ", "").equals("")) {
                throw new VirushadeException("OOPS!!! Please enter the task description!!!");
            }

            switch (taskType) {
            case "TODO":
                addedTask = new ToDo(addedTaskDescription, false);
                break;

            case "DEADLINE":
                if (strings[1].startsWith("by ")) {
                    addedTask = new Deadline(strings[0], strings[1].substring(3), false);
                } else {
                    throw new VirushadeException("Please include a deadline after your task name. " +
                            "(e.g. /by Sunday)");
                }
                break;

            case "EVENT":
                if (strings[1].startsWith("at ")) {
                    addedTask = new Event(strings[0], strings[1].substring(3), false);
                } else {
                    throw new VirushadeException("Please include a time after your task name. " +
                            "(e.g. /at 12 noon)");
                }
                break;

            // The add function would not reach this line at all.
            default:
                addedTask = new Task(addedTaskDescription, false);
            }

            TASKS.add(addedTask);
            listCount++;
            updateFile(generateList());
            return ("Added: " + addedTask.getTaskDescription() +
                    "\nNow you have " + listCount + " tasks in the list.\n");
        } else {
            throw new VirushadeException("Sorry, Virushade cannot keep track of more than 100 tasks!!!");
        }
    }

    /**
     * Deletes the specified task.
     * @param str Input string, determines which task to delete.
     * @return The string message of deletion.
     * @throws VirushadeException Thrown when there is trouble deleting the task.
     */
    public static String delete(String str) throws VirushadeException {
        try {
            // If what comes after "delete " is not an integer, this will throw a NumberFormatException.
            int index = Integer.parseInt(str);

            if (index <= 0) {
                throw new VirushadeException("Please enter an integer greater than 0.");
            } else if (index <= listCount) {
                Task deletedTask = TASKS.get(index - 1);
                TASKS.remove(index - 1);
                deletedTask.deleteMessage();
                listCount--;
                updateFile(generateList());

                return "You have " + listCount + " tasks in the list.\n";
            } else {
                throw new VirushadeException("Please check that you have entered the correct number!");
            }
        } catch (NumberFormatException e) {

            // Tells the user that he did not enter a number.
            throw new VirushadeException("Please enter an integer after 'done ' instead.\n" + e);
        }
    }

    /**
     * Marks a task as complete.
     * @param str Input string, determines which task to mark as complete.
     * @return The string to notify of successful completion.
     * @throws VirushadeException Thrown when there is something wrong with the user input.
     */
    public static String completeTask(String str) throws VirushadeException {

        try {
            // If what comes after "done " is not an integer, this will throw a NumberFormatException.
            int index = Integer.parseInt(str);

            if (index <= 0) {
                throw new VirushadeException("Please enter an integer greater than 0.");
            } else if (index <= listCount) {
                Task doneTask = TASKS.get(index - 1);
                String returnString = doneTask.completeTask();
                updateFile(generateList());

                return returnString;
            } else {
                throw new VirushadeException("Please check that you have entered the correct number!");
            }
        } catch (NumberFormatException e) {
            // Tells the user that he did not enter a number.
            throw new VirushadeException("Please enter an integer after 'done ' instead.");
        }
    }

    /**
     * @return String representation of the tasks within TASK_ARRAY_LIST.
     */
    public static String generateList() {
        if (listCount == 0) {
            return "Nothing in the list as of now.";
        }

        int index = 0;
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:");

        while (index < listCount) {
            String taskName = (index + 1) + "." + TASKS.get(index).toString();
            sb.append(System.lineSeparator()).append(taskName);
            index++;
        }

        return sb.toString();
    }

    /**
     * Returns a string representation of all the tasks that have input text as a substring.
     *
     * @param text The input search text
     * @return All results matching the input.
     */
    public static String findFromList(String text) {
        StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:");
        int counter = 1;
        for(int i = 0; i < listCount; i++) {
            String taskName = TASKS.get(i).toString();
            if (taskName.contains(text)) {
                String foundTaskName = counter + "." + taskName;
                counter++;
                sb.append(System.lineSeparator()).append(foundTaskName);
            }
        }

        if (counter == 1) {
            // Found nothing.
            return "There are no matching results";
        } else {
            return sb.toString();
        }
    }
}
