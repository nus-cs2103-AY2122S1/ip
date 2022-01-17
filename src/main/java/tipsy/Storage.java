package tipsy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Storage is the class that deals with loading tasks from the file
 * on the hard drive and saving tasks to the file on the hard drive.
 */
public class Storage {
    private Path savePath;

    /**
     * Class constructor.
     *
     * @param filepath  Filepath to the save file.
     */
    public Storage(String filepath) {
        savePath = Path.of(System.getProperty("user.dir"), filepath);
    }

    /**
     * Loads save data from the save file, if it exists.
     * If a save file does not exist, then a new save file
     * will be created.
     *
     * @return ArrayList of saved Tasks if save file exists, otherwise
     *                returns an empty ArrayList instead.
     * @throws LoadingException  If an IOException occurs while loading
     *                the save file.
     */
    public ArrayList<Task> load() throws LoadingException {
        if (Files.exists(savePath)) {
            return loadTasks(savePath);
        } else {
            try {
                Files.createDirectories(savePath.getParent());
                Files.createFile(savePath);
            } catch (IOException e) {
                e.printStackTrace();
                throw new LoadingException();
            }
            return new ArrayList<>();
        }
    }

    private ArrayList<Task> loadTasks(Path savePath) {
        try (BufferedReader reader = Files.newBufferedReader(savePath)) {

            ArrayList<Task> tasks = new ArrayList<>();
            String line = reader.readLine();

            while (line != null) {
                tasks.add(loadTask(line));
                line = reader.readLine();
            }

            return tasks;
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private Task loadTask(String savedTask) {
        Scanner saveDataScanner = new Scanner(savedTask).useDelimiter(", ");
        String taskType = saveDataScanner.next();
        boolean isTaskDone = saveDataScanner.nextInt() == 1;
        String taskDesc = saveDataScanner.next();
        Task loadedTask = null;

        switch (taskType) {
        case "T":
            loadedTask = new ToDo(taskDesc, isTaskDone);
            break;
        case "D":
            LocalDate deadlineDateTime = LocalDate.parse(saveDataScanner.next());
            loadedTask = new Deadline(taskDesc, deadlineDateTime, isTaskDone);
            break;
        case "E":
            LocalDate eventDateTime = LocalDate.parse(saveDataScanner.next());
            loadedTask = new Event(taskDesc, eventDateTime, isTaskDone);
            break;
        default:
            assert false; // Undefined task type input
        }
        saveDataScanner.close();

        return loadedTask;
    }

    /**
     * Saves all the tasks and their information onto the save file.
     *
     * @param tasks TaskList containing the tasks to be saved.
     */
    public void saveTasks(TaskList tasks) {
        try {
            BufferedWriter writer = Files.newBufferedWriter(savePath);
            for (int i = 1; i <= tasks.getSize(); i++) {
                writer.write(tasks.getTask(i).toSaveFormat() + "\n");
            }
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String getPath() {
        return savePath.toString();
    }
}
