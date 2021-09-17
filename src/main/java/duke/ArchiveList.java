package duke;

import duke.task.Task;

import java.util.ArrayList;

/**
 * Represents ArchiveList for Duke.
 */
public class ArchiveList extends DukeList {
    /**
     * Constructs a ArchiveList object.
     */
    public ArchiveList() {
        super();
    }

    /**
     * Constructs a ArchiveList object, setting taskArrayList
     * to a given list.
     *
     * @param list List to be assigned to taskArrayList.
     */
    public ArchiveList(ArrayList<Task> list) {
        super(list);
    }

    /**
     * Gets the archived tasks.
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

    /**
     * Unarchives Task at given index of taskArrayList.
     *
     * @param indexToUnarchive Index of Task to be unarchived.
     * @param taskList taskList to add the Task to.
     * @return Task which was unarchived.
     */
    public Task unarchive(int indexToUnarchive, TaskList taskList) {
        Task toUnarchive = this.remove(indexToUnarchive);

        taskList.add(toUnarchive);

        return toUnarchive;
    }

}
