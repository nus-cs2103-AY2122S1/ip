package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private final String LIST_ARCHIVE_DIVIDER = "---";
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the task list from file storage.
     * @return the loaded task list.
     */
    public List<Task>[] loadStorage() throws DukeException {
        File file = new File(filePath);

        List<Task>[] results = new List[2];

        if (!file.exists()) {
            createEmptyFile(file);

            results[0] = new ArrayList<>();
            results[1] = new ArrayList<>();
            return results;
        }

        try {
            return readFromFile(file);
        } catch (FileNotFoundException e) {
            throw new DukeException("Yiyang-bot cannot read the storage file");
        }
    }

    private List<Task>[] readFromFile(File file) throws FileNotFoundException, DukeException{
        Scanner sc = new Scanner(file);

        boolean isReadingArchive = false;
        List<Task>[] results = new List[2];

        List<Task> tasks = new ArrayList<>();
        while (sc.hasNextLine()) {
            String currItem = sc.nextLine();

            if (currItem.equals(LIST_ARCHIVE_DIVIDER)) {
                results[0] = tasks;
                isReadingArchive = true;
                tasks = new ArrayList<>();
                continue;
            }


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

        results[1] = (isReadingArchive) ? tasks : new ArrayList<>();
        return results;
    }

    private void createEmptyFile(File f) throws DukeException {
        try {
            f.getParentFile().mkdirs();
            f.createNewFile();
        } catch (IOException e) {
            throw new DukeException("Yiyang-bot cannot initialise an empty file for duke.Storage.");
        }
    }

    /**
     * Saves task list to file storage.
     * Called when the program ends.
     * @param tasks the task list to be saved.
     */
    public void saveStorage(List<Task> tasks, List<Task> archives) {
        try {
            writeToFile(tasks, archives);
        } catch (IOException e) {
            System.err.println("Something went wrong. Unable to write to duke.Storage.");
        }
    }

    private void writeToFile(List<Task> tasks, List<Task> archives) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);
        for (Task task : tasks) {
            fileWriter.write(task.toSaveString() + System.lineSeparator());
        }

        fileWriter.write(LIST_ARCHIVE_DIVIDER + System.lineSeparator());

        for (Task task : archives) {
            fileWriter.write(task.toSaveString() + System.lineSeparator());
        }

        fileWriter.close();
    }

}
