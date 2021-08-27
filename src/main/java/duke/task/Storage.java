package duke.task;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * This class implements a storage object that deals with loading tasks from the file and saving tasks in the file.
 *
 * CS2103T ip
 * AY21/22 Semester 1
 *
 * @author Kishendran Vendar Kon (Group G05)
 */
public class Storage {
    private File f;
    private String txtPath;

    /**
     * Default constructor.
     *
     * @param txtFile String representing TextFile used for storage.
     * @throws IOException if file or directory cannot be created.
     */
    public Storage(String txtFile) throws IOException {
        String home = System.getProperty("user.home");
        Path path = Paths.get(home, "duke", "dir");
        txtPath = new StringBuilder().append(home).append("\\duke")
                .append("\\dir").append("\\" + txtFile).toString();
        boolean directoryExists = Files.exists(path);

        if (!directoryExists) {
            Files.createDirectories(path);
        }

        f = new File(txtPath);

        if (!f.exists()) {
            f.createNewFile();
        }
    }

    /**
     * Loads task from file.
     *
     * @throws IOException if file cannot be read from.
     */
    public ArrayList<String> load() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(txtPath));
        ArrayList<String> loadedTasks = new ArrayList<>();
        String s;
        while ((s = reader.readLine()) != null) {
            loadedTasks.add(s);
        }
        reader.close();
        return loadedTasks;
    }

    /**
     * Saves task to file.
     *
     * @param tasks List of tasks to be saved.
     * @throws IOException if file cannot be written to.
     */
    public void save(TaskList tasks) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(txtPath));
        for (int i = 1; i < tasks.size() + 1; i++) {
            writer.write(tasks.getStringDes(i) + "\n");
        }
        writer.close();
    }

}
