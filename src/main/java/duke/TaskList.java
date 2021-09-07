package duke;

import duke.task.Task;

import java.util.ArrayList;

/**
 * Represents the list of Tasks for Duke.
 */
public class TaskList extends DukeList {
    /**
     * Constructor for TaskList.
     */
    public TaskList() {
        super();
    }

    /**
     * Constructor for TaskList, setting tasks to a given list.
     *
     * @param list List to be assigned to tasks.
     */
    public TaskList(ArrayList<Task> list) {
        super(list);
    }

    /**
     * Getter for tasks.
     *
     * @return tasks.
     */
    public ArrayList<Task> getTasks() {
        return super.getTaskArrayList();
    }

    /**
     * Marks Task at given index in tasks as done.
     *
     * @param index Index of Task to mark as done.
     */
    public void markAsDone(int index) {
        super.get(index).markAsDone();
    }

    public void markAllAsDone() {
        for (int i = 0; i < this.getSize(); i++) {
            markAsDone(i);
        }
    }

    /**
     * Finds tasks with given keyword in tasks.
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

    public Task archive(int index, ArchiveList archiveList) {
        Task toArchive = this.remove(index);

        archiveList.add(toArchive);

        return toArchive;
    }

    @Override
    public String type() {
        return "list";
    }
}
