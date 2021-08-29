package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.tasklist.Task;

/**
 * Represents a file directory manager.
 * Contains operations to read from file and save task into a file.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs TaskList Object.
     *
     * @param filePath relative or absolute file path to read.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads content task file from filePath.
     *
     * @return content of file.
     * @throws DukeException If filePath doesn't exist.
     */
    public Scanner load() throws DukeException {
        try {
            return new Scanner(new File(filePath));
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
    }

    /**
     * Saves list of task into a text file.
     *
     * @param lib library / list of task to be stored.
     * @throws DukeException If unable to create save file.
     */
    public void save(List<Task> lib) throws DukeException {
        String location = "data/duke.txt";

        try {

            File f = new File(location);
            if (!f.exists()) {
                if (f.getParentFile().mkdirs()) {
                    System.out.println("Directory created");
                }
                if (f.createNewFile()) {
                    System.out.println("Text file created");
                }
            }

            FileWriter fw = new FileWriter(location);
            for (Task tsk: lib) {
                fw.write(tsk.save() + System.lineSeparator());
            }
            fw.close();
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
    }
}
