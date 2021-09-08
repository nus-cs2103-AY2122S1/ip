package bobbybot.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import bobbybot.exceptions.InvalidSaveFileException;
import bobbybot.tasks.Deadline;
import bobbybot.tasks.Event;
import bobbybot.tasks.Task;
import bobbybot.tasks.ToDo;

/**
 * Represents the storage of data needed to load tasks
 */
public class Storage {

    private static final DateTimeFormatter DT_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-uuuu HH:mm");
    private final String filePath;
    private final String dirPath = "data";
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads files from .txt file
     * @return List of tasks
     */
    public List<Task> load() {
        File f = new File(filePath);
        File dir = new File(dirPath);
        if (!dir.isDirectory()) {
            dir.mkdir();
        }
        if (!f.isFile()) {
            createNewDataFile();
        }
        try {
            Scanner s = new Scanner(f);
            List<Task> tasks = new ArrayList<>();
            while (s.hasNext()) {
                String[] row = s.nextLine().split(",");
                // load row into task
                switch (row[0]) {
                // data format: [type],[isDone],[desc],[period]
                case "T":
                    // load task
                    tasks.add(new ToDo(row[2], row[1].equals("1")));
                    break;
                case "D":
                    // load deadline
                    tasks.add(new Deadline(row[2], row[3], row[1].equals("1"), DT_FORMATTER));
                    break;
                case "E":
                    // load event
                    tasks.add(new Event(row[2], row[3], row[1].equals("1")));
                    break;
                default:
                    System.out.println("Invalid task format");
                }
            }
            return tasks;
        } catch (FileNotFoundException e) {
            System.out.println("Failed to load from save file");
            return Collections.emptyList();
        }
    }

    /**
     * Saves all tasks in .txt file
     */
    public void save(TaskList tasks) throws InvalidSaveFileException {
        // save task to .txt file
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task task : tasks.getTasks()) {
                String saveRow = task.getSaveFormatString() + "\n";
                fw.write(saveRow);
            }
            fw.close();
        } catch (IOException e) {
            throw new InvalidSaveFileException("The save file is not accessible!");
        }
    }

    /**
     * Creates new .txt file to store tasks at filepath
     */
    public void createNewDataFile() {
        File file = new File(filePath);
        try {
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("Could not create new text file: " + filePath);
            System.exit(0);
        }
    }
}

