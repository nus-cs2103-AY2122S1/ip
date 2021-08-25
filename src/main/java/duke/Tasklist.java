package duke;

import duke.DukeException;
import duke.task.Task;

import java.util.ArrayList;

public class Tasklist {
    private ArrayList<Task> tasklist;
    private int count;

    public Tasklist(ArrayList<Task> tasklist) {
        this.tasklist = tasklist;
        this.count = tasklist.size();
    }

    public void add(Task task) {
        tasklist.add(task);
        count++;
    }

    public void delete(int index) throws DukeException {
        if (index > count) {
            throw new DukeException("Invalid index! Input a number less than or equal to " + count);
        }
        this.tasklist.remove(index - 1);
        count--;
    }

    public void markDone(int index) throws DukeException {
        if (index > count) {
            throw new DukeException("Invalid index! Input a number less than or equal to " + count);
        }
        this.tasklist.get(index - 1).markDone();
    }

    public Task getTask(int index) {
        return this.tasklist.get(index - 1);
    }

    public int getNumTasks() {
        return this.count;
    }

    public String stringSaveFile() {
        String toWrite = "";
        for (Task task: this.tasklist) {
            toWrite += task.typeString() + "\n";
        }
        return toWrite;
    }

    /**
     * Finds tasks that contain a keyword.
     *
     * @param keyword the keyword which related tasks contain.
     * @return a list of tasks which contain the keyword.
     */
    public Tasklist findRelated(String keyword) {
        ArrayList<Task> relatedTasks = new ArrayList<>();
        for (Task task: tasklist) {
            if (task.toString().contains(keyword)) {
                relatedTasks.add(task);
            }
        }
        return new Tasklist(relatedTasks);
    }

    @Override
    public String toString() {
        if (this.count == 0) {
            return "Nothing on your list!";
        }
        String str = "";
        int count = 1;
        for (Task task: tasklist) {
            str += count + ". " + task + "\n";
            count++;
        }
        return str;
    }
}
