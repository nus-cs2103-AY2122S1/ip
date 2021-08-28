package duke;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Represents a list that keeps track of the <code>Task</code>s that the user has.
 */
public class TaskList {

    private ArrayList<Task> list;
    private Duke duke;

    /**
     * Returns a TaskList object.
     *
     * @param duke the Duke object that is the parent.
     */
    public TaskList(Duke duke) {
        this.list = new ArrayList<>();
        this.duke = duke;
    }

    public ArrayList<Task> getList() {
        return this.list;
    }

    /**
     * Prints the <code>TaskList</code> in the correct format to be displayed in Ui.
     *
     * @return String of the current <code>TaskList</code> that has been formatted properly.
     */
    public String printList() {
        String listString = "";
        for (int i = 0; i < list.size(); i++) {
            int index = i + 1;
            listString = listString + index + "." + list.get(i).toString() + "\n";
        }
        return listString;
    }

    /**
     * Adds a <code>Task</code> to the <code>TaskList</code>.
     *
     * @param description of the Task.
     * @param time        of the Task if it is a Deadline or Event.
     * @param category    in the <code>Task.CATEGORY</code> enumeration. Either ToDo, Deadline or Event.
     * @param isDone      boolean indicating if <code>Task</code> has been completed yet.
     * @param hasNotif    boolean indicating if <code>Task</code> addition should be printed to <code>Ui</code>.
     */
    public void createTask(String description, String time, Task.Category category,
            boolean isDone, boolean hasNotif) {
        switch (category) {
        case TODO:
            list.add(new ToDo(description, isDone, hasNotif));
            break;
        case DEADLINE:
            list.add(new Deadline(description, time, isDone, hasNotif));
            break;
        case EVENT:
            list.add(new Event(description, time, isDone, hasNotif));
            break;
        default:
        }
        if (hasNotif) {
            duke.getUi().showAddTask();
            try {
                duke.getStorage().saveListToFile();
            } catch (IOException e) {
                duke.getUi().showLoadingError();
            }
        }
    }

    /**
     * Adds a <code>Task</code> to the <code>TaskList</code> with a date in <code>LocalDate</code> format.
     *
     * @param description of the Task.
     * @param time        of the Task if it is a Deadline or Event, in LocalDate.
     * @param category    in the <code>Task.CATEGORY</code> enumeration. Either ToDo, Deadline or Event.
     * @param isDone      boolean indicating if <code>Task</code> has been completed yet.
     * @param hasNotif    boolean indicating if <code>Task</code> addition should be printed to <code>Ui</code>.
     */
    public void createTaskDate(String description, LocalDate time, Task.Category category,
            boolean isDone, boolean hasNotif) {
        switch (category) {
        case DEADLINE:
            list.add(new Deadline(description, time, isDone, hasNotif));
            break;
        case EVENT:
            list.add(new Event(description, time, isDone, hasNotif));
            break;
        default:
        }
        if (hasNotif) {
            duke.getUi().showAddTask();
            try {
                duke.getStorage().saveListToFile();
            } catch (IOException e) {
                duke.getUi().showLoadingError();
            }
        }
    }
}
