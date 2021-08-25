package duke.functionality;

import duke.exceptions.DukeException;
import duke.tasks.TaskList;
import duke.tasks.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Represents a storage system that deals with loading tasks from and saving tasks to a file.
 */
public class Storage {
    private final static String FILE_NAME = "dukestorage.txt";
    private String filePath;
    private File f;
    private TaskList taskList;

    public Storage(String filePath) {
        try {
            this.taskList = new TaskList();
            this.filePath = filePath + "\\" + FILE_NAME;
            this.f = new File(this.filePath);
            this.taskList = new TaskList();
            if (f.exists()) {
                FileReader fr = new FileReader(this.filePath);
                BufferedReader br = new BufferedReader(fr);
                String content;
                while ((content = br.readLine()) != null) {
                    taskAdder(content);
                }
                f.delete();
            }
            f.createNewFile();

        } catch (IOException e) {
            Ui.showErrorMessage("Error in storage creation!");
        }
    }

    private void taskAdder(String content) {
        String[] contentArr = content.split(" \\| ");
        String taskType = contentArr[0];
        boolean isDone = contentArr[1].equals("1");
        switch (taskType) {
        case "T":
            this.taskList.addTask(new Todo(contentArr[2], isDone));
            break;
        case "D":
            this.taskList.addTask(new Deadline(contentArr[2], contentArr[3], isDone));
            break;
        case "E":
            this.taskList.addTask((new Event(contentArr[2], contentArr[3], isDone)));
            break;
        default:
            break;
        }
    }

    /**
     * Adds a task to the TaskList within the storage object.
     *
     * @param taskName Name of the task to be inserted.
     */
    public void addTask(Task taskName) {
        this.taskList.addTask(taskName);
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
     * Returns length of the TaskList within the storage object.
     *
     * @return Length of TaskList.
     */
    public int taskListLen() {
        return this.taskList.taskListLen();
    }

    public Task getTask(int taskIndex) {
        return this.taskList.getTask(taskIndex);
    }

    public TaskList getTaskList() { return this.taskList; }

    /**
     * Returns the string representation of the TaskList within the storage object.
     *
     * @return String representation of TaskList.
     */
    public String printTaskList() {
        return this.taskList.toString();
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

}
