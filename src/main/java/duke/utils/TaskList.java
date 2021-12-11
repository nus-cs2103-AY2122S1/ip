package duke.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Consumer;
import java.util.stream.Stream;

import duke.tasks.Deadlines;
import duke.tasks.Events;
import duke.tasks.Task;
import duke.tasks.ToDos;

/**
 * Contains all the listed tasks in the list, and has multiple operations to operate
 * on the list of tasks, such as adding, deleting, marking as done etc.
 */
public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();

    private final Consumer<String> loadDataAction = s -> {
        String[] loadedLine = s.split(",");
        char typeOfTask = loadedLine[0].charAt(0);
        boolean isDone = loadedLine[1].charAt(0) == '1';
        String description = loadedLine[2];
        Task loadedTask;
        switch (typeOfTask) {
        case 'T':
            loadedTask = new ToDos(description, isDone);
            tasks.add(loadedTask);
            break;
        case 'D':
            String byPart = loadedLine[3];
            loadedTask = new Deadlines(description, byPart, isDone);
            tasks.add(loadedTask);
            break;
        case 'E':
            String atPart = loadedLine[3];
            loadedTask = new Events(description, atPart, isDone);
            tasks.add(loadedTask);
            break;
        default:
            throw new IllegalStateException("Unexpected value: " + typeOfTask);
        }
    };

    /**
     * Constructor for TaskList.
     *
     * @param persistedData the persisted tasks that we loaded from the hard disk/text file.
     */
    public TaskList(Stream<String> persistedData) {
        persistedData.forEach(loadDataAction);
    }

    /**
     * Adds a new task to the TaskList.
     *
     * @param newTask the new task to add.
     */
    public void addTask(Task newTask) {
        tasks.add(newTask);
    }

    /**
     * Deletes the particular task in the task list that corresponds with the given
     * task number.
     *
     * @param taskNum the task number that we want to delete from the task list.
     */
    public void deleteTask(int taskNum) {
        tasks.remove(taskNum);
    }

    /**
     * Marks the task in the task list that corresponds with the given task number
     * as done.
     *
     * @param taskNum the task number that we want to mark as done.
     */
    public void markTaskAsDone(int taskNum) {
        boolean taskNumberInRange = (taskNum >= 0) && (taskNum < tasks.size());
        assert (taskNumberInRange) : "Task number that you want to mark as done is out of bounds.";

        Task currTask = tasks.get(taskNum);
        currTask.markAsDone();
    }

    /**
     * Returns the task that corresponds with the given task number.
     *
     * @param taskNum the task number of the task that you want to retrieve.
     * @return the task corresponding to the given task number.
     */
    public Task getTask(int taskNum) {
        boolean taskNumberInRange = (taskNum >= 0) && (taskNum < tasks.size());
        assert (taskNumberInRange) : "Task number that you want to get is out of bounds.";
        return tasks.get(taskNum);
    }

    /**
     * Returns the number of tasks in the TaskList.
     *
     * @return the number of tasks in the TaskList.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Returns a new instance of TaskList containing the tasks that matches the keyword.
     *
     * @param keyword the word in which to search within all the tasks.
     * @return a new instance of TaskList containing the tasks that matches the keyword.
     */
    public HashMap<String, Task> getMatchingTasks(String keyword) {
        HashMap<String, Task> matchingTasks = new HashMap<>();
        int counter = 1; // Start from 1 because that is how we print
        for (Task currTask : tasks) {
            if (currTask.toString().contains(keyword)) {
                matchingTasks.put(String.valueOf(counter), currTask);
            }
            counter += 1;
        }
        return matchingTasks;
    }

    /**
     * Returns an array list containing all the deadlines that are in the task list currently.
     *
     * @return array list containing all the deadlines in the task list currently.
     */
    public ArrayList<Deadlines> getAllDeadlines() {
        ArrayList<Deadlines> allDeadlines = new ArrayList<>();
        for (Task task : tasks) {
            if (task instanceof Deadlines) {
                Deadlines toAdd = (Deadlines) task;
                allDeadlines.add(toAdd);
            }
        }
        return allDeadlines;
    }

    /**
     * Returns the index at which this deadline exists within the full task list, but adding 1 to it.
     * This number is used for displaying to the user, hence it starts counting from 1.
     *
     * @param deadline the deadlines instance to retrieve the index for.
     * @return integer representing the task number at which this deadline instance exist within the task list.
     */
    public int getTaskNumberOf(Deadlines deadline) {
        return tasks.indexOf(deadline) + 1;
    }
}
