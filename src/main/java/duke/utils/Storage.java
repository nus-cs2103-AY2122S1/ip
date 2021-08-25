package duke.utils;

import duke.tasks.Task;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class Storage {
    final String persistedData;

    public Storage(String persistedData) {
        this.persistedData = persistedData;
    }

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

    public void markPersistedTaskAsDone(int taskNum, Task task) {
        Path persistedDataPath = Paths.get(persistedData);
        try {
            List<String> allLines = Files.readAllLines(persistedDataPath);
            allLines.set(taskNum, task.persistedDataStringFormat());
            Files.write(persistedDataPath, allLines);
        } catch (IOException e) {
            System.out.println("A problem occurred while reading from the text file.");
            e.printStackTrace();
        }
    }

    public void addTaskToPersistedData(Task task) {
        String line = task.persistedDataStringFormat();
        line += System.lineSeparator();
        try {
            FileWriter fw = new FileWriter(persistedData, true);
            fw.write(line);
            fw.close();
        } catch (IOException e) {
            System.out.println("A problem occurred while writing to text file.");
            e.printStackTrace();
        }
    }

    public void removePersistedTask(int taskNum) {
        Path persistedDataPath = Paths.get(persistedData);
        try {
            List<String> allLines = Files.readAllLines(persistedDataPath);
            allLines.remove(taskNum);
            Files.write(persistedDataPath, allLines);
        } catch (IOException e) {
            System.out.println("A problem occurred while reading/writing the text file.");
            e.printStackTrace();
        }
    }
}
