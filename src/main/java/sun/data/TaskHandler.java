package sun.data;

import java.util.ArrayList;

import sun.data.task.Task;
import sun.ui.Ui;

/**
 * Class that contains the task list and handles all task list operations.
 *
 * @author Won Ye Ji
 */
public class TaskHandler {
    private ArrayList<Task> storageList;
    private ArrayList<Task> archiveList;

    /**
     * Constructor for the TaskHandler class.
     *
     * @param storageList Task list.
     * @param archiveList Archives.
     */
    public TaskHandler(ArrayList<Task> storageList, ArrayList<Task> archiveList) {
        this.storageList = storageList;
        this.archiveList = archiveList;
    }

    /**
     * Returns the task list.
     *
     * @return the task list.
     */
    public ArrayList<Task> getList() {
        return storageList;
    }

    /**
     * Returns the String representation of the task list.
     *
     * @return String representation of the task list.
     */
    public String printList(String s) {
        ArrayList<Task> list;
        String toPrint;
        if (s == "storage") {
            list = storageList;
            toPrint = Ui.getPrintListMessage();
        } else {
            list = archiveList;
            toPrint = Ui.getPrintArchivesMessage();
        }
        assert list != null && toPrint != null : "No such lists";
        if (list.size() != 0) {
            for (int i = 0; i < list.size(); i++) {
                toPrint = toPrint.concat(Ui.getIndentation() + (i + 1) + ". " + list.get(i).toString() + "\n");
            }
            return toPrint;
        } else {
            return Ui.getPrintEmptyListMessage();
        }
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return Number of tasks in the task list.
     */
    public String printNoOfTasks() {
        return Ui.getPrintNoOfTasksMessage(storageList.size());
    }

    /**
     * Marks the task at the given task index as done and returns a message to the user.
     *
     * @param taskNo Task index of the task.
     * @return Sun's response to the user.
     */
    public String markTaskAsDone(int taskNo) {
        String toPrint = Ui.getMarkAsDoneMessage();
        Task task = storageList.get(taskNo - 1);
        assert task.getStatusIcon() != "X" : "Completed task cannot be marked as done again.";
        task.markAsDone();
        toPrint = toPrint.concat(Ui.getIndentation() + task + "\n");
        return toPrint;
    }

    /**
     * Deletes the task at the given task index and returns a message to the user.
     *
     * @param taskNo Task index of the task.
     * @return Sun's response to the user.
     */
    public String deleteTask(int taskNo) {
        String toPrint = Ui.getDeleteTaskMessage();
        Task task = storageList.get(taskNo - 1);
        storageList.remove(taskNo - 1);
        toPrint = toPrint.concat(Ui.getIndentation() + task + "\n");
        return toPrint;
    }

    /**
     * Adds a task to the task list and returns a message to the user.
     *
     * @param task Task to be added.
     * @return Sun's response to the user.
     */
    public String addTask(Task task) {
        storageList.add(task);
        String toPrint = Ui.getAddTaskMessage();
        toPrint = toPrint.concat(Ui.getIndentation() + Ui.getIndentation() + task + "\n");
        return toPrint;
    }

    /**
     * Finds tasks with the keyword from task list and returns a message to the user.
     *
     * @param keyword Keyword user wants to find.
     * @return Sun's response to the user.
     */
    public String findTasks(String keyword) {
        ArrayList<String> tasks = new ArrayList<>();
        int j = 1;
        for (Task task : storageList) {
            if (task.getDescription().contains(keyword)) {
                tasks.add(Ui.getIndentation() + j + ". " + task + "\n");
                j++;
            }
        }
        if (tasks.size() == 0) {
            return Ui.getNoSuchTasksFoundMessage();
        } else {
            String toPrint = Ui.getPrintFoundTasksMessage();
            for (String task : tasks) {
                toPrint = toPrint.concat(task);
            }
            return toPrint;
        }
    }

    /**
     * Archives selected task.
     * @param taskNo Index of selected task.
     * @return Sun's response to the user.
     */
    public String archiveTask(int taskNo) {
        Task task = storageList.get(taskNo - 1);
        storageList.remove(taskNo - 1);
        archiveList.add(task);
        String toPrint = Ui.getArchiveTaskMessage();
        toPrint = toPrint.concat(Ui.getIndentation() + Ui.getIndentation() + task + "\n");
        return toPrint;
    }

    /**
     * Formats the task list to be put into storage.
     *
     * @return A string of the formatted task list.
     */
    public String formatTasksToSave(String s) {
        ArrayList<Task> list;
        if (s == "storage") {
            list = storageList;
        } else {
            list = archiveList;
        }
        assert list != null : "No such lists";
        String[] tasksToSave = new String[list.size()];
        if (list.size() == 0) {
            return "";
        } else {
            for (int i = 0; i < list.size(); i++) {
                tasksToSave[i] = list.get(i).toSave();
            }
        }
        return String.join("\n", tasksToSave);
    }
}
