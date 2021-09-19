package duke;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class for Storage in Duke which
 * deals with loading and saving tasks in the given text file.
 *
 * @author Samuel Lau
 */
public class Storage {
    protected String filePath;

    /**
     * Constructor for Storage class.
     *
     * @param filePath The path of the given text file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns the ArrayList of tasks obtained from loading
     * the text file.
     *
     * @return ArrayList of tasks.
     */
    public ArrayList<Task> load() {
        ArrayList<Task> list = new ArrayList<>();
        File file = new File(filePath);
        file.getParentFile().mkdirs();
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            while (line != null) {
                Task task = Parser.parseData(line);
                if (task != null) {
                    list.add(task);
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            throw new DukeException(DukeException.Type.LOADING);
        }
        return list;
    }

    /**
     * Save the tasks in the TaskList to the text file
     * in the specified written format before exiting Duke.
     *
     * @param tasks TaskList to be saved.
     */
    public void writeAll(TaskList tasks) {
        try {
            FileWriter writer = new FileWriter(filePath);
            assert tasks.list != null : "List must exist";
            for (Task task : tasks.list) {
                writer.write(task.toWrite());
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
