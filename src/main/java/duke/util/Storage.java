package duke.util;

import duke.task.TaskList;

import java.io.FileNotFoundException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * Storage class for storing the TaskList in taskList.txt
 */
public class Storage {
    private static final String CREATE_FILE_ERROR = "An error occurred. Unable to create taskList file.";
    private static final String FILE_NOT_FOUND_MESSAGE = "An error occurred. Unable to find file.";
    private static final String INVALID_TASKLIST_MESSAGE = "Error reading taskLst. TaskList is probably invalid.";

    private final File taskFile;
    private boolean didTaskFileExist = false;

    /**
     * Constructor for Storage
     */
    public Storage(Path filePath) {
        taskFile = new File(String.valueOf(filePath));
        createOuterDirectory(taskFile);
        try {
            if (!taskFile.createNewFile()) {
                this.didTaskFileExist = true;
            }
        } catch (IOException err) {
            System.out.println(CREATE_FILE_ERROR);
        }
    }

    /**
     * Returns whether the task file exists or not
     *
     * @return whether the task file exists or not
     */
    public boolean didTaskFileExist() {
        return this.didTaskFileExist;
    }

    /**
     * Copies the contents of the taskList into taskList.txt
     *
     * @param taskList the TaskList
     */
    public void updateTaskListToFile(TaskList taskList) {
        try {
            FileWriter fileWriter = new FileWriter(taskFile, false);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(taskList.getSaveFormat());
            bufferedWriter.flush();
        } catch (IOException err) {
            System.out.println("Error writing task to file.");
        }
    }

    /**
     * Copies taskList.txt into TaskList
     *
     * @param taskList the TaskList
     */
    public void readTaskFile(TaskList taskList) {
        try {
            Scanner taskScanner = new Scanner(this.taskFile);
            while (taskScanner.hasNextLine()) {
                String taskLine = taskScanner.nextLine();
                taskList.addSavedTask(taskLine);
            }
            taskScanner.close();
        } catch (FileNotFoundException err) {
            Ui.displayMessage(FILE_NOT_FOUND_MESSAGE);
        } catch (ArrayIndexOutOfBoundsException err) {
            Ui.displayMessage(INVALID_TASKLIST_MESSAGE);
        } catch (DukeException err) {
            Ui.displayMessage(err.getMessage());
        }
    }

    public String load() throws DukeException{
        StringBuilder taskList = new StringBuilder("");
        try {
            Scanner taskScanner = new Scanner(this.taskFile);
            while (taskScanner.hasNextLine()) {
                String taskLine = taskScanner.nextLine();
                taskList.append(taskLine).append("\n");
            }
            taskScanner.close();
            return taskList.toString();
        } catch (FileNotFoundException err) {
            throw new DukeException(FILE_NOT_FOUND_MESSAGE);
        }
    }

    /**
     * Creates the outer directory. In this case data
     */
    private static void createOuterDirectory(File taskFile) {
        taskFile.getParentFile().mkdirs();
    }
}
