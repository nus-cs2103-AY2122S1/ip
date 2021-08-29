package duke.util;

import java.time.LocalDate;
import java.util.ArrayList;

import duke.exception.DukeNoSuchTaskException;
import duke.task.Task;

/**
 * TaskList class represents a list of tasks.
 *
 * @author Chng Zi Hao
 */
public class TaskList {
    private ArrayList<Task> list;
    private Storage storage;

    /**
     * Constructor for TaskList.
     *
     * @param list List of task.
     * @param storage Storage for Duke.
     */
    public TaskList(ArrayList<Task> list, Storage storage) {
        this.list = list;
        this.storage = storage;
    }

    /**
     * Process user input and adds task that user has given and formats the message specific to the task.
     *
     * @param task The task user has specified
     * @return Returns formatted string to be printed out to show task has been added or not.
     */
    public String addTask(Task task) {
        list.add(task);
        String totalTask = String.format("Now you have %d task(s) in the list.", list.size());
        return String.format("Got it! I've added this task:\n  %s\n%s", list.get(list.size() - 1), totalTask);
    }

    /**
     * Prints out the task list and shows all the task user has added in order.
     */
    public void printTaskList() {
        if (list.size() == 0) {
            System.out.println("List is empty. Add something to the list!");
            return;
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            String detail = String.format("%d.%s", i + 1, list.get(i));
            System.out.println(detail);
        }
    }

    /**
     * Marks task as done.
     *
     * @param index index of task to be mark as done.
     * @return a message of whether task has been successfully added or not
     * @throws DukeNoSuchTaskException For invalid indexes given by user.
     */
    public String markTaskDone(int index) throws DukeNoSuchTaskException {
        if (index >= 0 && index < list.size()) {
            if (list.get(index).getStatusIcon().equals(" ")) {
                list.get(index).markDone();
                return "Good job! I've marked this task as done:\n  " + list.get(index);
            } else {
                return "Task has already been completed!";
            }
        } else {
            throw new DukeNoSuchTaskException();
        }
    }

    /**
     * Deletes task at index specified by user.
     *
     * @param index Index of tasks to be deleted.
     * @return String message that task has been deleted.
     * @throws DukeNoSuchTaskException For invalid indexes given by user.
     */
    public String deleteTask(int index) throws DukeNoSuchTaskException {
        if (index >= 0 && index < list.size()) {
            Task removed = list.remove(index);
            String totalTask = String.format("Now you have %d task(s) in the list.", list.size());
            return String.format("Noted! I've removed this task:\n  %s\n%s", removed, totalTask);
        } else {
            throw new DukeNoSuchTaskException();
        }
    }

    /**
     * Converts the list of tasks to a formatted version to be placed in data.txt.
     *
     * @return Formatted string to be placed in data.txt.
     */
    public String toDataFileInput() {
        String data = "";
        for (int i = 0; i < list.size(); i++) {
            data += list.get(i).format() + "\n";
        }
        return data;
    }

    /**
     * Checks and filters task with specific date.
     *
     * @param date Date to be checked against.
     * @return List of task on the specified date.
     */
    public String filterByDate(LocalDate date) {
        String ls = "";
        int count = 1;
        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            if (task.hasDate(date)) {
                ls += String.format("%d.%s\n", count, task);
                count++;
            }
        }
        if (ls.equals("")) {
            return "You do not have any tasks on this day! :>";
        } else {
            return "Here is your list of task on this day:\n" + ls;
        }
    }

    /**
     * Checks and filters task with specific keyword.
     *
     * @param keyword Keyword to be checked against.
     * @return List of task with the specific keyword.
     */
    public String filterByKeyword(String keyword) {
        String ls = "";
        int count = 1;
        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            if (task.hasKeyword(keyword)) {
                ls += String.format("%d.%s\n", count, task);
                count++;
            }
        }
        if (ls.equals("")) {
            return "There is no task with this keyword! :<";
        } else {
            return "Here are the matching tasks in your list:\n" + ls;
        }
    }
}
