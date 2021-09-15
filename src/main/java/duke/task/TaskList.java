package duke.task;

import duke.exception.DukeException;

import duke.util.Storage;
import duke.util.Ui;

import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;

/**
 * Encapsulates task list and its operations.
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private Ui ui = new Ui();

    /**
     * Constructor for loading old task list.
     *
     * @param tasks List of tasks entered previously.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructor for new task list.
     */
    public TaskList() {
        this(new ArrayList<>());
    }

    /**
     * Returns number of tasks in list.
     *
     * @return Length of list.
     */
    public int getListLength() {
        return tasks.size();
    }

    /**
     * Returns string showing task added.
     *
     * @param task Task to be added to list.
     * @return String representation of task added.
     */
    public String addTaskToList(Task task) {
        tasks.add(task);
        return ui.showTaskAdded(task, tasks.size());
    }

    /**
     * Returns string of tasks in list.
     *
     * @return String representation of list.
     */
    public String printTasksInList() {
        return ui.showTasks(tasks);
    }

    /**
     * Returns tasks in list that match keyword.
     *
     * @param keyword Keyword to match in task list.
     * @return String representation of matching tasks.
     */
    public String findMatchingTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<Task>();
        for (Task task : tasks) {
            if (task.hasKeyword(keyword)) {
                matchingTasks.add(task);
            }
        }

        TaskList matchingList = new TaskList(matchingTasks);
        return ui.showMatchingListMessage() + matchingList.printTasksInList();
    }

    /**
     * Returns string showing task deleted.
     *
     * @param deleteNumber Index of task in list.
     * @return String representation of task deleted.
     */
    public String deleteFromList(int deleteNumber) {
        assert deleteNumber >= 0 && deleteNumber < tasks.size();
        Task task = tasks.get(deleteNumber);
        tasks.remove(deleteNumber);
        return ui.showTaskDeleted(task, tasks.size());
    }


    /**
     * Returns string of task set as done.
     *
     * @param doneNumber Index of task in list.
     * @return String representation of task set as done.
     */
    public String setTaskAsDone(int doneNumber) {
        assert doneNumber >= 0 && doneNumber < tasks.size();
        Task task = tasks.get(doneNumber);
        task.setDone();
        return ui.showTaskDone(task);
    }

    public void archiveAllTasks() throws DukeException {
        String ARCHIVE_FILE_PATH = "archive.txt";
        Storage archiveStorage = new Storage(ARCHIVE_FILE_PATH);
        archiveStorage.save(this, "archive");
        this.tasks = new ArrayList<>();
    }

    /**
     * Saves tasks in list to hard disk.
     *
     * @param writer FileWriter that write tasks to hard disk.
     * @throws IOException If file is not found.
     */
    public void saveTasksInStorage(FileWriter writer) throws IOException {
        for (Task tasks : tasks) {
            writer.write(tasks.saveTaskFormat() + System.lineSeparator());
        }
    }

}
