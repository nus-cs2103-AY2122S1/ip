package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import duke.DukeException;

public class TaskList {
    private ArrayList<Task> list;

    /**
     * Constructor for a new list of tasks.
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Constructor for TaskList when a list of tasks is loaded from a file.
     *
     * @param l input arraylist of tasks.
     */
    public TaskList(ArrayList<Task> l) {
        this.list = l;
    }

    /**
     * Adds new task to TaskList.
     *
     * @param input task to be added.
     */
    public void addToList(Task input) {
        list.add(input);
    }

    /**
     * Returns a particular task at a particular index of the task list.
     *
     * @param index index of a particular task.
     * @return task at the input index.
     */
    public Task getItem(int index) {
        if (this.list.size() <= index || index < 0) {
            return null;
        }
        return this.list.get(index);
    }

    public int getLength() {
        return this.list.size();
    }

    /**
     * Deletes a particular task from the list.
     *
     * @param index index of a particular task.
     * @return deleted task.
     */
    public Task deleteTask(int index) {
        return this.list.remove(index);
    }

    /**
     * Formats the tasks and the TaskList as a whole into a string form that will be stored on the users' hard drive
     *
     * @return String representation of all the tasks in the TaskList.
     */
    public String format() {
        String x = "";
        for (Task task : this.list) {
            x += task.formatTask() + "\n";
        }
        return x;
    }

    /**
     * Retrieves tasks form the TaskList that have the same date as the input.
     *
     * @param date Date of tasks to be searched for
     * @return array of tasks that are on the same date as the inputted date.
     */

    public Task[] tasksOnDate(String date) throws DukeException {
        try {
            LocalDate search = LocalDate.parse(date.trim(), DateTimeFormatter.ofPattern("d/MM/yyyy"));
            // use stream filter function to find results based on inputted date
            return this.list.stream().filter(task -> task.compareDate(search)).toArray(Task[]::new);

        } catch (DateTimeParseException e) {
            throw new DukeException("☹ OOPS!!! Invalid date format");
        }
    }

    /**
     * Finds and returns tasks by keyword.
     *
     * @param search keyword input.
     * @return Array of tasks containing given keyword.
     */
    public Task[] findByKeyword(String search) {
        // use stream filter function to find results based on keyword
        return this.list.stream().filter(task -> task.compareKeyword(search)).toArray(Task[]::new);
    }

    @Override
    public String toString() {
        if (list.isEmpty()) {
            return "No tasks added yet!";
        }
        String l = "Here are the tasks on your list:";
        for (Task s : list) {
            l += "\n" + (list.indexOf(s) + 1) + ". " + s;
        }
        return l;
    }
}
