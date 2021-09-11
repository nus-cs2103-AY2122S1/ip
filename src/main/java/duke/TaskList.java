package duke;

import java.util.List;

/**
 * This represents a list of tasks.
 */
public class TaskList {

    private List<Task> tasks;
    private Storage storage;
    private boolean isTaskFound = false;

    /**
     * This constructor creates the initial list of tasks for Duke bot by
     * firstly loading all the tasks stored in the given Storage.
     *
     * @param storage where all the tasks are stored on initial load.
     */
    public TaskList(Storage storage) {
        this.storage = storage;
        tasks = storage.readFromStorage();
    }

    /**
     * This method adds the given task into the list of tasks.
     *
     * @param newTask Task object provided to be put into list.
     * @return String to inform user the command is successful.
     */
    public String addToList(Task newTask) {
        tasks.add(newTask);
        storage.writeToStorage(this.getList());

        return newTask.toString();
    }

    /**
     * This method deletes the task from the list of tasks at the given
     * index.
     *
     * @param index the int that tells which task on the list to delete
     * @return String to inform user the command is successful.
     */
    public String deleteFromList(int index) {
        Task curr = tasks.get(index - 1);
        tasks.remove(index - 1);
        storage.writeToStorage(this.getList());

        return curr.toString();
    }

    /**
     * This method marks the task on the list of tasks as done at the
     * given index.
     *
     * @param index the int that tells which task on the list to mark as done.
     * @return String to inform user the command is successful.
     */
    public String taskDone(int index) {
        Task curr = tasks.get(index - 1);
        curr.setDone();
        storage.writeToStorage(this.getList());

        return curr.toString();
    }

    /**
     * This method searches the input string inside the list of tasks and returns
     * all the tasks containing the searchString as a list of String.
     *
     * @param searchString String input to search.
     * @return List of String of tasks that match the search term.
     */
    public String findTask(String searchString) {
        int counter = 1;
        String result = "";
        isTaskFound = false;

        for (int i = 0; i < tasks.size(); i++) {
            assert tasks.get(i) != null : "tasklist has no tasks";
            Task curr = tasks.get(i);
            if (curr.getDescription().contains(searchString)) {
                result += counter + "." + curr + "\n";
                counter++;
                isTaskFound = true;
            }
        }

        return result.trim();
    }

    public boolean isTaskFound() {
        return isTaskFound;
    }

    /**
     * This method tells how many tasks are there in the task list.
     *
     * @return number of task remaining.
     */
    public int taskCount() {
        return tasks.size();
    }

    /**
     * This method transforms the list of task into a String that lists out all
     * the tasks neatly in a numbered order in a user-friendly way.
     *
     * @return String that lists out the tasks.
     */
    public String getList() {
        int counter = 1;
        String result = "";

        for (int i = 0; i < tasks.size(); i++) {
            assert tasks.get(i) != null : "tasklist has no tasks";
            result += counter + ". " + tasks.get(i) + "\n";
            counter++;
        }

        return result.trim();
    }
}
