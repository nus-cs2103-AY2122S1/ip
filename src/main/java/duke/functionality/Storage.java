package duke.functionality;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.Todo;

/**
 * Represents a storage system that deals with loading tasks from and saving tasks to a file.
 */
public class Storage {
    private static final String FILE_NAME = "dukestorage.txt";
    private static final String ARCHIVE_FILE_NAME = "dukearchive.txt";
    private String filePath;
    private String archiveFilePath;
    private File f;
    private File archiveF;
    private TaskList taskList;
    private TaskList archiveTaskList;

    /**
     * Returns a storage system that is loaded with pre-saved tasks and archived tasks, if any, and allows for the
     * storage of more tasks.
     *
     * @param filePath The filepath in which to place the files containing saved data.
     */
    public Storage(String filePath) {
        try {
            this.filePath = filePath + "\\" + FILE_NAME;
            loadStorage();

            this.archiveFilePath = filePath + "\\" + ARCHIVE_FILE_NAME;
            loadArchive();

        } catch (IOException e) {
            System.err.println("Error in storage creation!");
        }
    }

    private void loadStorage() throws IOException {
        this.f = new File(this.filePath);
        this.taskList = new TaskList();
        if (f.exists()) {
            FileReader fr = new FileReader(this.filePath);
            BufferedReader br = new BufferedReader(fr);
            String content;
            while ((content = br.readLine()) != null) {
                taskAdder(content, this.taskList);
            }
            this.f.delete();
        }
        this.f.createNewFile();

        assert(this.taskList.taskListLen() >= 0) : "taskList has not been created properly.";
    }

    private void loadArchive() throws IOException {
        this.archiveF = new File(this.archiveFilePath);
        this.archiveTaskList = new TaskList();
        if (archiveF.exists()) {
            FileReader fr = new FileReader(this.archiveFilePath);
            BufferedReader br = new BufferedReader(fr);
            String content;
            while ((content = br.readLine()) != null) {
                taskAdder(content, this.archiveTaskList);
            }
            this.archiveF.delete();
        }
        this.archiveF.createNewFile();

        assert(this.archiveTaskList.taskListLen() >= 0) : "archiveTaskList has not been created properly.";
    }

    private void taskAdder(String content, TaskList taskList) {
        String[] contentArr = content.split(" \\| ");
        String taskType = contentArr[0];
        boolean isDone = contentArr[1].equals("1");
        switch (taskType) {
        case "T":
            taskList.addTask(new Todo(contentArr[2], isDone));
            break;
        case "D":
            taskList.addTask(new Deadline(contentArr[2], contentArr[3], isDone));
            break;
        case "E":
            taskList.addTask((new Event(contentArr[2], contentArr[3], isDone)));
            break;
        default:
            break;
        }
    }

    /**
     * Adds a task to the TaskList within the storage object.
     *
     * @param taskName Name of the task to be inserted.
     * @return True if the task has been added successfully, false otherwise.
     */
    public boolean addTask(Task taskName) {
        return this.taskList.addTask(taskName);
    }

    /**
     * Deletes a task from the TaskList within the storage object.
     *
     * @param taskNum Index of the task to be deleted.
     * @return Deleted task.
     */
    public Task deleteTask(int taskNum) {
        return this.taskList.deleteTask(taskNum);
    }

    /**
     * Adds a task to the archive TaskList within the storage object.
     *
     * @param taskName Name of the task to be inserted.
     * @return True if the task has been added successfully, false otherwise.
     */
    public boolean archiveTask(Task taskName) {
        return this.archiveTaskList.addTask(taskName);
    }

    /**
     * Deletes a task from the archiveTaskList within the storage object.
     *
     * @param taskNum Index of the task to be deleted.
     * @return Deleted task.
     */
    public Task deleteArchivedTask(int taskNum) {
        return this.archiveTaskList.deleteTask(taskNum);
    }


    /**
     * Returns length of the TaskList within the storage object.
     *
     * @return Length of TaskList.
     */
    public int taskListLen() {
        return this.taskList.taskListLen();
    }

    /**
     * Returns length of the archive TaskList within the storage object.
     *
     * @return Length of archiveTaskList.
     */
    public int archiveListLen() {
        return this.archiveTaskList.taskListLen();
    }

    public Task getTask(int taskIndex) {
        return this.taskList.getTask(taskIndex);
    }

    public TaskList getTaskList() {
        return this.taskList;
    }

    /**
     * Returns the string representation of the TaskList within the storage object.
     *
     * @return String representation of TaskList.
     */
    public String printTaskList() {
        return this.taskList.toString();
    }

    /**
     * Returns the string representation of the archiveTaskList within the storage object.
     *
     * @return String representation of archiveTaskList.
     */
    public String printArchiveTaskList() {
        return this.archiveTaskList.toString();
    }

    /**
     * Saves all contents of the current TaskList into a file on the system.
     */
    public void saveToFile() {
        try {
            FileWriter fw = new FileWriter(this.filePath);
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i < this.taskList.taskListLen(); i++) {
                bw.write(this.taskList.getTask(i).fileSaveFormat() + "\n");
            }
            bw.close();
        } catch (IOException e) {
            Ui.showErrorMessage("Error in saving file!");
        }
    }

    /**
     * Saves all contents of the current archiveTaskList into an archive file on the system.
     */
    public void saveToArchive() {
        try {
            FileWriter fw = new FileWriter(this.archiveFilePath);
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i < this.archiveTaskList.taskListLen(); i++) {
                bw.write(this.archiveTaskList.getTask(i).fileSaveFormat() + "\n");
            }
            bw.close();
        } catch (IOException e) {
            Ui.showErrorMessage("Error in saving file!");
        }
    }

}
