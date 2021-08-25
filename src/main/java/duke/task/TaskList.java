package duke.task;

import duke.util.DukeException;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Manipulates and stores tasks.
 */
public class TaskList {
    private final LinkedList<Task> tasks = new LinkedList<>();

    /**
     * Retrieve value from a file on local storage to initialise a copy
     * of all the tasks saved in the file.
     *
     * @param file file which stores data about task from previous run session.
     * @throws DukeException file cannot be found or read.
     */
    public void readFile(File file) throws DukeException {
        try {
            Scanner s = new Scanner(file);
            while (s.hasNext()) {
                String taskCode = s.nextLine();
                tasks.add(decodeTask(taskCode));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new DukeException("Unable initialise task list from file memory");
        }
    }

    /**
     * Instantiates a task list to store tasks.
     */
    public TaskList() {

    }

    /**
     * Determines the total number of tasks in the list.
     *
     * @return number of task in the task list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Interprets string form of task saved in memory and convert them to
     * Task objects.
     *
     * @param taskCode string representation of a task and its attributes in memory.
     * @return Task as an object with its respective attributes.
     */
    protected Task decodeTask(String taskCode) {
        String[] taskDetails = taskCode.split("\\|");
        String taskType = taskDetails[0];
        boolean done = taskDetails[1].equals("1");
        switch (taskType) {
        case "T":
            return new Todo(done, taskDetails[2]);
        case "E": {
            String info = taskDetails[3];
            return new Event(done, taskDetails[2], LocalDateTime.parse(info));
        }
        case "D": {
            String info = taskDetails[3];
            return new Deadline(done, taskDetails[2], LocalDateTime.parse(info));
        }
        default:
            return null;
        }
    }

    /**
     * Lists all task in the task list and number them from top to bottom.
     *
     * @return string of all tasks in the task list with a task number
     */
    public String list() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            result.append(String.format("%d. %s\n", i + 1, tasks.get(i).toString()));
        }
        return result.toString();
    }

    /**
     * Marks task as done.
     *
     * @param taskNumber first task has task number of 1.
     * @return task that was marked as done.
     */
    public Task markAsDone(int taskNumber) {
        Task task = tasks.get(taskNumber - 1);
        task.markAsDone();
        return task;
    }

    /**
     * Adds task to the task list.
     *
     * @param task task to be added to the list.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes task from the list.
     *
     * @param taskNumber first task has task number of 1.
     * @return task that was removed.
     */
    public Task deleteTask(int taskNumber) {
        return tasks.remove(taskNumber - 1);
    }

    /**
     * Encodes all tasks in the task list.
     *
     * @return string comprising of all tasks in its encoded form.
     */
    public String compileTasks() {
        StringBuilder result = new StringBuilder();
        for (Task task : tasks) {
            result.append(String.format("%s\n", task.encode()));
        }
        return result.toString();
    }

    public Task[] findTasksWithName(String searchTerm) {
        return tasks
                .stream()
                .parallel()
                .filter(task -> task.getTaskName().contains(searchTerm))
                .toArray(Task[]::new);
    }
}
