package duke;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import duke.task.Task;

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
    public String markTaskDoneById(int id) {
        Task task = taskList.get(id);
        task.markDone();
        return "Nice! I've marked this task as done: \n\t" + task;
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
    public String findAndDisplay(String searchItem) {
        List<Task> searchList = new ArrayList<>();
        String msg = "";

        //filter out the search item into a list.
        searchList = taskList.stream().filter(x -> x.getValue().contains(searchItem)).collect(Collectors.toList());

        if (searchList.size() > 0) {
            msg = "Here are the matching tasks in your list: \n";
            for (int i = 0; i < searchList.size(); i++) {
                int index = i + 1;
                msg += index + "." + searchList.get(i) + "\n";
            }
        } else {
            msg = "Sorry there are no items that match your query!";
        }

        return msg;
    }

    /**
     * Remove task by the given index.
     *
     * @param id Index of the task given in the list of Tasks.
     */
    public void removeTaskById(int id) {
        assert id >= 0;
        taskList.remove(id);
    }
}
