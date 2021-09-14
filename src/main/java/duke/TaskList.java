package duke;

import java.util.ArrayList;
import java.util.List;

import duke.task.Task;

/**
 * Encapsulates the task list object.
 */
public class TaskList {
    private List<Task> tasks;
    private List<Task> archives;
    //For the secret hp command
    private int hpCount = 0;

    /**
     * Creates new TaskList instance.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
        this.archives = new ArrayList<>();
    }

    /**
     * Creates new TaskList instance when tasks are loaded from storage.
     *
     * @param existingTasks Previously saved tasks.
     */
    public TaskList(List<Task> existingTasks, List<Task> existingArchives) {
        this.tasks = (existingTasks == null ? new ArrayList<>() : existingTasks);
        this.archives = existingArchives;
    }

    public void addHp() {
        hpCount++;
    }

    public int getHp() {
        return hpCount;
    }

    public int getSize() {
        return tasks.size();
    }

    public Task get(int i) {
        return tasks.get(i);
    }

    public int getArchiveSize() {
        return archives.size();
    }

    public Task getArchivedTask(int i) {
        return archives.get(i);
    }

    /**
     * Archives the task at index i in the task list.
     *
     * @param i Index of the task in the main task list.
     */
    public void archive(int i) {
        Task t = tasks.get(i);
        tasks.remove(i);
        archives.add(t);
    }

    /**
     * Unarchives the task at index i in the archives list.
     *
     * @param i Index of the task in the archives list.
     */
    public void unarchive(int i) {
        Task t = archives.get(i);
        archives.remove(i);
        tasks.add(t);
    }

    public void add(Task t) {
        tasks.add(t);
    }

    public void remove(int i) {
        this.tasks.remove(i);
    }

    public List<Task> getTasks() {
        return this.tasks;
    }

    public List<Task> getArchives() {
        return this.archives;
    }
}
