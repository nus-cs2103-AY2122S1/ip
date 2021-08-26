import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.util.regex.Pattern;


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
    public TaskList load() throws DukeException {
        String home = System.getProperty("user.dir");
        Path path = Paths.get(home, this.filepath);
        boolean directoryExists = Files.exists(path);

        if (directoryExists) {
            try {
                TaskList tasklist = new TaskList();
                List<String> lines = Files.readAllLines(path);

                for (String str: lines) {
                    String[] strparse = str.split(Pattern.quote(" | "));
                    if (strparse.length < 3) {
                        continue;
                        // incorrect task listed for some reason
                    } else if (strparse[0].equals("T")) {
                            tasklist.addReadTodo(strparse[2].split(" "));
                        } else if (strparse[0].equals("D")) {
                            tasklist.addReadDeadline(strparse[2].split(" "));
                        } else if (strparse[0].equals("E")) {
                        tasklist.addReadEvent(strparse[2].split(" "));
                    }
                }
                return tasklist;
            } catch (IOException e) {
                throw new ReadError();
            }
        } else {
            throw new ReadError();
        }
    }

    public void save(TaskList tasklist) throws DukeException {
        String home = System.getProperty("user.dir");

        String temp = tasklist.saveString();


    }

}
