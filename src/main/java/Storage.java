import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

import commands.DukeException;
import tasks.TaskList;

public class Storage {
    private static final String DIRECTORY_PATH = "./duke-files";
    private static final String FILE_PATH = DIRECTORY_PATH + "/tasks.txt";
    private final File file;

    public Storage() {
        File directory = new File(DIRECTORY_PATH);
        boolean isDirectoryExist = directory.exists();
        if (!isDirectoryExist) {
            directory.mkdir();;
        }
        this.file = new File(FILE_PATH);
        try {
            this.file.createNewFile();
        } catch (IOException e) {

        }
    }

    public void save(TaskList taskList) throws DukeException {
        try {
            FileWriter fileWriter = new FileWriter(this.file);
            fileWriter.write(taskList.toString());
            fileWriter.close();
        } catch (IOException e) {
            throw new DukeException("OOPS!!! " + e.getMessage());
        }
    }
}
