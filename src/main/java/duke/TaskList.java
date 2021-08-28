package duke;

import java.util.ArrayList;

/**
 * Contains the task list e.g., it has operations to add/delete tasks in the list
 */
public class TaskList {
    private ArrayList<Task> myTasks;

    /**
     * Constructor.
     */
    public TaskList() {
        myTasks = new ArrayList<>();
    }

    /**
     * Gets the list of tasks.
     *
     * @return the list of tasks
     */
    public ArrayList<Task> getTasks() {
        return myTasks;
    }

    /**
     * Get the size of the list of tasks.
     *
     * @return the size of the list of tasks.
     */
    public int getSize() {
        return myTasks.size();
    }

    /**
     * Prints all the tasks in the list of tasks.
     */
    public void printTaskList() {
        for (int i = 1; i <= myTasks.size(); i++) {
            System.out.println(i + ". " + myTasks.get(i - 1));
        }
    }

    /**
     * Marks a task as done.
     *
     * @param index index of the task to be marked as done
     * @param printMsg boolean flag for whether to print a confirmation message or not
     */
    public void markAsDone(int index, boolean printMsg) {
        Task task = myTasks.get(index);
        boolean isDone = task.markAsDone();
        if (printMsg) {
            Ui.showTaskDoneMessage(task, isDone);
        }
    }

    /**
     * Deletes a task.
     *
     * @param index index of the task to be deleted
     */
    public void deleteTask(int index) {
        Task task = myTasks.remove(index);
        Ui.showRemoveTaskMsg(task, myTasks.size());
    }

    /**
     * Adds a task
     *
     * @param task the task to be added to the list of tasks
     * @param printMsg boolean flag for whether to print a confirmation message or not
     */
    public void addTask(Task task, boolean printMsg) {
        myTasks.add(task);
        if (printMsg) {
            Ui.showAddTaskMsg(task, myTasks.size());
        }
    }

    /**
     * Show tasks if there are any that match the search.
     * Else tells user that search has yielded nothing.
     *
     * @param searchString the thing that user is searching for
     */
    public void findTask(String searchString) {
        boolean hasMatches = false;
        for (int i = 1; i <= myTasks.size(); i++) {
            Task currTask = myTasks.get(i - 1);
            if (currTask.description.contains(searchString)) {
                hasMatches = true;
                System.out.println(i + ". " + currTask);
            }
        }
        if (!hasMatches) {
            System.out.println("Nothing matched your search! Try something else.");
        } else {
            System.out.println("End of find.");
        }
    }
}