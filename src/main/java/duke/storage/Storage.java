package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.data.exception.DukeException;
import duke.data.tasks.Deadlines;
import duke.data.tasks.Events;
import duke.data.tasks.Task;
import duke.data.tasks.ToDos;

/**
 * Represents the file used to store the list of tasks.
 */
public class Storage {
    private final File filePath;

    /**
     * Constructor for Storage instance.
     * @param filePath The path where the data file is to be stored
     */
    public Storage(File filePath) {
        this.filePath = filePath;

        if (!filePath.getParentFile().exists()) {
            filePath.getParentFile().mkdir();
        }
    }

    /**
     * Loads data from the storage file.
     * @return The list of tasks in the storage file
     */
    public ArrayList<Task> load() {
        ArrayList<Task> loadedTasks = new ArrayList<>();

        if (filePath.exists()) {
            try {
                Scanner s = new Scanner(filePath);
                s.useDelimiter("~");

                while (s.hasNext()) {
                    String type = s.next();

                    switch (type) {
                    case "T":
                        loadedTasks.add(
                                new ToDos(
                                        s.next().equals("1"),
                                        s.next(),
                                        s.next()
                                ));
                        break;
                    case "D":
                        loadedTasks.add(
                                new Deadlines(
                                        s.next().equals("1"),
                                        s.next(),
                                        s.next(),
                                        s.next()
                                ));
                        break;
                    case "E":
                        loadedTasks.add(
                                new Events(
                                        s.next().equals("1"),
                                        s.next(),
                                        s.next(),
                                        s.next()
                                ));
                        break;
                    default:
                        break;
                    }
                }
                s.close();
            } catch (FileNotFoundException e) {
                throw new DukeException("Data file error");
            }
        }
        return loadedTasks;
    }

    /**
     * Writes data to the storage file.
     * @param data The data to be written to the storage file
     */
    public void write(String data) {
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(data);
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Failed to write save file");
        }
    }
}
