package duke.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

import duke.task.TaskList;

/**
 * Storage class for storing the TaskList in taskList.txt.
 */
public class Storage {
    private static final String CREATE_FILE_ERROR = "An error occurred. Unable to create taskList file.";
    private static final String FILE_NOT_FOUND_MESSAGE = "An error occurred. Unable to find file.";
    private static final String INVALID_TASKLIST_MESSAGE = "Error reading taskLst. TaskList is probably invalid.";

    private File taskFile;

    /**
     * Constructor for Storage.
     */
    public Storage(Path filePath) {
        taskFile = new File(String.valueOf(filePath));
        createOuterDirectory(taskFile);
        try {
            taskFile.createNewFile();
        } catch (IOException err) {
            System.out.println(CREATE_FILE_ERROR);
        }
    }

    /**
     * Copies the contents of the taskList into taskList.txt.
     *
     * @param taskList the TaskList.
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
     * Copies taskList.txt into TaskList.
     *
     * @param taskList the TaskList.
     * @throws DukeException The exception to be thrown.
     */
    public void readTaskFile(TaskList taskList) throws DukeException {
        try {
            Scanner taskScanner = new Scanner(this.taskFile);
            while (taskScanner.hasNextLine()) {
                String taskLine = taskScanner.nextLine();
                taskList.addSavedTask(taskLine);
            }
            taskScanner.close();
        } catch (FileNotFoundException err) {
            throw new DukeException(FILE_NOT_FOUND_MESSAGE);
        } catch (ArrayIndexOutOfBoundsException err) {
            throw new DukeException(INVALID_TASKLIST_MESSAGE);
        }
    }

    /**
     * Outputs a string representing the tasks in taskList.txt.
     *
     * @return A string representing the tasks in taskList.txt.
     * @throws DukeException Exception to be handled.
     */
    public String load() throws DukeException {
        StringBuilder taskList = new StringBuilder();
        try {
            Scanner taskScanner = new Scanner(this.taskFile);
            while (taskScanner.hasNextLine()) {
                String taskLine = taskScanner.nextLine();
                taskList.append(taskLine).append("\n");
            }
            taskScanner.close();
        } catch (FileNotFoundException err) {
            throw new DukeException(FILE_NOT_FOUND_MESSAGE);
        }
        return taskList.toString();
    }

    /**
     * Creates the outer directory. In this case the directory data.
     */
    private static void createOuterDirectory(File taskFile) {
        taskFile.getParentFile().mkdirs();
    }
}
