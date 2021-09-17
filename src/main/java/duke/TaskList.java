package duke;

import java.util.ArrayList;

import task.Task;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Adds Task to task list.
     *
     * @param t Task to add to task list.
     */
    public String add(Task t) {
        this.tasks.add(t);
        return "Added task: " + t.toString() + "\nYou have " + tasks.size() + " tasks in the list";
    }

    /**
     * Adds Task to task list.
     *
     * @param s String stored in storage.
     */
    public void add(String s) {
        this.tasks.add(Task.getTask(s));
    }

    /**
     * Prints all the Tasks in task list.
     */
    public String printTasks() {
        return this.toString();
    }

    /**
     * Marks Task as done.
     *
     * @param id Id of tasks done.
     * @throws DukeException if Id is invalid.
     */
    public void doneTask(int id) throws DukeException {
        if (id >= this.tasks.size()) {
            throw new DukeException("no such task");
        }
        Task t = this.tasks.get(id);
        t.markDone();
    }

    /**
     * Deletes Task from task list.
     *
     * @param id Id of task to delete.
     * @throws DukeException if Id is invalid.
     */
    public void deleteTask(int id) throws DukeException {
        int originalSize = tasks.size();
        if (id >= this.tasks.size()) {
            throw new DukeException("no such task");
        }
        this.tasks.remove(id);
        assert tasks.size() < originalSize;
    }

    /**
     * Finds Task that contains keyword.
     *
     * @param keyword Keyword used to find Task.
     */
    public String findTasks(String keyword) {
        StringBuilder response = new StringBuilder("Matching task(s) are:\n");
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            String name = t.getName();
            if (name.contains(keyword)) {
                response.append(i).append(": ").append(t.toString());
            }
        }
        return response.toString();
    }

    /**
     * Adds a tag to the specified task.
     *
     * @param id Id of task to tag.
     * @param tagName Name of tag.
     * @return Task that was tagged.
     */
    public Task tagTask(int id, String tagName) {
        Task t = tasks.get(id);
        t.addTag(tagName);
        return t;
    }

    /**
     * Returns string representing task list to be saved to text file.
     *
     * @return String representing task list
     */
    public String saveTasklist() {
        String txt = "";
        for (int i = 0; i < tasks.size(); i++) {
            txt = txt + tasks.get(i).saveTask() + "\n";
        }
        return txt;
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < this.tasks.size(); i++) {
            Task t = this.tasks.get(i);
            out.append(i).append(": ").append(t.toString()).append(t.getTags()).append("\n");
        }
        return out.toString();
    }
}
