package duke.data;

import java.util.ArrayList;

import duke.data.task.Task;
import duke.ui.Ui;

/**
 * Class that contains the tasklist and handles all tasklist operations.
 *
 * @author Won Ye Ji
 */
public class TaskHandler {
    private ArrayList<Task> storageList;
    private ArrayList<Task> archiveList;

    /**
     * Constructor for the TaskHandler class.
     *
     * @param storageList Tasklist.
     * @param archiveList Archives.
     */
    public TaskHandler(ArrayList<Task> storageList, ArrayList<Task> archiveList) {
        this.storageList = storageList;
        this.archiveList = archiveList;
    }

    /**
     * Returns the tasklist.
     *
     * @return the tasklist.
     */
    public ArrayList<Task> getList() {
        return storageList;
    }

    /**
     * Returns the String representation of the tasklist.
     *
     * @return String representation of the tasklist.
     */
    public String printList(String s) {
        ArrayList<Task> list;
        String toPrint;
        if (s == "storage") {
            list = storageList;
            toPrint = Ui.printList();
        } else {
            list = archiveList;
            toPrint = Ui.printArchives();
        }
        assert list != null && toPrint != null : "No such lists";
        if (list.size() != 0) {
            for (int i = 0; i < list.size(); i++) {
                toPrint = toPrint.concat(Ui.indentation() + (i + 1) + ". " + list.get(i).toString() + "\n");
            }
            return toPrint;
        } else {
            return Ui.printEmptyList();
        }
    }

    /**
     * Returns the number of tasks in the tasklist.
     *
     * @return Number of tasks in the tasklist.
     */
    public String printNoOfTasks() {
        return Ui.printNoOfTasks(storageList.size());
    }

    /**
     * Marks the task at the given task index as done and returns a message to the user.
     *
     * @param taskNo Task index of the task.
     * @return Duke's response to the user.
     */
    public String markTaskAsDone(int taskNo) {
        String toPrint = Ui.markAsDone();
        Task task = storageList.get(taskNo - 1);
        assert task.getStatusIcon() != "X" : "Completed task cannot be marked as done again.";
        task.markAsDone();
        toPrint = toPrint.concat(Ui.indentation() + task + "\n");
        return toPrint;
    }

    /**
     * Deletes the task at the given task index and returns a message to the user.
     *
     * @param taskNo Task index of the task.
     * @return Duke's response to the user.
     */
    public String deleteTask(int taskNo) {
        String toPrint = Ui.deleteTask();
        Task task = storageList.get(taskNo - 1);
        storageList.remove(taskNo - 1);
        toPrint = toPrint.concat(Ui.indentation() + task + "\n");
        return toPrint;
    }

    /**
     * Adds a task to the tasklist and returns a message to the user.
     *
     * @param task Task to be added.
     * @return Duke's response to the user.
     */
    public String addTask(Task task) {
        storageList.add(task);
        String toPrint = Ui.addTask();
        toPrint = toPrint.concat(Ui.indentation() + Ui.indentation() + task + "\n");
        return toPrint;
    }

    /**
     * Finds tasks with the keyword from tasklist and returns a message to the user.
     *
     * @param keyword Keyword user wants to find.
     * @return Duke's response to the user.
     */
    public String findTasks(String keyword) {
        ArrayList<String> tasks = new ArrayList<>();
        int j = 1;
        for (int i = 0; i < storageList.size(); i++) {
            if (storageList.get(i).toString().contains(keyword)) {
                tasks.add(Ui.indentation() + j + ". " + storageList.get(i).toString() + "\n");
                j++;
            }
        }
        if (tasks.size() == 0) {
            return Ui.noSuchTasksFound();
        } else {
            String toPrint = Ui.printFoundTasks();
            for (int k = 0; k < tasks.size(); k++) {
                toPrint = toPrint.concat(tasks.get(k));
            }
            return toPrint;
        }
    }

    /**
     * Archives selected task.
     * @param taskNo Index of selected task.
     * @return Duke's response to the user.
     */
    public String archiveTask(int taskNo) {
        Task task = storageList.get(taskNo - 1);
        storageList.remove(taskNo - 1);
        archiveList.add(task);
        String toPrint = Ui.archiveTask();
        toPrint = toPrint.concat(Ui.indentation() + Ui.indentation() + task + "\n");
        return toPrint;
    }

    /**
     * Formats the tasklist to be put into storage.
     *
     * @return A string of the formatted tasklist.
     */
    public String formatTasksToSave(String s) {
        ArrayList<Task> list;
        if (s == "storage") {
            list = storageList;
        } else {
            list = archiveList;
        }
        assert list != null : "No such lists";
        assert list.size() != 0 : "There are no tasks to save!";
        String[] tasksToSave = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            tasksToSave[i] = list.get(i).toSave();
        }
        return String.join("\n", tasksToSave);
    }
}
