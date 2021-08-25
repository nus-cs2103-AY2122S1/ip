package duke.utils;

import duke.tasks.Deadlines;
import duke.tasks.Events;
import duke.tasks.Task;
import duke.tasks.ToDos;

import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.stream.Stream;

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
     * Adds a new task to the TaskList
     *
     * @param newTask the new task to add
     */
    public void addTask(Task newTask) {
        tasks.add(newTask);
    }

    /**
     * Deletes the particular task in the task list that corresponds with the given
     * task number
     *
     * @param taskNum the task number that we want to delete from the task list
     */
    public void deleteTask(int taskNum) {
        tasks.remove(taskNum);
    }

    /**
     * Marks the task in the task list that corresponds with the given task number
     * as done
     *
     * @param taskNum the task number that we want to mark as done
     */
    public void markTaskAsDone(int taskNum) {
        Task currTask = tasks.get(taskNum);
        currTask.markAsDone();
    }

    /**
     * Constructor for TaskList
     *
     * @param persistedData the persisted tasks that we loaded from the hard disk/text file
     */
    public TaskList(Stream<String> persistedData) {
        persistedData.forEach(loadDataAction);
    }

    /**
     * Returns the task that corresponds with the given task number
     *
     * @param taskNum the task number of the task that you want to retrieve
     * @return the task corresponding to the given task number
     */
    public Task getTask(int taskNum) {
        return tasks.get(taskNum);
    }

    /**
     * Returns the number of tasks in the TaskList
     *
     * @return the number of tasks in the TaskList
     */
    public int getSize() {
        return tasks.size();
    }
}
