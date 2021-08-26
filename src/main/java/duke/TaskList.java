package duke;

import duke.task.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * TaskList manages the tasks for Duke.
 */
public class TaskList {

    private List<Task> taskList = new ArrayList<>();

    /**
     * Constructor that initialises an existing taskList.
     *
     * @param taskList A List of task given to initialise TaskList with.
     */
    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public TaskList() {

    }

    /**
     * Get the total number of existing tasks in Duke.
     *
     * @return The number of tasks.
     */
    public int getTotalNumberOfTask() {
        return taskList.size();
    }

    /**
     * Retrieve the task object by their index.
     *
     * @param id Index of the task given in the list of Tasks.
     * @return The selected task by the given id.
     */
    public Task getTaskById(int id) {
        return taskList.get(id);
    }

    /**
     * Set the Task given by the id to be done.
     *
     * @param id Index of the task given in the list of Tasks.
     */
    public void markTaskDoneById(int id) {
        Task task = taskList.get(id);
        task.markDone();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("\t" + task);
    }

    /**
     * Add task to the list of Tasks.
     *
     * @param task The task that is to be added.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Search and display item matching the query.
     *
     * @param searchItem The query.
     */
    public void findAndDisplay(String searchItem) {
        List<Task> searchList = new ArrayList<>();

        for (Task task : taskList) {
            if (task.getValue().contains(searchItem)) {
                searchList.add(task);
            }
        }

        if (searchList.size() > 0) {
            System.out.println("Here are the matching tasks in your list: ");
            for (int i = 0; i < searchList.size(); i++) {
                int index = i + 1;
                System.out.println(index + "." + searchList.get(i));
            }
        } else {
            System.out.println("Sorry there are no items that match your query!");
        }
    }

    /**
     * Remove task by the given index.
     *
     * @param id Index of the task given in the list of Tasks.
     */
    public void removeTaskById(int id) {
        taskList.remove(id);
    }
}
