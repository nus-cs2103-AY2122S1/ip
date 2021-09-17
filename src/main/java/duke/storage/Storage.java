package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import duke.task.TaskList;

/**
 * Responsible for loading and saving user's tasks on disk.
 */
public class Storage {
    private final static String DIR_PATH = "data";
    private final static String FILE_NAME = "tasks.txt";

    /**
     * Returns file object which contains the tasks stored on disk.
     *
     * @return file object which contains tasks stored on disk.
     */
    public File load() {
        File file = new File(DIR_PATH + "/" + FILE_NAME);
        return file;
    }

    /**
     * Saves users tasks in a file on disk.
     *
     * @param tasks List of users tasks.
     * @throws StorageException If unable to write tasks to a file on disk.
     */
    public void saveTasks(TaskList tasks) throws StorageException {
        assert tasks != null : "Task list should not be null in order to save tasks on disk";

        try {
            File base_dir = new File(DIR_PATH);
            if (!base_dir.exists()) {
                base_dir.mkdirs();
            }
            FileWriter fw = new FileWriter(DIR_PATH + "/" + FILE_NAME);
            fw.write(tasks.toStorageFormat());
            fw.close();
        } catch (IOException e) {
            throw new StorageException("There was an error in saving your tasks to disk");
        }
    }

}
