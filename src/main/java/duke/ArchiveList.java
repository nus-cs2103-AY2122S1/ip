package duke;

import duke.task.Task;

import java.util.ArrayList;

/**
 * Represents ArchiveList for Duke.
 */
public class ArchiveList extends DukeList {
    /**
     * Constructor for ArchiveList.
     */
    public ArchiveList() {
        super();
    }

    /**
     * Constructor for ArchiveList, setting taskArrayList
     * to a given list.
     *
     * @param list List to be assigned to taskArrayList.
     */
    public ArchiveList(ArrayList<Task> list) {
        super(list);
    }

    /**
     * Getter for archived tasks.
     *
     * @return Archived tasks.
     */
    public ArrayList<Task> getArchivedTasks() {
        return super.getTaskArrayList();
    }

    /**
     * Gets the type of list.
     *
     * @return "archived list"
     */
    @Override
    public String type() {
        return "archived list";
    }

}
