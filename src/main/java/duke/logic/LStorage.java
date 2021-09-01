package duke.logic;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import duke.task.Task;
import duke.task.TaskList;

/**
 * Deals with the logic of writing to the dukedata.txt file each time there is an update to the task list.
 */
public class LStorage {
    private final String filePath;
    private final TaskList taskList;
    private final File textFile;

    /**
     * Creates a new instance of a storage object that deals with saving the task list in Duke.
     *
     * @param filePath The string representing the path of the file that the data will be saved into.
     * @param taskList The list of task that will be saved into the file.
     */
    public LStorage(String filePath, TaskList taskList) throws IOException {
        this.filePath = filePath;
        this.taskList = taskList;
        this.textFile = new File(filePath);
        if (!this.textFile.canWrite()) {
            throw new IOException("This file cannot be written by duke!");
        }
    }

    /**
     * Updates the text file with the latest task list.
     *
     * <p>Currently the text file is overwritten each time it updates.
     * A more efficient method should be implemented.</p>
     */
    public void updateDukeTextFile() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(textFile));
            writer.write(""); // Overwrites everything
            for (Task task : taskList.getTasks()) {
                writer.append(task.getDataLine()).append("\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.printf("However an error occurred while writing to %s:\n", textFile.getAbsolutePath());
            e.printStackTrace();
        }
    }
}
