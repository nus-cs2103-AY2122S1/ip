package side.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

import side.tasks.Deadline;
import side.tasks.Event;
import side.tasks.Task;

/**
 * Models saving to hard disk using a .txt file. It contains logic for saving and retrieving from
 * the .txt file.
 *
 * @author Lua Yi Da
 */

public class Storage {

    private Parser parser;

    /**
     * Initialises new Storage instance with a parser.
     */
    public Storage() {
        this.parser = new Parser();
    }

    /**
     * Helper method to save existing tasks to .txt file.
     *
     * @param savedList ArrayList containing existing tasks.
     * @param directory String representing directory to save to.
     * @throws IOException Error with file operation.
     */
    public void save(ArrayList<Task> savedList, String directory) throws IOException {
        String filePath = directory + "/Side.txt";
        File file = new File(filePath);
        File dataDirectory = new File(directory);

        if (!Files.exists(Path.of(directory))) {
            boolean isDirectory = dataDirectory.mkdir();
        }
        if (!Files.exists(Path.of(filePath))) {
            boolean isFile = file.createNewFile();
        }

        FileWriter writer = new FileWriter(filePath);
        StringBuilder taskString = new StringBuilder();

        for (Task t : savedList) {
            if (t instanceof Deadline) {
                String taskDetails = "D | " + ((Deadline) t).getDatetime().saveDatetime();
                taskString.append(taskDetails);
            } else if (t instanceof Event) {
                String taskDetails = "E | " + ((Event) t).getStartDatetime().saveDatetime()
                        + "/to" + ((Event) t).getEndDatetime().saveDatetime();
                taskString.append(taskDetails);
            } else {
                taskString.append("T | No time");
            }
            String generalTaskDetails = " | " + t.getDescription() + " | " + (t.getIsDone() ? "T" : "F") + "\n";
            taskString.append(generalTaskDetails);
        }

        writer.write(taskString.toString());
        writer.close();
    }

    /**
     * Helper method to retrieve saved tasks from .txt file.
     *
     * @param directory String representing directory to read from.
     * @return ArrayList of tasks saved from previous session.
     * @throws FileNotFoundException Error with finding file to read from.
     */
    public ArrayList<Task> retrieve(String directory) throws FileNotFoundException {
        String filePath = directory + "/Side.txt";
        File file = new File(filePath);
        ArrayList<Task> savedList = new ArrayList<>();

        if (Files.exists(Path.of(filePath))) {
            Scanner fileScanner = new Scanner(file);

            while (fileScanner.hasNextLine()) {
                String[] lineData = fileScanner.nextLine().split("\\|");
                String taskType = lineData[0];
                String taskTime = lineData[1].trim();
                String taskDescription = lineData[2];
                boolean isTaskDone = lineData[3].equals(" T");

                switch (taskType) {
                case "D ":
                    savedList.add(new Deadline(taskDescription, taskTime, isTaskDone));
                    break;
                case "E ":
                    if (this.parser.findEventDatetime(taskTime) != null) {
                        savedList.add(new Event(taskDescription, this.parser.findEventDatetime(taskTime), isTaskDone));
                    }
                    break;
                case "T ":
                    savedList.add(new Task(taskDescription, isTaskDone));
                    break;
                default:
                    break;
                }
            }
        } else {
            throw new FileNotFoundException();
        }
        return savedList;
    }
}
