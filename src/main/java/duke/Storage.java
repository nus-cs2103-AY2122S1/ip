package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Reads and Writes data to the tasks' database
 */
public class Storage {
    private final String FILE_PATH;
    private final String DIRECTORY = "data";

    public Storage(String filePath) {
        this.FILE_PATH = filePath;
    }

    /**
     * Reads data from tasks' database and loads them
     * into an ArrayList to be parsed.
     *
     * @return arraylist of string representation of saved tasks
     * @throws IOException
     */
    public ArrayList<String> load() throws IOException {
        File directory = new File(DIRECTORY);
        if (!directory.exists()) {
            directory.mkdir();
        }
        File data = new File(FILE_PATH);
        data.createNewFile();
        Scanner s = new Scanner(data);
        ArrayList<String> tasks = new ArrayList<>();
        while (s.hasNext()) {
            tasks.add(s.nextLine());
        }
        return tasks;
    }

    /**
     * Writes all the tasks in TaskList into the database.
     *
     * @param list of tasks
     * @throws IOException
     */
    public void saveTask(TaskList list) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        for (Task t : list.getList()) {
            fw.write(t.toSavedAs() + System.lineSeparator());
        }
        fw.close();
    }
}
