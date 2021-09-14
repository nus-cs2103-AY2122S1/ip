package duke.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import duke.tasks.Task;

/**
 * A class to handle all the operations that deals with the computer hard drive or memory.
 */
public class Storage {
    private static final String DISK_ERROR_MESSAGE = "A problem occurred while accessing the text file.";
    private final String persistedData;

    /**
     * Constructor for Storage.
     *
     * @param persistedData the relative path to the persisted data starting from the project directory.
     */
    public Storage(String persistedData) {
        this.persistedData = persistedData;
    }

    /**
     * Returns a stream of strings that contains all the individual lines within the persisted data,
     * which is a text file.
     *
     * @return the stream of strings containing all the individual lines in persisted data.
     */
    public Stream<String> loadPersistedData() {
        Path pathToTxt = Paths.get("data", "duke.txt");
        Path pathToData = Paths.get("data");

        if (Files.exists(pathToTxt)) {
            System.out.println("Persisted data exists. Loading...");
            try {
                Stream<String> persistedData = Files.lines(pathToTxt);
                return persistedData;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Persisted data does not exist.");
            try {
                Files.createDirectories(pathToData);
                Files.createFile(pathToTxt);
                return Stream.empty();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return Stream.empty();
    }

    /**
     * Marks the particular task within the persisted data as done, by editing the text file.
     *
     * @param taskNum the task number within the TaskList.
     * @param task the task to mark as done.
     */
    public void markPersistedTaskAsDone(int taskNum, Task task) {
        Path persistedDataPath = Paths.get(persistedData);
        try {
            List<String> allLines = Files.readAllLines(persistedDataPath);
            allLines.set(taskNum, task.persistedDataStringFormat());
            Files.write(persistedDataPath, allLines);
        } catch (IOException e) {
            System.out.println(DISK_ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    /**
     * Adds in a new line of text to the persisted data, corresponding to this task.
     *
     * @param task the new task to add to the persisted data.
     */
    public void addTaskToPersistedData(Task task) {
        String line = task.persistedDataStringFormat();
        line += System.lineSeparator();
        try {
            FileWriter fw = new FileWriter(persistedData, true);
            fw.write(line);
            fw.close();
        } catch (IOException e) {
            System.out.println(DISK_ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    /**
     * Removes the particular line within the persisted data, that corresponds with this
     * particular task number.
     *
     * @param taskNum the task number within the TaskList.
     */
    public void removePersistedTask(int taskNum) {
        Path persistedDataPath = Paths.get(persistedData);
        try {
            List<String> allLines = Files.readAllLines(persistedDataPath);
            allLines.remove(taskNum);
            Files.write(persistedDataPath, allLines);
        } catch (IOException e) {
            System.out.println(DISK_ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}
