package src.main.java.duke;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to represent the list of tasks.
 */

public class TaskList {

    private ArrayList<Task> list = new ArrayList<>();

    /**
     * method to add the task to the list in src.main.java.duke.TaskList.
     *
     * @param task
     */
    void add(Task task) {
        list.add(task);
    }

    /**
     * method to remove the specified task from the list.
     *
     * @param n index of the task.
     * @return String representation of the task deleted.
     * @throws DukeException
     */
    String remove(int n) throws DukeException {
        if (n > list.size()) {
            throw new TaskNotFoundException("list has only " + list.size() + "tasks. Enter a valid task");
        }

        String deletedTask = list.get(n - 1).toString();
        list.remove(n - 1);
        return deletedTask;
    }

    /**
     * getter for accessing a particular from the list.
     *
     * @param n index of the task.
     * @return task object which is to be accessed.
     */
    Task get(int n) {
        return list.get(n);
    }

    /**
     * method to mark a task in the list as done.
     *
     * @param n index of the task.
     * @throws DukeException
     */
    void markAsDone(int n) throws DukeException {
        if (n > list.size()) {
            throw new TaskNotFoundException("list has only " + list.size() + " tasks. Enter a valid task");
        }
        list.get(n - 1).markAsDone();
    }

    /**
     * method to convert the task list into a list of string to be stored in the database.
     *
     * @return list of the tasks in String form.
     */
    List<String> convertListtoStringList() {
        ArrayList<String> content = new ArrayList<>();
        list.forEach((elem) -> {
            if (elem instanceof Deadline) {
                content.add(((Deadline) elem).makeStorageString());
            } else {
                content.add(elem.toString());
            }
        });
        return content;
    }

    /**
     * method to find all tasks in the list that match the given text.
     *
     * @param text Keyword to be searched.
     * @return list of all matching tasks.
     */
    List<Task> find(String text) {
        ArrayList<Task> content = new ArrayList<>();

        list.forEach((elem) -> {
            if (elem.getTask().contains(text)) {
                content.add(elem);
            }
        });
        return content;
    }

    /**
     * method to convert list of tasks to String form for printing.
     *
     * @return list of tasks in String form.
     */
    String printList() {
        StringBuilder text = new StringBuilder();
        if (list.size() == 0) {
            return "The list has no tasks\n";
        }
        for (int i = 0; i < this.list.size(); i++) {
            text.append(i + 1).append(". ").append(list.get(i).toString()).append("\n");
        }
        return text.toString();
    }

    /**
     * method to update the time of the task at the given index to the new specified time.
     *
     * @param index integer index of the task in the list.
     * @param time  time in String to be set to the task.
     * @return updated task in String form.
     */
    String update(int index, String time) throws DukeException {
        Task task = list.get(index - 1);
        task.setTime(time);
        return task.toString();
    }


    int getSize() {
        return list.size();
    }
}
