package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the task list from file storage.
     * @return the loaded task list.
     */
    public List<Task> loadStorage() {
        File file = new File(filePath);
        if (!file.exists()) {
            createEmptyFile(file);
            return new ArrayList<>();
        }

        try {
            return readFromFile(file);
        } catch (FileNotFoundException e) {
            // create empty file, prepare for saving later
            System.err.println("Unable to read from the file");
            return new ArrayList<>();
        } catch (DukeException e) {
            System.err.println(e.getMessage());
            return new ArrayList<>();
        }
    }

    private List<Task> readFromFile(File file) throws FileNotFoundException, DukeException{
        Scanner sc = new Scanner(file);
        List<Task> tasks = new ArrayList<>();

        while (sc.hasNextLine()) {
            String currItem = sc.nextLine();
            String[] itemDetails = currItem.split("~");

            Task task;
            switch (itemDetails[0]) {
            case "duke.Todo":
                task = new Todo(itemDetails[1], itemDetails[2]);
                break;
            case "duke.Deadline":
                task = new Deadline(itemDetails[1], itemDetails[2], itemDetails[3]);
                break;
            case "duke.Event":
                task = new Event(itemDetails[1], itemDetails[2], itemDetails[3]);
                break;
            default:
                System.out.println(itemDetails[0]);
                throw new DukeException("Unidentifiable saved information.");
            }
            tasks.add(task);
        }

        return tasks;
    }

    private void createEmptyFile(File f) {
        try {
            f.getParentFile().mkdirs();
            f.createNewFile();
        } catch (IOException e) {
            System.err.println("Unable to initialise an empty file for duke.Storage.");
        }
    }

    /**
     * Saves task list to file storage.
     * Called when the program ends.
     * @param tasks the task list to be saved.
     */
    public void saveStorage(List<Task> tasks) {
        try {
            writeToFile(tasks);
        } catch (IOException e) {
            System.err.println("Something went wrong. Unable to write to duke.Storage.");
        }
    }

    private void writeToFile(List<Task> tasks) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);
        for (Task task : tasks) {
            fileWriter.write(task.toSaveString() + System.lineSeparator());
        }
        fileWriter.close();
    }

}
