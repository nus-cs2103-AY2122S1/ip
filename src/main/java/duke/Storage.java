package main.java.duke;

import main.java.duke.task.Deadline;
import main.java.duke.task.Event;
import main.java.duke.task.Task;
import main.java.duke.task.ToDo;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Storage is in-charge of loading the saved taskList, and updating it.
 *
 * @author Zhen Xuan (Tutorial Group W12)
 * @version CS2103T AY21/22 S2
 */
public class Storage {
    private final String FILEPATH;
    private ArrayList<String> fileContent;

    /**
     * Constructor.
     *
     * @param filePath the specified path of the file.
     */
    Storage(String filePath) {
        this.FILEPATH = filePath;
    }

    /**
     * Loads the file and initialises the TaskList.
     *
     * @return the list of tasks
     * @throws IOException if there is an issue with writing/reading the file
     */
    protected ArrayList<Task> load() throws IOException {
        File directory = new File(this.FILEPATH.split("/")[0]);
        if (!directory.exists()) {
            directory.mkdir();
        }
        ArrayList<Task> tl = new ArrayList<>();
        File savedList = new File(this.FILEPATH);
        if (!savedList.exists()) {
            savedList.createNewFile();
            return tl;
        }
        fileContent = new ArrayList<>(Files.readAllLines(Paths.get(this.FILEPATH), StandardCharsets.UTF_8));

        fileContent.forEach(str ->
        {
            String[] command = str.split("\\|");
            switch (command[0]) {
                case "T":
                    tl.add(new ToDo(command[1].equals("1"), command[2]));
                    break;
                case "E":
                    tl.add(new Event(command[1].equals("1"), command[2], command[3]));
                    break;
                case "D":
                    tl.add(new Deadline(command[1].equals("1"), command[2], command[3]));
                    break;
                default:
                    break;
            }
        });
        return tl;
    }

    /**
     * Change the string in the storage file to indicate that the task is done.
     *
     * @param index the position of the item
     * @throws IOException if there is an error writing to the file
     */
    public void setDone(int index) throws IOException {
        String str = fileContent.get(index);
        fileContent.set(index, str.replace("|0|", "|1|"));
        Files.write(Paths.get(this.FILEPATH), fileContent, StandardCharsets.UTF_8);
    }

    /**
     * Adds a new task to the storage file.
     *
     * @param type        the type of the task
     * @param description the description of the task
     * @param date        the date of the task (if deadline or event)
     * @throws IOException if there is an error writing to the file
     */
    public void add(String type, String description, String date) throws IOException {
        fileContent.add(type + "|0|" + description + "|" + date);
        Files.write(Paths.get(this.FILEPATH), fileContent, StandardCharsets.UTF_8);
    }

    /**
     * Deletes the task from the storage file.
     *
     * @param index the position of the task in the list
     * @throws IOException if there is an error writing to the file
     */
    public void delete(int index) throws IOException {
        fileContent.remove(index);
        Files.write(Paths.get(this.FILEPATH), fileContent, StandardCharsets.UTF_8);
    }
}
