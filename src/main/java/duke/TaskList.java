package duke;

import exceptions.NoSuchCommandException;
import exceptions.NoTaskNameException;
import exceptions.TaskDoesNotExistException;
import tasks.Task;

import java.io.*;
import java.util.ArrayList;

/**
 * Encapsulates all information for a list of Tasks.
 *
 * @author Quan Teng Foong
 */
public class TaskList {
    private final ArrayList<Task> taskList = new ArrayList<>();

    public TaskList() {}

    /**
     * Constructor that takes in a File of previously stored tasks to initialize a
     * TaskList.
     *
     * @param storedTasks a File containing previously saved tasks
     * @throws IOException if there is an error opening the file or reading lines
     * from it
     * @throws NoTaskNameException if there is no task name
     * @throws NoSuchCommandException if the command does not match any of the known
     * commands
     */
    public TaskList(File storedTasks) throws IOException, NoTaskNameException, NoSuchCommandException{
        FileReader fileReader = new FileReader(storedTasks);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = bufferedReader.readLine();
        while (line != null) {
            this.taskList.add(Task.createTaskFromString(line));
            line = bufferedReader.readLine();
        }
        fileReader.close();
    }

    /**
     * Overrides toString() method.
     * @return string representation of the TaskList object
     */
    @Override
    public String toString() {
        String allTasks = "";
        for (int i = 0; i < taskList.size(); i++) {
            allTasks += "      " + (i + 1) + ". " + taskList.get(i).toString() + "\n";
        }
        return allTasks;
    }

    /**
     * Marks the taskNum-th item in the tasks list as completed.
     * @param taskIndex integer index of the task to be marked
     */
    public void doTask(int taskIndex) throws TaskDoesNotExistException {
        if (taskIndex >= this.taskList.size()) {
            throw new TaskDoesNotExistException("Task number out of bounds!");
        } else {
            this.taskList.get(taskIndex).doTask();
        }
    }

    /**
     * Adds a task to the TaskList and prints a success message.
     * @param task the task to be added
     */
    public void add(Task task) {
        this.taskList.add(task);
    }

    /**
     * Removes a task from the taskList.
     * @param taskIndex integer index of the task to be deleted
     */
    public void delete(int taskIndex) throws TaskDoesNotExistException {
        if (taskIndex >= this.taskList.size()) {
            throw new TaskDoesNotExistException("Task number out of bounds!");
        } else {
            this.taskList.remove(taskIndex);
        }
    }

    /**
     * Converts the TaskList to a storable string.
     *
     * @return String to be stored
     */
    public String toStorage() {
        String storage = "";
        for (Task task : taskList) {
            storage += task.toStorage() + "\n";
        }
        return storage;
    }

    /**
     * Returns the number of items in the TaskList.
     *
     * @return size of TaskList
     */
    public int size() {
        return this.taskList.size();
    }

    /**
     * Returns the Task object at the respective taskIndex.
     * @param taskIndex integer index of object to return
     * @return a Task
     */
    public Task get(int taskIndex) {
        return this.taskList.get(taskIndex);
    }

    /**
     * Searches all Tasks in the TaskList to find all Tasks that contain searchTerm.
     *
     * @param searchTerm search term to find
     * @return an ArrayList containing all Tasks with the search term in their message
     */
    public ArrayList<Task> search(String searchTerm) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task: this.taskList) {
            if (task.getMessage().contains(searchTerm)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}
