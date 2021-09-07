package duke;

import duke.task.Task;

import java.util.ArrayList;

public class ArchiveList extends DukeList {
    /**
     * Constructor for TaskList.
     */
    public ArchiveList() {
        super();
    }

    /**
     * Constructor for TaskList, setting tasks to a given list.
     *
     * @param list List to be assigned to tasks.
     */
    public ArchiveList(ArrayList<Task> list) {
        super(list);
    }

    /**
     * Getter for archived tasks.
     *
     * @return tasks.
     */
    public ArrayList<Task> getArchivedTasks() {
        return super.getTaskArrayList();
    }

    @Override
    public String type() {
        return "archived list";
    }

}
