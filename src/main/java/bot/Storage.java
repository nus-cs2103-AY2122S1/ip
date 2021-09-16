package bot;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import task.Task;

/**
 * A class that handles the storage of list data in a txt format.
 * The data is stored in the local hard drive.
 */
public class Storage {

    private static final String FILE_PATH = "data/duke.txt";

    /**
     * Returns a TaskList object de-serialised from the local data.
     * If no local data exists, returns an empty TaskList object.
     *
     * @return A TaskList object that will contain all the tasks given to Duke by the user.
     * @throws DukeException if there is an error loading the local data.
     */
    public static TaskList load() throws DukeException {
        try {
            // Set up the list of lines and the reader
            TaskList list = new TaskList();
            BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));

            // Read the lines from the file specified
            // FileNotFoundException <: IOException
            String line;
            while ((line = reader.readLine()) != null) {
                list.addTask(Task.parseFromStorage(line));
            }

            // Return the parsed list of tasks
            return list;

        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }

    /**
     * Saves the current list as a local txt file.
     *
     * @param list The list to be saved into the local Data directory.
     * @throws DukeException if there is an error saving the local data.
     */
    public static void save(TaskList list) throws DukeException {
        assert list != null : "The task list has not been initialised yet";

        // Set up the file
        String absolutePath = new File(FILE_PATH).getAbsolutePath();
        File file = new File(absolutePath);

        try {
            while (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }

            // Set up the writer using absolute path
            BufferedWriter writer = new BufferedWriter(new FileWriter(absolutePath));

            // Write the lines from the list into the file
            for (int i = 0; i < list.getSize(); i++) {
                writer.write(list.getTask(i).convertToStorageFormat());
                writer.newLine();
            }
            writer.close();

        } catch (IOException e) {
            throw new DukeException(e.getMessage() + ".");
        }
    }

}
