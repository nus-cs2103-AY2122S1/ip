package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exceptions.AuguryException;
import duke.exceptions.FileIoException;
import duke.tasks.Task;
import duke.tasks.TaskFactory;
import duke.tasks.TaskList;

/**
 * The {@code Storage} class handles reading and writing data from files.
 */
public class Storage {

    private String path;
    private String dataPath;
    private String settingsPath;

    /**
     * Initializes a {@code Storage} instance to have a {@code String path}.
     *
     * @param path {@code String} representing the filepath to work on.
     */
    public Storage(String path) {
        this.path = path;
        this.dataPath = path + "/tasks.txt";
        this.settingsPath = path + "/settings.txt";
    }

    /**
     * Initializes a {@code TaskList t} using data from the save file.
     * If save file does not exist, {@code Storage} will attempt to create the directory and file.
     *
     * @param tasks {@code TaskList} to write data to.
     * @throws IOException If file cannot be found or written to
     */
    public void initializeTaskList(TaskList tasks) throws IOException {
        createDirectory();
        createFileAtPath(dataPath);
        loadTaskListFromStorage(tasks);
    }

    /**
     * Initializes user {@code Settings} using data from the settings file.
     * If settings file does not exist, {@code Storage} will attempt to create the directory and file.
     *
     * @param settings {@code Settings} instance to write data to
     * @throws IOException If file cannot be found or written to
     */
    public void initializeSettings(Settings settings) throws IOException, AuguryException {
        createDirectory();
        createFileAtPath(settingsPath);
        loadSettingsFromStorage(settings);
    }

    /**
     * Saves the given {@code TaskList tasks} into the save file.
     *
     * @param tasks {@code TaskList} to read data from.
     * @throws FileIoException If file cannot be found or written to
     */
    public void saveTaskListToStorage(TaskList tasks) throws AuguryException {
        try {
            File f = new File(dataPath);
            String s = convertTaskListToString(tasks);
            writeStringToStorage(s, dataPath);
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileIoException("File error occured");
        }
    }

    /**
     * Saves the given {@code Settings} into the save file.
     *
     * @param settings {@code Settings} to read data from.
     * @throws FileIoException If file cannot be found or written to
     */
    public void saveSettingsToStorage(Settings settings) throws AuguryException {
        try {
            File f = new File(settingsPath);
            writeStringToStorage(settings.toString(), settingsPath);
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileIoException("File error occured");
        }
    }

    private void createDirectory() {
        String directory = this.path;
        File dir = new File(directory);
        if (!dir.exists()) {
            boolean dirCreated = dir.mkdir();
            assert dirCreated;
        }
    }

    private void createFileAtPath(String path) throws IOException {
        File f = new File(path);
        boolean fileCreated = f.createNewFile();
        assert fileCreated;
    }

    private String convertTaskListToString(TaskList xs) {
        ArrayList<Task> tasks = xs.getTasks();
        ArrayList<String> tasksString = new ArrayList<>();

        for (Task t : tasks) {
            tasksString.add(t.toString());
        }

        String res = String.join("\n", tasksString);
        return res;
    }

    private void loadTaskListFromStorage(TaskList t) throws IOException {
        // read tasks.txt
        Scanner s = new Scanner(new File(dataPath));
        ArrayList<String> tasks = new ArrayList<>();
        while (s.hasNext()) {
            tasks.add(s.nextLine());
        }

        // create a task for each line
        TaskFactory tf = new TaskFactory();
        for (String task : tasks) {
            Task newTask = tf.createTask(task);
            t.addTask(newTask);
        }
    }

    private void loadSettingsFromStorage(Settings settings) throws IOException, AuguryException {
        // read settings.txt
        Scanner s = new Scanner(new File(settingsPath));
        String theme = "light";
        while (s.hasNext()) {
            theme = s.nextLine();
        }
        settings.setTheme(theme);
    }

    private void writeStringToStorage(String s, String path) throws IOException {
        FileWriter fw = new FileWriter(path);
        fw.write(s);
        fw.close();
    }

}
