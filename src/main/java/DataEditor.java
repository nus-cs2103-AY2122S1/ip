import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.util.regex.Pattern;


/**
 * Allows reading and saving of tasks to a given file.
 *
 */
public class DataEditor {
    private String filePath;

    /**
     * Constructor for DataEditor
     * @param filePath
     */
    public DataEditor(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Reads txt file from path given and returns an ArrayList of the tasks inside.
     * @return ArrayList of tasks.
     * @throws DukeException Exception if any when reading file.
     */
    public TaskList loadData() throws DukeException {
        String homeDir = System.getProperty("user.dir");
        Path path = Paths.get(homeDir, this.filePath);
        boolean directoryExists = Files.exists(path);

        if (directoryExists) {
            try {
                TaskList tasklist = new TaskList();
                List<String> lines = Files.readAllLines(path);

                for (String str : lines) {
                    String[] strparse = str.split(Pattern.quote(" | "));
                    if (strparse.length < 3) {
                        continue;
                        // incorrect task listed for some reason
                    } else if (strparse[0].equals("T")) {
                        tasklist.addReadTodo(strparse[2], Integer.parseInt(strparse[1]));
                    } else if (strparse[0].equals("D")) {
                        tasklist.addReadDeadline(strparse[2], Integer.parseInt(strparse[1]), strparse[3]);
                    } else if (strparse[0].equals("E")) {
                        tasklist.addReadEvent(strparse[2], Integer.parseInt(strparse[1]), strparse[3]);
                    }
                }
                return tasklist;
            } catch (DukeException e) {
                    throw e;
            } catch (IOException e) {
                throw new LoadingFileError("Uwu! There's something wrong withw the existing file! "
                        + "Creating new file for you. . .");
            }
        } else {
            throw new LoadingFileError("Uwu! Pre-existing File not foundw! "
                    + "Creating new file for you. . .");
        }
    }

    /**
     * Saves tasklist in String form to txt file. If file does not exist, one is created.
     * @param tasklist tasklist to be converted into txt file, then saved.
     * @throws DukeException Exception if any when saving file.
     */
    public void saveData(TaskList tasklist) throws DukeException {
        String textToSave = tasklist.saveString();
        String homeDir = System.getProperty("user.dir");
        Path path = Paths.get(homeDir, this.filePath);
        boolean directoryExists = Files.exists(path);

        if (!directoryExists) {
            try {
                Files.createDirectories(path.getParent());
            } catch(IOException e){
                throw new SaveDirectoryError();
            }
        }

        try {
            Files.write(Paths.get(homeDir, this.filePath), textToSave.getBytes());
        } catch (IOException e){
            throw new SaveFileError();
        }

    }

}
