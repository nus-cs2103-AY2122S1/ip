package duke;

import exceptions.NoSuchCommandException;
import exceptions.NoTaskNameException;
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
            allTasks += (i + 1) + ". " + taskList.get(i).toString() + "\n";
        }
        return allTasks;
    }

    /**
     * Marks the taskNum-th item in the tasks list as completed.
     * @param taskNum integer index of the task to be marked
     */
    public void doTask(int taskNum) {
        taskList.get(taskNum).doTask();
    }

    /**
     * Adds a task to the taskList and prints a success message.
     * @param task the task to be added
     */
    public void add(Task task) {
        this.taskList.add(task);
        System.out.println("Alright, I've added the following task:");
        System.out.println(taskList.get(taskList.size() - 1) + "\nNow you have " + taskList.size() + " tasks in the list.\n");
    }

    /**
     * Removes a task from the taskList.
     * @param taskIndex integer index of the task to be deleted
     */
    public void delete(int taskIndex) {
        taskList.remove(taskIndex);
    }

    /**
     * Returns the taskList.
     * @return an ArrayList holding the list of tasks
     */
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Returns the number of items in the TaskList.
     *
     * @return size of TaskList
     */
    public int size() {
        return this.taskList.size();
    }
}
