package bot;

import task.Task;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class Storage {

    private static final String FILE_PATH = "data/duke.txt";

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

    public static void save(TaskList list) throws DukeException {

        // Set up the file
        String absolutePath = new File(FILE_PATH).getAbsolutePath();
        File file = new File(absolutePath);

        try {
            if (!file.exists()) {
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
