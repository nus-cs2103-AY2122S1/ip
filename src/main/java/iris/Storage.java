package iris;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import iris.command.Command;

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

    private void createTaskFile() throws IrisException {
        try {
            File taskFile = new File(taskFilePath);
            taskFile.getParentFile().mkdirs();
            taskFile.createNewFile();
        } catch (IOException exception) {
            throw new IrisException("IOException: " + exception.getMessage());
        }
    }

    /**
     * Reads tasks from task file
     *
     * @param tasks TaskList object to update with read tasks
     * @throws IrisException for invalid tasks.txt
     */
    public void readTasks(TaskList tasks) throws IrisException {
        File taskFile = new File(taskFilePath);
        try {
            Scanner scanner = new Scanner(taskFile);
            while (scanner.hasNextLine()) {
                String rawCommand = scanner.nextLine();
                Command command = Parser.parse(rawCommand);
                command.run(tasks);
            }
        } catch (FileNotFoundException exception) {
            createTaskFile();
        } catch (IrisException exception) {
            throw new IrisException("tasks.txt has been corrupted");
        }
    }

    /**
     * Writes tasks to task file
     *
     * @param tasks TaskList object representing current list of tasks
     */
    public void writeTasks(TaskList tasks) throws IrisException {
        try {
            FileWriter fw = new FileWriter(taskFilePath);
            String[] commands = tasks.toCommands();
            for (String command : commands) {
                fw.write(command);
            }
            fw.close();
        } catch (IOException exception) {
            throw new IrisException("IOException: " + exception.getMessage());
        }
    }
}
