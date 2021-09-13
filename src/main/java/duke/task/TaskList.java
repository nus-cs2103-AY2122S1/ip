package duke.task;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Scanner;

import duke.util.DukeException;

/**
 * Manipulates and stores tasks.
 */
public class TaskList {
    private static final int TASK_TYPE = 0;
    private static final int TASK_DONE = 1;
    private static final int TASK_NAME = 2;
    private static final int TASK_DateTime = 3;

    private final LinkedList<Task> tasks = new LinkedList<>();

    /**
     * Instantiates a task list to store tasks.
     */
    public TaskList() {

    }

    /**
     * Retrieves value from a file on local storage to initialise a copy
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
        String taskType = taskDetails[TASK_TYPE];
        String dateTimeInfo;
        boolean done = taskDetails[TASK_DONE].equals("1");
        switch (taskType) {
        case "T":
            return new Todo(done, taskDetails[TASK_NAME]);
        case "E":
            dateTimeInfo = taskDetails[TASK_DateTime];
            return new Event(done, taskDetails[TASK_NAME], LocalDateTime.parse(dateTimeInfo));
        case "D":
            dateTimeInfo = taskDetails[TASK_DateTime];
            return new Deadline(done, taskDetails[TASK_NAME], LocalDateTime.parse(dateTimeInfo));
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

    /**
     * Filters tasks in the task list with name that includes the search term.
     *
     * @param searchTerm search term that user wants to extract.
     * @return Task array comprising of all tasks with search term in task name.
     */
    public Task[] findTasksWithName(String searchTerm) {
        return tasks
                .stream()
                .parallel()
                .filter(task -> task.getTaskName().contains(searchTerm))
                .toArray(Task[]::new);
    }

    /**
     * Updates the task name of a task.
     *
     * @param taskNumber identifies the task to be updated.
     * @param newName new task name for identified task.
     * @return task with name updated.
     */
    public Task updateTaskName(int taskNumber, String newName) {
        Task task = tasks.get(taskNumber - 1);
        task.setName(newName);
        return task;
    }

    /**
     * Updates datetime of task if applicable
     *
     * @param taskNumber identifies task to be updated.
     * @param dateTime new datetime for identified task.
     * @return task with datetime updated.
     * @throws DukeException if task does not have a datetime attribute.
     */
    public Task updateTaskDateTime(int taskNumber, LocalDateTime dateTime) throws DukeException {
        Task task = tasks.get(taskNumber - 1);
        task.setDateTime(dateTime);
        return task;
    }
}
