package duke;

import java.util.ArrayList;
/**
 * Contains the task list e.g. it has operation to add/delete tasks in the list
 *
 * @author Timothy Wong Eu-Jin
 */

public class TaskList {

    private ArrayList<Task> arrayList;

    /** Constructor for a new TaskList */
    public TaskList() {
        this.arrayList = new ArrayList<Task>();
    }

    /** Constructor for an empty TaskList */
    public TaskList(ArrayList<Task> arrayList) {
        this.arrayList = arrayList;
    }

    /** Adds a task to the TaskList. */
    public void add(Task task) {
        this.arrayList.add(task);
    }

    /** Returns a task at the specified index. */
    public Task getIndex(int index) throws InvalidDescriptionException {
        if (index < 1 || index > arrayList.size()) {
            throw new InvalidDescriptionException("The task you have indicated does not exist!");
        }
        return this.arrayList.get(index - 1);
    }

    /** Marks a specific task as done, returns the task. */
    public Task markAsDone(int index) throws InvalidDescriptionException {
        Task completedTask = this.getIndex(index);
        completedTask.markAsDone();
        return completedTask;

    }

    /** Deletes a specific task, returns the task. */
    public Task deleteTask(int index) throws InvalidDescriptionException {
        Task deletedTask = this.getIndex(index);
        this.arrayList.remove(index - 1);
        return deletedTask;
    }

    /**
     * Searches the TaskList for tasks containing keyword.
     *
     * @param keyword Provided by user.
     * @return ArrayList Matched tasks.
     */
    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> matchedTasks = new ArrayList<>();
        for (Task t : arrayList) {
            if (t.getDescription().contains(keyword)) {
                matchedTasks.add(t);
            }
        }
        return matchedTasks;
    }

//    //getNumOfTasks method prints the number of tasks in the list
//    public void getNumOfTasks() {
//        int totalNum = this.array.size();
//        if (totalNum == 1) {
//            System.out.println("Now you have " + totalNum + " task in the list");
//        } else {
//            System.out.println("Now you have " + totalNum + " tasks in the list");
//        }
//    }

    //getAll method to return all entries in list
    public ArrayList<Task> getAll() {
        return this.arrayList;
    }

}
