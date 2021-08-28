package duke;

import java.util.ArrayList;

/**
 * Contains the task list e.g., it has operations to add/delete tasks in the list
 */
public class TaskList {
    private ArrayList<Task> myTasks;

    public TaskList(ArrayList<Task> myTasks) {
        this.myTasks = myTasks;
    }

    public TaskList() {
        myTasks = new ArrayList<>();
    }

    public ArrayList<Task> getTasks() {
        return myTasks;
    }

    public int getSize() {
        return myTasks.size();
    }

    public void printTaskList() {
        for (int i = 1; i <= myTasks.size(); i++) {
            System.out.println(i + ". " + myTasks.get(i - 1));
        }
    }

    public void markAsDone(int index, boolean printMsg) {
        Task task = myTasks.get(index);
        boolean isDone = task.markAsDone();
        if (printMsg) {
            Ui.taskDoneMessage(task, isDone);
        }
    }

    public void deleteTask(int index) {
        Task task = myTasks.remove(index);
        Ui.removeTaskMsg(task, myTasks.size());
    }

    public void addTask(Task task, boolean printMsg) {
        myTasks.add(task);
        if (printMsg) {
            Ui.addTaskMsg(task, myTasks.size());
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