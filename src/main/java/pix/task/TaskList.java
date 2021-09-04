package pix.task;

import java.util.ArrayList;

import pix.Pix;
import pix.exception.PixException;
import pix.exception.PixIoException;
import pix.exception.PixNoSuchTaskException;
import pix.storage.Storage;

/**
 * Class to manage the task list.
 */
public class TaskList {
    private Storage storage;
    private ArrayList<Task> taskList;
    private Pix pix;

    /**
     * Constructor for empty task list.
     */
    public TaskList(Storage storage, Pix pix) {
        this.storage = storage;
        this.taskList = new ArrayList<>();
        this.pix = pix;
    }

    /**
     * Constructor for a task list that has pre-loaded data.
     *
     * @param taskList task.Task list to copy from.
     */
    public TaskList(ArrayList<Task> taskList, Storage storage, Pix pix) {
        this.taskList = taskList;
        this.storage = storage;
        this.pix = pix;
    }

    /**
     * Getter for the task list.
     *
     * @return Returns the task list.
     */
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Setter for task list.
     */
    public void setTaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds a task to the taskList.
     *
     * @param task The task to be added
     * @return Returns the string representation of the task added.
     */
    public String addTask(Task task) {
        taskList.add(task);
        String res = "Added this task: \n" + task;
        res += "\nYou now have " + taskList.size() + " task(s) in your list";
        try {
            System.out.println("add");
            storage.writeToFile(taskList);
        } catch (PixIoException e) {
            //The format should be set and there shouldn't be any I/O error.
        }
        return res;
    }

    /**
     * Displays the task list.
     *
     * @return Returns the string representation of the task list.
     */
    public String displayList() {
        String res = "";

        if (taskList.size() == 0) {
            return "There is nothing in the list, go and do something!";
        } else {
            res += "Why can't you keep track of these yourself:\n";
            for (int i = 0; i < taskList.size(); i++) {
                res += (i + 1) + ". " + taskList.get(i).toString() + "\n";
            }
            return res;
        }
    }

    /**
     * Sets the selected task to be completed.
     *
     * @param n The number of the task to be completed.
     * @return Returns the string representation of the task completed.
     * @throws PixNoSuchTaskException Throws the exception when the task does not exist.
     */
    public String completeTask(int n) throws PixException {
        String res = "Wow. You did it. Yay.\n";
        try {
            taskList.get(n - 1).completeTask();
            res += taskList.get(n - 1).toString() + "\n";
            storage.writeToFile(taskList);
        } catch (PixIoException e) {
            //The format should be set and there shouldn't be any I/O error.
        } catch (IndexOutOfBoundsException e) {
            throw new PixNoSuchTaskException();
        }

        return res;
    }

    /**
     * Sets the selected task to be uncompleted
     *
     * @param n The number of the task to be uncompleted.
     * @return Returns the string representation of the task uncompleted.
     * @throws PixNoSuchTaskException Throws the exception when the task does not exist.
     * */
    public String uncompleteTask(int n) throws PixException {
        String res = "Looks like you're back for more.\n";
        try {
            taskList.get(n - 1).uncompleteTask();
            res += taskList.get(n - 1).toString() + "\n";
            storage.writeToFile(taskList);
        } catch (PixIoException e) {
            //The format should be set and there shouldn't be any I/O error.
        } catch (IndexOutOfBoundsException e) {
            throw new PixNoSuchTaskException();
        }

        return res;
    }

    /**
     * Deletes the selected task from the task List.
     *
     * @param n The number of the task to be deleted.
     * @return Returns the string representation of the task deleted.
     */
    public String deleteTask(int n) throws PixException {
        String res = "";
        try {
            Task taskToDelete = taskList.get(n - 1);
            res += "Given up already? Task removed:\n";
            res += taskToDelete + "\n";
            taskList.remove(n - 1);
            res += "You now have " + taskList.size() + " task(s) in your list.";
            storage.writeToFile(taskList);
        } catch (PixIoException e) {
            //The format should be set and there shouldn't be any I/O error.
        } catch (IndexOutOfBoundsException e) {
            throw new PixNoSuchTaskException();
        }

        return res;
    }

    /**
     * Finds all tasks in the task list that contain the keyword.
     *
     * @param keyword Keyword to search for.
     * @return Returns a list of all the tasks containing the keyword.
     */
    public String findTasks(String keyword) {
        String res = "Here are the matching tasks in your list:\n";
        for (int i = 1; i < taskList.size() + 1; i++) {
            if (taskList.get(i - 1).getTaskName().contains(keyword)) {
                res += i + ". " + taskList.get(i - 1).toString() + "\n";
            }
        }

        return res;
    }
}
