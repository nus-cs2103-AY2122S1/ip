import java.io.File;
import java.util.ArrayList;

public class TaskList {

    static private ArrayList<Task> storage;

    public TaskList (ArrayList<Task> filledStorage) {
        storage = filledStorage;
    }

    public TaskList () {
        storage = new ArrayList<Task>();
    }

    public static int noOfTasks() {
        return storage.size();
    }

    public static void todo(String todoEntry) throws DukeException {
        if (todoEntry.length() == 5) {
            throw new DukeException("Sorry ☹, please enter a description!");
        }
        String todoTitle = todoEntry.substring(5);
        Todo newToDo = new Todo(todoTitle);
        storage.add(newToDo);
        Ui.print("Alright. I'm adding this task:\n  " + newToDo.toString() + "\nNow there are " + storage.size()
                + " tasks in the list" );
    }

    public static void deadline(String deadlineEntry) throws DukeException {
        if (deadlineEntry.length() == 9) {
            throw new DukeException("Sorry ☹, please enter a description!");
        }
        int indexOfSlash = deadlineEntry.indexOf("/");
        if (indexOfSlash == -1) {
            throw new DukeException("Sorry ☹, please enter a deadline!");
        }
        String deadlineDate = deadlineEntry.substring(indexOfSlash + 4);

        if (Time.validateJavaDate(deadlineDate)) {
            String deadlineTitle = deadlineEntry.substring(9, indexOfSlash);
            System.out.println(deadlineDate);
            deadlineDate = Time.changeDateFormat(deadlineDate);
            Deadline newDeadline = new Deadline(deadlineTitle, deadlineDate);
            storage.add(newDeadline);
            Ui.print("Alright. I'm adding this task:\n  " + newDeadline.toString() + "\nNow there are " + storage.size()
                    + " tasks in the list" );
        } else {
            throw new DukeException("Sorry ☹, please enter the deadline in the correct format! (DD/MM/YYYY)");
        }

    }

    public static void event(String eventEntry) throws DukeException {
        if (eventEntry.length() == 6) {
            throw new DukeException("Sorry ☹, please enter a description!");
        }
        int indexOfSlash = eventEntry.indexOf("/");
        if (indexOfSlash == -1) {
            throw new DukeException("Sorry ☹, please enter an event time!");
        }

        String eventDate = eventEntry.substring(indexOfSlash + 4);

        if (Time.validateJavaDate(eventDate)) {
            String eventTitle = eventEntry.substring(6, indexOfSlash);
            Event newEvent = new Event(eventTitle, eventDate);
            storage.add(newEvent);
            Ui.print("Alright. I'm adding this task:\n  " + newEvent.toString() + "\nNow there are " + storage.size()
                    + " tasks in the list" );
        } else {
            throw new DukeException("Sorry ☹, please enter the event in the correct format! (DD/MM/YYYY)");
        }
    }

    public static void delete(String deleteInput) throws DukeException {
        int taskNumber = Integer.parseInt(deleteInput.substring(7,8));
        if (taskNumber > storage.size()) {
            throw new DukeException("Sorry ☹, please enter a valid task to delete!");
        }
        Task deletedTask = storage.remove(taskNumber - 1);
        Ui.print("Okay! I have deleted the task for you.\n  " + deletedTask.toString()
                + "\nNow there are " + storage.size() + " tasks in the list");
    }

    public static Task getCurrentTask(int taskNumber) {
        return storage.get(taskNumber);
    }



}
