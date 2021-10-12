package duke;

import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList {

    private final ArrayList<Task> list;

    public TaskList(ArrayList<Task> list) throws DukeException {
        if (list.isEmpty()) {
            throw new DukeException("Task list is empty!");
        }
        this.list = list;
    }

    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Adds task to tasklist
     *
     * @param task to be added in
     */
    public void addTask(Task task) {
        this.list.add(task);
    }

    /**
     * Removes task from tasklist on specified index
     *
     * @param index of task to be removed
     */
    public void removeTask(int index) {
        this.list.remove(index);
    }

    /**
     * Marks task in list to done given index
     *
     * @param index of task to be marked
     */
    public void markAsDone(int index) {
        this.list.get(index).markAsDone();
    }

    /**
     * Getter for task at specific index
     *
     * @param index of task requested
     * @return Task at the specific index
     */
    public Task getTask(int index) {
        return this.list.get(index);
    }

    /**
     * Edits task at specific index
     *
     * @param index       of task to be edited
     * @param description new description for the task chosen
     * @param date        new date for task chosen
     */
    public void updateTask(int index, String description, LocalDate date) {
        this.list.get(index).markUndone();
        if (!description.equalsIgnoreCase("")) {
            this.getTask(index).changeDescription(description);
        }
        if (date != null) {
            if (this.list.get(index) instanceof Event) {
                ((Event) this.list.get(index)).changeDate(date);
            } else if (this.list.get(index) instanceof Deadline) {
                ((Deadline) this.list.get(index)).changeDate(date);
            }
        }
    }

    /**
     * Get size of current task list
     *
     * @return size of task list
     */
    public int size() {
        return this.list.size();
    }

    /**
     * Find task given a search word
     *
     * @param find string to be searched using
     * @return formatted string to be displayed
     */
    public String findTask(String find) {
        ArrayList<Task> result = new ArrayList<>();
        for (Task t : this.list) {
            if (t.toString().toLowerCase().contains(find.toLowerCase())) {
                result.add(t);
            }
        }
        return TaskListToString(result);
    }

    /**
     * Given an ArrayList of Task returns the String to be displayed in duke
     *
     * @return String to be displayed
     */
    public String TaskListToString(ArrayList<Task> taskArrayList) {
        if (taskArrayList.size() == 0) {
            return "There are no matching task in your list!\n";
        }
        String listContent = "Here are the matching tasks in your list:\n";
        for (int i = 0; i < taskArrayList.size(); i++) {
            listContent += (i + 1) + ". " + taskArrayList.get(i).toString() + "\n";
        }
        return listContent;

    }

    @Override
    public String toString() {
        return TaskListToString(this.list);
    }

}
