package task;

import exception.PixIOException;
import exception.PixNoSuchTaskException;
import storage.Storage;

import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Constructor for empty task list.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Constructor for a task list that has pre-loaded data.
     *
     * @param taskList task.Task list to copy from.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
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
     * Adds a task to the taskList.
     *
     * @param task The task to be added
     */
    public String addTask(Task task, Storage storage) {
        taskList.add(task);
        String res = "Added this task: \n" + task;
        res += "\nYou now have " + taskList.size() + " task(s) in your list";
        try {
            storage.writeToFile(taskList);
        } catch (IOException | PixIOException e) {
            //The format should be set and there shouldn't be any I/O error.
        }
        return res;
    }

    /**
     * Displays the task list.
     */
    public String displayList() {
        StringBuilder res = new StringBuilder();

        if (taskList.size() == 0) {
            return "There is nothing in the list, go and do something!";
        } else {
            res.append("Why can't you keep track of these yourself:\n");
            for (int i = 0; i < taskList.size(); i++) {
                res.append(i + 1).append(". ").append(taskList.get(i).toString()).append("\n");
            }
            return res.toString();
        }
    }

    /**
     * Sets the selected task to be completed.
     *
     * @param n The number of the task.Task to be completed.
     */
    public String completeTask(int n) throws PixNoSuchTaskException {
        try {
            String res = "Wow. You did it. Yay.\n";
            taskList.get(n - 1).completeTask();
            res += taskList.get(n - 1).toString() + "\n";
            return res;
        } catch (IndexOutOfBoundsException e) {
            throw new PixNoSuchTaskException();
        }
    }

    /**
     * Deletes the selected task from the task.Task List.
     *
     * @param n The number of the task.Task to be deleted.
     */
    public String deleteTask(int n) throws PixNoSuchTaskException {
        try {
            String res = "";
            Task taskToDelete = taskList.get(n - 1);
            res += "Given up already? task. Task removed:\n";
            res += taskToDelete + "\n";
            taskList.remove(n - 1);
            res += "You now have " + taskList.size() + " task(s) in your list.";
            return res;
        } catch (IndexOutOfBoundsException e) {
            throw new PixNoSuchTaskException();
        }
    }
}