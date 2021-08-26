package duke.task;

import duke.Ui;

import java.util.ArrayList;

/**
 * This class represents a TaskList, which is the list of tasks that Duke refers to to carry out commands provided
 * to him.
 */

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Adds an Event to the TaskList.
     *
     * @param event event to be added to the TaskList
     */
    public void add(Event event, boolean print) {
        this.tasks.add(event);
        if (print) {
            Ui.addTask(event);
            Ui.numberOfTasks(tasks);
        }
    }


    /**
     * Adds a deadline to the TaskList.
     *
     * @param deadline deadline to be added to the TaskList
     */
    public void add(Deadline deadline, boolean print) {
        this.tasks.add(deadline);
        if (print) {
            Ui.addTask(deadline);
            Ui.numberOfTasks(tasks);
        }
    }

    /**
     * Adds a toDo to the TaskList.
     *
     * @param toDo deadline to be added to the TaskList
     */
    public void add(ToDo toDo, boolean print) {
        this.tasks.add(toDo);
        if (print) {
            Ui.addTask(toDo);
            Ui.numberOfTasks(tasks);
        }
    }

    /**
     * Finishes a task at a given index.
     *
     * @param index index from 1 (i.e lowest index is 1, so subtract 1 to get real index)
     */
    public void finishTask(int index) {
        Task task = this.tasks.get(index - 1);
        task.doneTask(true);
    }

    /**
     * Lists out the current items in the TaskList.
     */
    public void listOut() {
        Ui.listTasks(tasks);
    }

    /**
     * This method is to be used when quitting and saving the file.
     *
     * @return the resulting string containing save-friendly information
     */
    public String save() {
        String output = "";
        for (Task task: tasks) {
            output += task.type() + " | " + (task.getState() ? "1 | " : "0 | ") + task.getSaveInfo() + "\n";
        }
        return output;
    }

    /**
     * Deletes a task at a certain index
     *
     * @param index index from 1 (i.e lowest index is 1, so subtract 1 to get real index)
     */
    public void deleteTask(int index) {
        Task item = this.tasks.remove(index - 1);
        Ui.deleteTask(item);
        Ui.remainingTasks(tasks);
    }
}
