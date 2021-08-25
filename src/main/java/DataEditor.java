import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;


/**
 * Allows reading, editing, saving of tasks to a given txt file.
 *
 */
public class DataEditor {
    private final String filepath;

    /**
     * Constructor for DataEditor
     * @param filepath
     */
    public DataEditor(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Reads txt file from path given and returns an ArrayList of the tasks inside.
     * @return ArrayList of tasks.
     * @throws DukeException Exception if any when reading file.
     */
    public ArrayList<Task> load() throws DukeException {
        String home = System.getProperty("user.dir");
        Path path = Paths.get(home, this.filepath);
        boolean directoryExists = Files.exists(path);

        if (directoryExists) {
            try {
                ArrayList<Task> tasklist = new ArrayList<>(100);
                List<String> lines = Files.readAllLines(path);

                for (String str: lines) {
                    String[] strparse = str.split(" | ");
                    if (strparse.length < 3) {
                        continue;
                        // incorrect task
                    } else {
//
                    }

                }

                return tasklist;
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } else {
//            throw new ReadError();
        }
        return new ArrayList<>(100);
    }

    public void save() throws DukeException {
        String home = System.getProperty("user.dir");

    }

}
