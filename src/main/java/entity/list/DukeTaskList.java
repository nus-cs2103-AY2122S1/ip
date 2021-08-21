package entity.list;

import entity.data.DukeFile;
import exception.ErrorAccessingFile;
import exception.NonExistentTaskNumberException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Encapsulates a task list storing the users input tasks.
 */
public class DukeTaskList {
    private ArrayList<DukeTask> list = new ArrayList<>();
    private DukeFile listFile;

    public DukeTaskList(DukeFile listFile) {
        this.listFile = listFile;
    }

    public void scanExistingTaskToList(DukeTask task) {
        this.list.add(task);
    }

    /**
     * Adds task to a list.
     *
     * @param task is the task be added to the list
     */
    public void addTaskToList(DukeTask task) throws ErrorAccessingFile {
        try {
            this.listFile.add(task.toString());
            this.list.add(task);
        } catch(IOException e) {
            throw new ErrorAccessingFile("add");
        }
    }

    /**
     * Delete task from a list.
     *
     * @param taskNumber is the number of the task to be removed from the list
     */
    public DukeTask deleteTaskFromList(int taskNumber) throws NonExistentTaskNumberException, ErrorAccessingFile {
        try {
            validateTaskNumberExists(taskNumber);

            DukeTask task = this.getTaskByTaskNumber(taskNumber);
            this.list.remove(taskNumber - 1);
            this.listFile.rewriteFileWith(this.list);

            return task;
        } catch(IOException e) {
            throw new ErrorAccessingFile("delete");
        }
    }

    public DukeTask markTaskAsDone(int taskNumber) throws NonExistentTaskNumberException, ErrorAccessingFile {
        try {
            validateTaskNumberExists(taskNumber);

            DukeTask task = this.getTaskByTaskNumber(taskNumber);
            task.markAsDone();
            this.listFile.rewriteFileWith(this.list);

            return task;
        } catch (IOException e) {
            throw new ErrorAccessingFile("mark as done");
        }

    }

    /**
     * Formats tasks in a numbered list form, starting from 1.
     *
     * @return the numbered list
     */
    @Override
    public String toString() {
        StringBuilder stringBuilderList = new StringBuilder();

        for (int i = 0; i < this.list.size(); i++) {
            DukeTask task = this.list.get(i);
            String listItem = String.format("%d. %s\n\t", i + 1, task.toString());
            stringBuilderList.append(listItem);
        }

        return stringBuilderList.toString();
    }

    private boolean contains(int taskNumber) {
        return taskNumber > 0 && taskNumber <= this.list.size();
    }

    private DukeTask getTaskByTaskNumber(int taskNumber) {
        return this.list.get(taskNumber - 1);
    }

    /**
     * Gets the number of tasks in the list
     *
     * @return the number of tasks in the list
     */
    public int getNumberOfTasks() {
        return this.list.size();
    }

    private void validateTaskNumberExists(int taskNumber) throws NonExistentTaskNumberException {
        if (!this.contains(taskNumber)) {
            throw new NonExistentTaskNumberException(taskNumber);
        }
    }
}
