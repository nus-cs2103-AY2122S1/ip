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
    public String getTaskList() {
        String returnStr = "";
        for (int i = 1; i <= myTasks.size(); i++) {
            returnStr += i + ". " + myTasks.get(i - 1) + "\n";
        }
        return returnStr;
    }

    /**
     * Marks a task as done.
     *
     * @param index index of the task to be marked as done
     * @return a message
     */
    public String markAsDone(int index) {
        Task task = myTasks.get(index);
        boolean isDone = task.markAsDone();
        return Ui.getTaskDoneMessage(task, isDone);
    }

    /**
     * Deletes a task.
     *
     * @param index index of the task to be deleted
     * @return a message
     */
    public String deleteTask(int index) {
        Task task;
        try {
            task = myTasks.remove(index);
        } catch (IndexOutOfBoundsException e) {
            return Ui.getDeleteError();
        }
        return Ui.getRemoveTaskMsg(task, myTasks.size());
    }

    /**
     * Adds a task
     *
     * @param task the task to be added to the list of tasks
     * @return a message
     */
    public String addTask(Task task) {
        myTasks.add(task);
        return Ui.getAddTaskMsg(task, myTasks.size());
    }

    /**
     * Adds a tag to a task.
     *
     * @param index the index of the task
     * @param tag the tag to be added
     * @return a confirmation message
     */
    public String addTag(int index, String tag) {
        return myTasks.get(index).addTag(tag);
    }

    /**
     * Show tasks if there are any that match the search.
     * Else tells user that search has yielded nothing.
     *
     * @param searchString the thing that user is searching for
     * @return a message
     */
    public String findTask(String searchString) {
        boolean hasMatches = false;
        String returnStr = "";
        for (int i = 1; i <= myTasks.size(); i++) {
            Task currTask = myTasks.get(i - 1);
            if (currTask.description.contains(searchString)) {
                hasMatches = true;
                returnStr += i + ". " + currTask + "\n";
            }
        }
        if (!hasMatches) {
            return "Nothing matched your search! Try something else.";
        } else {
            assert !returnStr.isEmpty(); // Return value from tasks should never be empty
            return returnStr;
        }
    }
}
