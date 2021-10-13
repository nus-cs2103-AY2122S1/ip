package duke;

import java.util.List;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Represents the list of strings Duke uses internally to make changes to the list file.
 */
public class TaskListInternal {
    public static List<String> lines;
    private Ui ui = new Ui();

    public TaskListInternal() {
    }

    /**
     * Initialises the list and loads the existing contents of the task list file,
     * with each line at a separate index.
     *
     * @param file File representing the list file.
     * @return True if initialisation was successful, false if the list file is missing.
     */
    public boolean initialise(File file, Storage storage) {
        try {
            storage.listFileContents(file.getPath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Marks a task as done in the internal task list and the task list file.
     *
     * @param storage Storage object used by Duke to assist with file handling.
     * @taskNo Index of the task that is being marked as done in the list.
     */
    boolean makeDone(Storage storage, int taskNo) {
        String toBeDone = lines.get(taskNo);
        if (toBeDone.contains("[ ]")) {
            toBeDone = toBeDone.substring(0, 4) + "X" + toBeDone.substring(5);
            lines.set(taskNo, toBeDone);
            try {
                storage.writeListToFile(Duke.file.getPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
            ui.showMarkedAsDone(toBeDone);
            return true;
        } else {
            ui.showAlreadyDone();
            return false;
        }
    }

    /**
     * Deletes a task from the internal task list and the task list file.
     *
     * @param storage Storage object used by Duke to assist with file handling.
     * @param taskNo  Index of the task that is being deleted in the list.
     */
    void delete(Storage storage, int taskNo) {
        String toBeDeleted = TaskListInternal.lines.get(taskNo);
        TaskListInternal.lines.remove(taskNo);
        try {
            storage.writeListToFile(Duke.file.getPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        ui.showDeletionMsg(toBeDeleted, TaskListInternal.lines.size());
    }

    /**
     * Adds a task to the internal task list and the task list file.
     *
     * @param storage   Storage object used by Duke to assist with file handling.
     * @param toBeAdded Task that is being added.
     */
    boolean add(Storage storage, String toBeAdded) {
        if (!TaskListInternal.lines.contains(toBeAdded)) {
            TaskListInternal.lines.add(toBeAdded);
            ui.showTaskAdded(toBeAdded);
            ui.showListSize(TaskListInternal.lines.size());
            return true;
        } else {
            ui.showAlreadyInList(toBeAdded);
            return false;
        }
    }
}
