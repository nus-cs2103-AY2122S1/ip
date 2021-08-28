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
            System.out.println(i + ". " + myTasks.get(i - 1).toString());
        }
    }

    public void markAsDone(int index, boolean printMsg) {
        Task task = myTasks.get(index);
        boolean isDone = task.markAsDone();
        if (printMsg) {
            Ui.showTaskDoneMessage(task, isDone);
        }
    }

    public void deleteTask(int index) {
        Task task = myTasks.remove(index);
        Ui.showRemoveTaskMsg(task, myTasks.size());
    }

    public void addTask(Task task, boolean printMsg) {
        myTasks.add(task);
        if (printMsg) {
            Ui.showAddTaskMsg(task, myTasks.size());
        }
    }
}
