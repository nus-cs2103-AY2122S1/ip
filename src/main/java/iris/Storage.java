package iris;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Encapsulates storage-related functionality of Iris
 */
public class Storage {
    private final String taskFilePath;

    /**
     * Creates a new Storage object
     *
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
     * Reads tasks from task file
     *
     * @param tasks TaskList object to update with read tasks
     * @param ui       Ui object for current Iris instance
     * @throws IrisException for invalid tasks.txt
     */
    public void readTasks(TaskList tasks, Ui ui) throws IrisException {
        File taskFile = new File(taskFilePath);
        try {
            Scanner scanner = new Scanner(taskFile);
            while (scanner.hasNextLine()) {
                Parser.handleCommand(scanner.nextLine(), tasks, ui, true);
            }
        } catch (FileNotFoundException exception) {
            createTaskFile();
        } catch (IrisException exception) {
            throw new Error("tasks.txt has been corrupted");
        }
    }

    /**
     * Writes tasks to task file
     *
     * @param tasks TaskList object representing current list of tasks
     */
    public void writeTasks(TaskList tasks) {
        try {
            FileWriter fw = new FileWriter(taskFilePath);
            String[] commands = tasks.toCommands();
            for (String command : commands) {
                fw.write(command);
            }
            fw.close();
        } catch (IOException exception) {
            // TODO: handle IOException?
        }
    }
}
