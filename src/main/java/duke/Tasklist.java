package duke;

import java.util.ArrayList;

import duke.task.Task;



/**
 * Class to store the current tasks.
 * Supports addition, deletion, mark done of tasks.
 */
public class Tasklist {
    private ArrayList<Task> tasklist;
    private int count;

    /**
     * Constructor to make a tasklist.
     *
     * @param tasklist current list of tasks.
     */
    public Tasklist(ArrayList<Task> tasklist) {
        this.tasklist = tasklist;
        this.count = tasklist.size();
    }

    /**
     * Adds task to tasklist.
     *
     * @param task the task to be added to tasklist.
     */
    public void add(Task task) {
        tasklist.add(task);
        count++;
    }

    /**
     * Deletes task from tasklist.
     *
     * @param index the index of the task to be deleted in the tasklist.
     * @throws DukeException if index is out of bounds of tasklist.
     */
    public void delete(int index) throws DukeException {
        if (index > count) {
            throw new DukeException("Invalid index! Input a number less than or equal to " + count);
        }
        this.tasklist.remove(index - 1);
        count--;
    }

    /**
     * Marks a task as done.
     *
     * @param index the index of the task to be done in the tasklist.
     * @throws DukeException if index is out of bounds of tasklist.
     */
    public void markDone(int index) throws DukeException {
        if (index > count) {
            throw new DukeException("Invalid index! Input a number less than or equal to " + count);
        }
        this.tasklist.get(index - 1).markDone();
    }

    /**
     * Gets the task in the index of the tasklist.
     *
     * @param index the index of the task to get.
     * @return the task in the index.
     */
    public Task getTask(int index) {
        return this.tasklist.get(index - 1);
    }

    /**
     * Gets the number of tasks in the tasklist.
     *
     * @return the number of tasks in the tasklist.
     */
    public int getNumTasks() {
        return this.count;
    }

    /**
     * Gets the formatted string to be saved in the filemanager.
     *
     * @return the formatted string to be saved in the filemanager
     */
    public String stringSaveFile() {
        String toWrite = "";
        for (Task task: this.tasklist) {
            toWrite += task.typeString() + "\n";
        }
        return toWrite;
    }

    /**
     * Finds tasks that contain keywords.
     *
     * @param keywords the keywords which related tasks contain.
     * @return a list of tasks which contain the keyword.
     */
    public Tasklist findRelated(ArrayList<String> keywords) {
        ArrayList<Task> relatedTasks = new ArrayList<>();
        for (Task task: tasklist) {
            for (String keyword: keywords) {
                if (task.toString().contains(keyword)) {
                    relatedTasks.add(task);
                    break;
                }
            }
        }
        return new Tasklist(relatedTasks);
    }

    /**
     * Adds tags to tasks indicated the indexes
     *
     * @param indexes indexes of tasks to add the tags to
     * @param tags the tags to be added to the tasks
     */
    public Tasklist addTags(ArrayList<Integer> indexes, ArrayList<String> tags) throws DukeException {
        Tasklist taggedTasks = new Tasklist(new ArrayList<>());
        for (int i = 0; i < indexes.size(); i++) {
            if (indexes.get(i) > this.getNumTasks()) {
                throw new DukeException("Invalid input. Index must be less than or equal to " + this.getNumTasks());
            }
            Task currentTask = this.getTask(indexes.get(i));
            taggedTasks.add(currentTask);
            currentTask.addTags(tags);
        }
        return taggedTasks;
    }

    /**
     * Gets a string that shows the list of current tasks.
     *
     * @return string that shows the list of current tasks
     */
    @Override
    public String toString() {
        if (this.count == 0) {
            return "Nothing on your list!";
        }
        String strRepresentation = "";
        int count = 1;
        for (Task task: tasklist) {
            strRepresentation += count + ". " + task + "\n";
            count++;
        }
        return strRepresentation;
    }
}
