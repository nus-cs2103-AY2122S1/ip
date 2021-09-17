package duke.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.exceptions.DukeException;

/**
 * Represents as class that handles saving/loading tasks to/from hard disk
 */
public class Storage {
    private static final String DONE = "1";
    private static final String NOT_DONE = "0";
    private static final String DELIMITER = " | ";
    private static final String DELIMITER_REGEX = " \\| ";
    private static final String NUM_TASK_DONE = "Number of tasks done";
    private String filePath;
    private int numTaskDone;
    /**
     * Creates Storage class based on file path
     *
     * @param filePath
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    private StorageElement fileLineToStorageElement(String fileLine) {
        String[] dataList = fileLine.split(Storage.DELIMITER_REGEX);
        String taskIcon = dataList[0];
        Boolean isDone = Integer.parseInt(dataList[1]) == 1;
        String description = dataList[2];
        if (dataList.length == 3) {
            return new StorageElement(taskIcon, isDone, description);
        }
        if (dataList.length > 3) {
            LocalDate time = LocalDate.parse(dataList[3]);
            return new StorageElement(taskIcon, isDone, description, time);
        }
        return null;
    }

    private String storageElementToFileLine(StorageElement storageElement) {
        List<String> stringList = new ArrayList<>();
        stringList.add(storageElement.getTaskIcon());
        stringList.add(storageElement.getDone() ? DONE : NOT_DONE);
        stringList.add(storageElement.getDescription());

        if (storageElement.getTime() != null) {
            stringList.add(storageElement.getTime().toString());
        }

        return String.join(Storage.DELIMITER, stringList);
    }

    private static void writeToFile(String filePath, String textToAdd) {
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(textToAdd);
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    /**
     * Loads task list from disk
     *
     * @return A list of storage element that can be passed to Task object
     * @throws DukeException
     */
    public List<StorageElement> load() throws DukeException {
        ArrayList<StorageElement> storageList = new ArrayList<>();
        File f = new File(this.filePath); // create a File for the given file path
        try {
            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            while (s.hasNext()) {
                String fileLine = s.nextLine();
                if (fileLine.startsWith(NUM_TASK_DONE)) {
                    this.numTaskDone = Integer.parseInt(fileLine.split(DELIMITER_REGEX)[1]);
                } else {
                    StorageElement storageElement = this.fileLineToStorageElement(fileLine);
                    storageList.add(storageElement);
                }
            }
            return storageList;
        } catch (FileNotFoundException e) {
            //Data file doesn't exist
            //Create directory
            Path path = Path.of(filePath);
            File directory = new File(path.getParent().toString());
            if (!directory.exists()) {
                directory.mkdirs();
            }
            throw new DukeException("Data file not found");
        }
    }

    /**
     * Saves tasklist to disk
     * @param taskList Task list to be saved
     */
    public void save(TaskList taskList) {
        List<StorageElement> storageList = taskList.getStorageList();

        String fileContent = "";

        // Saving the number of task done
        fileContent += NUM_TASK_DONE + DELIMITER + taskList.getNumTaskDone() + System.lineSeparator();

        for (StorageElement storageElement : storageList) {
            fileContent += this.storageElementToFileLine(storageElement) + System.lineSeparator();
        }

        this.writeToFile(this.filePath, fileContent);
    }

    public int getNumTaskDone() {
        return this.numTaskDone;
    }
}
