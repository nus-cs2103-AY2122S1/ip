package chadbot.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    /** File to read and write saved data */
    private File file;
    /** Scanner to read saved data from file */
    private Scanner scanner;
    /** Intermediate data structure to modify saved data */
    private ArrayList<String> taskListString;

    /**
     * Returns a new Storage object initialized with data from the file in filePath.
     *
     * @param filePath Path to the file containing storage data.
     */
    public Storage (String filePath) {
        this.file = new File(filePath);
        this.taskListString = new ArrayList<>();
    }

    /**
     * Returns an ArrayList of String arrays, where each array specifies a single stored Task.
     *
     * @return ArrayList of String arrays representing Tasks.
     * @throws FileNotFoundException  If file specified in filePath does not exists.
     */
    public ArrayList<String[]> load() throws FileNotFoundException {
        ArrayList<String[]> taskList = new ArrayList<>();
        scanner = new Scanner(file);
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            taskListString.add(line);
            String[] parsedSavedEntry = line.split("[|]");
            taskList.add(parsedSavedEntry);
        }
        return taskList;
    }

    /**
     * Saves current tasks from the intermediate data structure to file specified in filePath.
     *
     * @throws IOException  If file specified in filePath cannot be written to.
     */
    public void save() throws IOException {
        FileWriter fileWriter = new FileWriter(file);
        int lastIndex = this.taskListString.size() - 1;
        boolean hasMoreThanOneEntry = lastIndex >= 0;
        if (hasMoreThanOneEntry) {
            for (int i = 0; i < lastIndex; i++) {
                String saveFileEntry = this.taskListString.get(i);
                fileWriter.write(saveFileEntry + "\n");
            }
            String finalSaveEntry = this.taskListString.get(lastIndex);
            fileWriter.write(finalSaveEntry);
        }
        fileWriter.close();
    }

    /**
     * Saves non-timed task to the intermediate data structure.
     *
     * @param type Type of task to be saved.
     * @param status Status of task to be saved.
     * @param description Description of task to be saved.
     */
    public void saveUntimedTask(String type, String status, String description) {
        taskListString.add(type + "|" + status + "|" + description);
    }

    /**
     * Saves timed task to the intermediate data structure.
     *
     * @param type Type of task to be saved.
     * @param status Status of task to be saved.
     * @param description Description of task to be saved.
     * @param timeframe Time limit of task to be saved
     */
    public void saveTimedTask(String type, String status, String description, LocalDateTime timeframe) {
        String dateTime = timeframe.format(DateTimeFormatter.ISO_DATE_TIME);
        taskListString.add(type + "|" + status + "|" + description + "|" + dateTime);
    }

    /**
     * Modifies the status of task in intermediate data structure to done.
     *
     * @param index Index of task to be modified.
     */
    public void saveTaskDone(int index) {
        assert taskListString.size() >= index : "Index should be in range";
        int storedIndex = index - 1;
        String oldData = taskListString.get(storedIndex);
        String newData = oldData.substring(0, 2) + 'X' + oldData.substring(3);
        taskListString.set(storedIndex, newData);
    }

    /**
     * Removes task from intermediate data structure.
     *
     * @param index Index of task to be removed.
     */
    public void removeTask(int index) {
        assert taskListString.size() >= index : "Index should be in range";
        taskListString.remove(index);
    }
}
