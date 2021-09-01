package task;

import java.util.ArrayList;

import duke.DukeException;
import ui.Ui;

/**
 * Class that contains the initialization of a list to store the tasks
 *
 * @author: Wei Yangken
 */
public class Tasklist {
    private ArrayList<Task> taskList;

    /**
     * Constructor to create a new taskList to store tasks
     */
    public Tasklist(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds task to list and notifies the user if task has not been added properly
     * @param task Task to be added
     */
    public void add(Task task) {
        try {
            if (task == null) {
                throw new DukeException.TaskNotAddedException("Task has not been added successfully.");
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            Ui.printBreakline();
            return;
        }

        this.taskList.add(task);
        System.out.println("Got it. I've added this task:");
        String addMsg = String.format("%s", task);
        String counterMsg = String.format("Now you have %d tasks in the list.", taskList.size());
        System.out.println(addMsg);
        System.out.println(counterMsg);
        Ui.printBreakline();
    }

    /**
     * Lists out the tasks present in tasklist.
     * Checks for the scenario where list is empty.
     * @return
     */
    public String list() {
        String returnMsg = "";
        try {
            if (this.taskList.size() == 0) {
                throw new DukeException.EmptyListException("List is empty :(");
            }
        } catch (DukeException e) {
            returnMsg += e.getMessage();
            Ui.printBreakline();
            return returnMsg;
        }

        returnMsg += String.format("Here are the tasks in your list:\n");
        for (int i = 0; i < taskList.size(); i++) {
            returnMsg += String.format("%d. %s\n", i + 1, this.taskList.get(i).toString());
        }
        Ui.printBreakline();
        return returnMsg;

    }

    /**
     * Deletes the indicated task from tasklist.
     * Accounts for situations where task could not be found.
     * @param idx Ranking of task to be deleted
     */
    public void delete(int idx) {
        Task removedTask;
        try {
            removedTask = this.taskList.remove(idx - 1);
        } catch (IndexOutOfBoundsException e) {
            System.out.printf("Task number %d not found.\n", idx);
            Ui.printBreakline();
            return;
        }
        String removeMsg = String.format("Noted. I've removed this task:\n%s", removedTask.toString());
        System.out.println(removeMsg);
        System.out.printf("Now you have %d task(s) in the list.\n", taskList.size());
        Ui.printBreakline();
    }

    /**
     * Check if string is found in tasklist
     * @param item key to search for
     */
    public void findString(String item) {
        Tasklist filteredList = new Tasklist(new ArrayList<>());
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            if (task.getName().contains(item)) {
                filteredList.add(task);
            }
        }
        filteredList.list();
    }

    /**
     * Returns the task of a particular index in arraylist
     * @param idx Index in the arraylist
     * @return Task of a particular index
     */
    public Task getTask(int idx) throws DukeException.TaskNotFoundException {
        Task task = null;
        try {
            task = taskList.get(idx);
        } catch (IndexOutOfBoundsException e) {
            String errMsg = String.format("Task number %d not found.", idx + 1);
            throw new DukeException.TaskNotFoundException(errMsg);
        }
        return task;
    }

    /**
     * Sets task to completed
     *
     * @param idx Index of task set to compeleted
     */
    public void setToCompleted(int idx) {
        try {
            Task task = this.getTask(idx);
            task.setToCompleted();
        } catch (DukeException.TaskNotFoundException e) {
            System.out.println(e.getMessage());
            Ui.printBreakline();
        }
    }

    /**
     * @return size of todo list
     */
    public int size() {
        return this.taskList.size();
    }

    /**
     * @param idx Index of list
     * @return Object of specified index on list
     */
    public Task get(int idx) {
        return taskList.get(idx);
    }

    /**
     * @param o List to be compared to
     * @return Whether the two list share the same tasks
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Tasklist)) {
            return false;
        }
        Tasklist tasklist = (Tasklist) o;
        if (tasklist.size() != this.size()) {
            return false;
        } else {
            for (int i = 0; i < this.size(); i++) {
                if (!tasklist.get(i).equals(this.get(i))) {
                    System.out.printf("different %d", i);
                    return false;
                }
            }
            return true;
        }
    }
}


