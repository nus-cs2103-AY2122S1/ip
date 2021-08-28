package duke;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class encapsulates the mechanism of storing data for Duke.
 *
 * @author Kleon Ang
 */
public class Storage {
    private static final String DATA_DELIMITER = " \\| ";
    private static final String DATA_FILEPATH = System.getProperty("user.dir") + "/data/";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-d H:mm");
    private final String fileName;

    /**
     * Constructor for a Storage class.
     *
     * @param fileName The name of the file to store data in.
     */
    public Storage(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Load the data from the data file if available into Duke.
     *
     * @return a List of Tasks saved in the data file.
     * @throws DukeException An exception specifically encountered in Duke's operations.
     */
    public List<Task> load() throws DukeException {
        List<Task> tasks = new ArrayList<>();
        File directory = new File(DATA_FILEPATH);
        if (!directory.exists()) {
            directory.mkdir();
        }
        try {
            File dataFile = new File(DATA_FILEPATH + this.fileName);
            Scanner fileReader = new Scanner(dataFile);
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                String[] data = line.split(DATA_DELIMITER);
                String taskType = data[0];
                String doneStatus = data[1];
                Task importedTask;
                // Assign correct Task type to importedTask
                switch (taskType) {
                case "T":
                    importedTask = new Todo(data[2]);
                    break;
                case "D":
                    LocalDateTime deadlineDatetime = LocalDateTime.parse(data[3], FORMATTER);
                    importedTask = new Deadline(data[2], deadlineDatetime);
                    break;
                case "E":
                    LocalDateTime eventDatetime = LocalDateTime.parse(data[3], FORMATTER);
                    importedTask = new Event(data[2], eventDatetime);
                    break;
                default:
                    throw new IllegalStateException("Unexpected Task value: " + taskType);
                }
                // Mark imported task as done if doneStatus is 1
                if (doneStatus.equals("1")) {
                    importedTask.markAsDone();
                }
                tasks.add(importedTask);
            }
            fileReader.close();
            Ui.printReply("duke.txt found. Tasks have been imported.");
        } catch (FileNotFoundException e) {
            try {
                File dataFile = new File(DATA_FILEPATH + this.fileName);
                dataFile.createNewFile();
                String message = this.fileName + " not found. File has been created.";
                Ui.printReply(message);
            } catch (IOException ioException) {
                Ui.printReply(ioException.getMessage());
            }
        }
        return tasks;
    }

    /**
     * Rewrite the data in the data file with the most recent Tasks.
     *
     * @param tasks A TaskList containing the most updated tasks.
     */
    public void rewriteData(TaskList tasks) {
        try {
            FileWriter fileWriter = new FileWriter(DATA_FILEPATH + this.fileName, false);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (Task task : tasks) {
                bufferedWriter.write(task.getDataString());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (IOException e) {
            Ui.printReply(e.getMessage());
        }
    }
}
