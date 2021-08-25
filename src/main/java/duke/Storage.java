package duke;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Storage {
    private final String fileName;
    private static final String DATA_FILEPATH = System.getProperty("user.dir") + "/data/";
    private static final String DATA_DELIMITER = " \\| ";
    private final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-d H:mm");

    public Storage(String fileName) {
        this.fileName = fileName;
    }

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
