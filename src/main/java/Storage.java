import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private final Path filePath;
    private final DukeParser parser;

    private void createDirectoriesAndFiles() throws IOException {
        // create storage directories and files if it does not exist
        if (Files.notExists(filePath)) {
            File f = new File(filePath.toString());
            f.getParentFile().mkdirs();
            f.createNewFile();
        }
    }

    public Storage(String filePath) throws IOException {
        this.filePath = Paths.get(System.getProperty("user.dir"), filePath);
        createDirectoriesAndFiles();
        this.parser = new DukeParser();
    }

    /**
     * Returns a list of tasks based on the specified data file path.
     *
     * @return a list of tasks from storage.
     * @throws DukeFileSystemException if the file that was read is of an invalid format.
     */
    public List<Task> load() throws DukeFileSystemException {
        File dataFile = new File(this.filePath.toString());
        List<Task> taskList = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(dataFile);
            while (scanner.hasNext()) {
                taskList.add(parser.parseTaskFromLine(scanner.nextLine()));
            }
        } catch (FileNotFoundException e) {
            throw new DukeFileSystemException("Unable to load previous tasks. " +
                    "A new list will be used for this session. Ensure that " + filePath.toString() + " exists.");
        } catch (DukeFileSystemException e) {
            throw e;
        }
        return taskList;
    }

    /**
     * Stores the tasks in Duke format to specified file path in the constructor to be cached.
     *
     * @param taskList a list of tasks to be converted and stored.
     * @throws IOException if the file path specified is invalid.
     */
    public void writeTasksToFile(TaskList taskList) throws IOException {
        FileWriter fileWriter = new FileWriter(this.filePath.toString());
        fileWriter.write(taskList.toDukeStoreFormat());
        fileWriter.close();
        return;
    }
}
