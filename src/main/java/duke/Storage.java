package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Encapsulate storage-related functionality of Iris
 */
public class Storage {
    private final String taskFilePath;

    /**
     * Create a new Storage object
     * @param taskFilePath Path to file containing tasks
     */
    public Storage(String taskFilePath) {
        this.taskFilePath = taskFilePath;
    }

    private void createTaskFile() {
        try {
            File taskFile = new File(taskFilePath);
            taskFile.getParentFile().mkdirs();
            taskFile.createNewFile();
        } catch (IOException exception) {
            // TODO: handle IOException?
        }
    }

    /**
     * Read tasks from task file
     * @param taskList TaskList object to update with read tasks
     * @param ui       Ui object for current Iris instance
     * @throws IrisException for invalid tasks.txt
     */
    public void readTasks(TaskList taskList, Ui ui) throws IrisException {
        File taskFile = new File(taskFilePath);
        try {
            Scanner scanner = new Scanner(taskFile);
            while (scanner.hasNextLine()) {
                Parser.handleCommand(scanner.nextLine(), taskList, ui, true);
            }
        } catch (FileNotFoundException exception) {
            createTaskFile();
        } catch (IrisException exception) {
            throw new Error("tasks.txt has been corrupted");
        }
    }

    /**
     * Write tasks to task file
     * @param taskList TaskList object representing current list of tasks
     */
    public void writeTasks(TaskList taskList) {
        try {
            FileWriter fw = new FileWriter(taskFilePath);
            String[] commands = taskList.toCommands();
            for (String command : commands) {
                fw.write(command);
            }
            fw.close();
        } catch (IOException exception) {
            // TODO: handle IOException?
        }
    }
}
