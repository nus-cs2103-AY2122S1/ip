import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Storage {
    private static final Path DATA_DIRECTORY_PATH = Paths.get("data");

    private static final Path SAVE_FILE_PATH = Paths.get("data", "duke.txt");

    private File saveFile;

    public Storage() throws DukeException {
        try {
            if (!Files.exists(DATA_DIRECTORY_PATH)) {
                Files.createDirectory(DATA_DIRECTORY_PATH);
            }
            if (!Files.exists(SAVE_FILE_PATH)) {
                Files.createFile(SAVE_FILE_PATH);
            }
            this.saveFile = SAVE_FILE_PATH.toFile();
        } catch (IOException e) {
            throw new DukeException("There was an error creating a save file for Duke.");
        }
    }

    public List<String> getSavedContents() throws DukeException {
        try {
            return Files.readAllLines(SAVE_FILE_PATH);
        } catch (IOException e) {
            throw new DukeException("There was an error retrieving your save file for Duke.");
        }
    }
    public void appendToSave(Task task) throws DukeException {
        try {
            FileWriter fw = new FileWriter(this.saveFile, true);
            fw.write(task.getSaveFormat());
            fw.close();
        } catch (IOException e) {
            throw new DukeException("There was an error accessing your save file.");
        }
    }

    public void overwriteSave(TaskList taskList) throws DukeException {
        try {
            FileWriter fw = new FileWriter(this.saveFile);
            fw.write(taskList.getSaveFormat());
            fw.close();
        } catch (IOException e) {
            throw new DukeException("There was an error accessing your save file.");
        }
    }
}
