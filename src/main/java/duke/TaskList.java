package duke;

import duke.task.Task;

import java.util.ArrayList;

/**
 * Represents the list of Tasks for Duke.
 */
public class TaskList extends DukeList {
    /**
     * Constructs a TaskList object.
     */
    public TaskList() {
        super();
    }

    /**
     * Constructs a TaskList object.
     *
     * @param list List to be assigned to taskArrayList in DukeList.
     */
    public TaskList(ArrayList<Task> list) {
        super(list);
    }

    /**
     * Gets the taskArrayList.
     *
     * @return tasks.
     */
    public ArrayList<Task> getTasks() {
        return super.getTaskArrayList();
    }

    /**
     * Marks Task at given index in taskArrayList as done.
     *
     * @param index Index of Task to mark as done.
     */
    public void markAsDone(int index) {
        super.get(index).markAsDone();
    }

    /**
     * Marks all the Tasks in taskArrayList as done.
     */
    public void markAllAsDone() {
        for (int i = 0; i < this.getSize(); i++) {
            markAsDone(i);
        }
    }

    /**
     * Finds tasks with given keyword in taskArrayList.
     *
     * @param keyword Keyword of tasks we want to find.
     * @return TaskList of Tasks with given keyword.
     */
    public TaskList findTasksWithKeyword(String keyword) {
        ArrayList<Task> tasksWithKeyword = new ArrayList<>();

        for (int i = 0; i < super.getSize(); i++) {
            if (super.get(i).hasKeyword(keyword)) {
                tasksWithKeyword.add(super.get(i));
            }
        }

        return new TaskList(tasksWithKeyword);
    }

    /**
     * Archives Task at given index of taskArrayList.
     *
     * @param index Index of Task to be archived.
     * @param archiveList ArchiveList to add the Task to.
     * @return Task which was archived.
     */
    public Task archive(int index, ArchiveList archiveList) {
        Task toArchive = this.remove(index);

        archiveList.add(toArchive);

        return toArchive;
    }

    /**
     * Gets the type of list.
     *
     * @return "list".
     */
    @Override
    public String type() {
        return "list";
    }
}
