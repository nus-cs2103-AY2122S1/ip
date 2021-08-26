package duke.storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import duke.exception.DukeException;
import duke.parser.TaskParser;
import duke.task.Task;
import duke.task.TaskManager;
/**
 * Represents the file used to store the user's task data.
 */
public class Storage {
    private final String filePath;
    private final File file;

    /**
     * Constructor for a Storage object.
     * @param filePath path to the stored file
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        file = new File(filePath);
        if (!file.isFile()) {
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.printf("An error occurred when trying to access file %s."
                        + "Changes to your task list will not be saved locally.\n", filePath);
            }
        }
    }

    /**
     * Loads the user's tasks from file.
     * @return the list of tasks that were successfully loaded
     * @throws DukeException if an <code>IOException</code> occurred while trying to read the file
     */
    public List<Task> loadTasks() throws DukeException {
        List<Task> taskList = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String nextLine = bufferedReader.readLine();
            while (nextLine != null) {
                try {
                    taskList.add(TaskParser.parse(nextLine));
                } catch (DukeException e) {
                    System.out.printf("Bad task format found in %s: %s\nSkipping over...\n", filePath, e.getMessage());
                }
                nextLine = bufferedReader.readLine();
            }
            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
            throw new DukeException(String.format("An error occurred when trying to load %s:\n\t%s\n",
                    filePath, e.getMessage()));
        }
        return taskList;
    }

    /**
     * Saves the tasks into the file.
     * @param tasks tasks to be saved into the file
     */
    public void saveTasks(TaskManager tasks) {
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(tasks.toText());
            fileWriter.close();
        } catch (IOException e) {
            System.out.printf("An error occurred when trying to save tasks locally:\n\t%s\n", e.getMessage());
        }
    }
}
