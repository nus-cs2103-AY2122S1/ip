package duke;
import java.util.ArrayList;

/**
 * Class to represent the list of tasks that the User has.
 */
public class TaskList {

    private static ArrayList<Task> storage;

    /**
     * Constructor for the TaskList if there already is an existing Storage.
     * @param filledStorage existing ArrayList.
     */
    public TaskList (ArrayList<Task> filledStorage) {
        storage = filledStorage;
    }

    /**
     * Constructor for the TaskList if there is no storage yet.
     */
    public TaskList () {
        storage = new ArrayList<Task>();
    }

    /**
     * Calculates the number of tasks present in the task list.
     * @return the number of tasks in the task list.
     */
    public static int noOfTasks() {
        return storage.size();
    }

    /**
     * Creates a new Todo task which is added to the task list.
     * @param todoEntry description for the Todo task.
     * @throws DukeException if the User leaves the description blank.
     */
    public static String todo(String todoEntry) throws DukeException {
        if (todoEntry.length() == 5) {
            throw new DukeException("Sorry, please enter a description!");
        }
        String todoTitle = todoEntry.substring(5);
        Todo newToDo = new Todo(todoTitle);
        storage.add(newToDo);
        return "Alright. I'm adding this task:\n  " + newToDo.toString() + "\nNow there are " + storage.size()
                + " tasks in the list";
    }

    /**
     * Creates a new Deadline task which is added to the task list.
     * @param deadlineEntry description for the Deadline task.
     * @throws DukeException if the user does not enter a description or does not enter a deadline or provided the
     *      deadline in the wrong format.
     */
    public static String deadline(String deadlineEntry) throws DukeException {
        if (deadlineEntry.length() == 9) {
            throw new DukeException("Sorry, please enter a description!");
        }
        int indexOfSlash = deadlineEntry.indexOf("/");
        if (indexOfSlash == -1) {
            throw new DukeException("Sorry, please enter a deadline!");
        }
        String deadlineDate = deadlineEntry.substring(indexOfSlash + 4);

        if (Time.validateJavaDate(deadlineDate)) {
            String deadlineTitle = deadlineEntry.substring(9, indexOfSlash);
            deadlineDate = Time.changeDateFormat(deadlineDate);
            Deadline newDeadline = new Deadline(deadlineTitle, deadlineDate);
            storage.add(newDeadline);
            return "Alright. I'm adding this task:\n  " + newDeadline.toString() + "\nNow there are "
                    + storage.size() + " tasks in the list";
        } else {
            throw new DukeException("Sorry, please enter the deadline in the correct format! (DD/MM/YYYY)");
        }

    }

    /**
     * Creates a new Event task which is added to the task list.
     * @param eventEntry description for the Event task.
     * @throws DukeException if the user does not enter a description or does not enter an event date or provided the
     *      event date in the wrong format.
     */
    public static String event(String eventEntry) throws DukeException {
        if (eventEntry.length() == 6) {
            throw new DukeException("Sorry, please enter a description!");
        }
        int indexOfSlash = eventEntry.indexOf("/");
        if (indexOfSlash == -1) {
            throw new DukeException("Sorry, please enter an event time!");
        }

        String eventDate = eventEntry.substring(indexOfSlash + 4);

        if (Time.validateJavaDate(eventDate)) {
            String eventTitle = eventEntry.substring(6, indexOfSlash);
            Event newEvent = new Event(eventTitle, eventDate);
            storage.add(newEvent);
            return "Alright. I'm adding this task:\n  " + newEvent.toString() + "\nNow there are "
                    + storage.size() + " tasks in the list";
        } else {
            throw new DukeException("Sorry, please enter the event in the correct format! (DD/MM/YYYY)");
        }
    }

    /**
     * Deletes the task according to the User's input.
     * @param deleteInput contains the task number that the User wants to delete.
     * @throws DukeException if the task number is invalid.
     */
    public static String delete(String deleteInput) throws DukeException {
        int taskNumber = Integer.parseInt(deleteInput.substring(7, 8));
        if (taskNumber > storage.size()) {
            throw new DukeException("Sorry, please enter a valid task to delete!");
        }
        duke.Task deletedTask = storage.remove(taskNumber - 1);
        return "Okay! I have deleted the task for you.\n  " + deletedTask.toString()
                + "\nNow there are " + storage.size() + " tasks in the list";
    }


    /**
     * Returns the task that has the task number inputted.
     * @param taskNumber task number that the User wants to get.
     * @return the Task that has the Task number that the User inputted.
     */

    public static Task getCurrentTask(int taskNumber) {
        return storage.get(taskNumber);
    }



}
