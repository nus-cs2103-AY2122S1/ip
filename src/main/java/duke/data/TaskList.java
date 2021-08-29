package duke.data;

import duke.storage.Storage;
import duke.ui.Ui;
import duke.data.task.Task;
import duke.data.exception.DukeException;

import java.util.ArrayList;

/**
 * Class that contains the tasklist and handles all tasklist operations.
 *
 * @author Wang Hong Yong
 */
public class TaskList {
    private ArrayList<Task> list;
    private final Storage storage;

    /**
     * Constructor for the TaskList class.
     *
     * @param list Tasklist.
     * @param storage storage for duke.
     */
    public TaskList(ArrayList<Task> list, Storage storage) {
        this.list = list;
        this.storage = storage;
    }

    /**
     * Adds a task to the tasklist.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        list.add(task);
        Ui.printAddMsg(task.toString(), getTotalTasks());
        this.writeFile(task);
    }

    /**
     * Deletes the task at the given task index.
     *
     * @param taskNum Task index of the task.
     */
    public void removeTask(int taskNum) throws DukeException {
        if (taskNum < 1) {
            throw new DukeException("negative item");
        } else if (list.size() == 0) {
            throw new DukeException("no task found");
        } else if (taskNum > list.size()) {
            throw new DukeException("no such task");
        }

        Task item = list.remove(taskNum - 1);
        Ui.printRemoveMsg(item.toString(), getTotalTasks());
        this.updateFile();
    }

    /**
     * Marks the task at the given task index as done.
     *
     * @param taskNum Task index of the task.
     */
    public void markTaskAsDone(int taskNum) throws DukeException {
        if (taskNum < 1) {
            throw new DukeException("negative item");
        } else if (list.size() == 0) {
            throw new DukeException("no task found");
        } else if (taskNum > list.size()) {
            throw new DukeException("no such task");
        }

        Task item = list.get(taskNum - 1);
        item.markAsDone();
        this.updateFile();
        Ui.printTaskDone(item.toString());
    }

    /**
     * Returns number of undone task.
     *
     * @return A int representing total undone task.
     */
    private int getUndoneTasks() {
        int count = 0;
        for (Task task : list) {
            if (!task.isDone()) {
                count++;
            }
        }
        return count;
    }

    /**
     * Returns number of total task.
     *
     * @return A int representing total task.
     */
    private int getTotalTasks() {
        return list.size();
    }

    public void findTask(String wordToFind) {
        ArrayList<String> tasks = new ArrayList<>();
        int index = 1;
        for (Task t: list) {
            if (t.toString().contains(wordToFind)) {
                tasks.add(String.format("    %d.%s", (index), t.toString()) + System.lineSeparator());
                index++;
            }
        }
        if (tasks.size() == 0) {
            Ui.printNoSuchTaskMsg();
        } else {
            String msg = "     Here are the matching tasks in your list:" + System.lineSeparator();
            for (int i = 0; i < tasks.size(); i++) {
                msg += String.format("    %d.%s", (i + 1), tasks.get(i).toString()) + System.lineSeparator();
            }
            Ui.prettify(msg);
        }
    }





    public void listTasks() throws DukeException {
        String msg = "    Here are the tasks in your list:" + System.lineSeparator();
        for (int i = 0; i < list.size(); i++) {
            msg += String.format("    %d.%s", (i + 1), list.get(i).toString()) + System.lineSeparator();
        }
        Ui.prettify(msg);
    }

    public void updateFile() throws DukeException {
        storage.updateFile(list);
    }

    public void writeFile(Task t) throws DukeException {
        storage.writeFile(t);
    }
}
