package duke;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import duke.Task.Category;

/**
 * Represents a list that keeps track of the <code>Task</code>s that the user has.
 */
public class TaskList {

    private final ArrayList<Task> list;
    private final Duke duke;

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

        String listToString = IntStream
            .range(0, list.size())
            .mapToObj(index -> {
                Task currTask = list.get(index);
                assert currTask != null : "listItem should be present";
                return index + "." + currTask;
            })
            .collect(Collectors.joining("\n"));

        return listToString;
    }

    /**
     * Adds a <code>Task</code> to the <code>TaskList</code>.
     *
     * @param description of the Task.
     * @param time        of the Task if it is a Deadline or Event, in String or LocalDate.
     * @param category    in the <code>Task.CATEGORY</code> enumeration. Either ToDo, Deadline or Event.
     * @param isDone      boolean indicating if <code>Task</code> has been completed yet.
     * @param hasNotif    boolean indicating if <code>Task</code> addition should be printed to <code>Ui</code>.
     * @return String of the UI response.
     */
    public String createTask(String description, Object time, Category category, boolean isDone, boolean hasNotif) {
        String response = "";
        switch (category) {
        case TODO:
            list.add(new ToDo(description, isDone));
            break;
        case DEADLINE:
            list.add(new Deadline(description, time, isDone));
            break;
        case EVENT:
            list.add(new Event(description, time, isDone));
            break;
        default:
        }
        if (hasNotif) {
            response = response + "\n" + duke.getUi().showAddTask();
            try {
                response = response + "\n" + duke.getStorage().saveListToFile();
            } catch (IOException e) {
                response = response + "\n" + duke.getUi().showLoadingError();
            }
        }
        return response;
    }
}
