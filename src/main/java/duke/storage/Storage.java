package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import duke.task.TaskList;

public class Storage {
    private final static String DIR_PATH = "data";
    private final static String FILE_NAME = "tasks.txt";

    public  File load() {
        File file = new File(DIR_PATH + "/" + FILE_NAME);
        return file;
    }

    public void saveTasks(TaskList tasks) throws StorageException {
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
