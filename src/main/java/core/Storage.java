package core;

import java.io.File;

/**
 * Encapsulates a storage object that handles loading data from and updating data to a file.
 */
public class Storage {
    private File file;

    public Storage(String filePath) {
        file = new File("data/duke.txt");
        // Add way to automatically create folder and file if they do not exist
    }

    public void addTasksToFile(TaskList taskList) {
        taskList.saveContents(file);
    }

}
