package duke;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Class responsible for saving and loading the data at the start and end of application runs.
 */
public class Storage {
    private BufferedWriter writer;
    private BufferedReader reader;
    private Path path;

    /**
     * Initialises a storage object, that will create a data directory in the source directory if it does not exist.
     *
     * Currently fileName represents where the data is to be saved, and it is hard-coded in Duke.java.
     * @param fileName is the file the application will write to, in the data folder.
     */
    public Storage (String fileName, Ui ui) {
        path = FileSystems.getDefault().getPath("data", fileName);
        File directory = new File("data");
        if (directory.mkdir()) {
            ui.notifyFolderCreated();
        } else {
            ui.notifyFolderFound();
        }
    }

    /**
     * Loads located saved data if it exists, and parses it to create a TaskList representing the saved sessions data.
     *
     * @return a list of tasks saved from previous session.
     * @throws IOException if initialising the reader fails, or reading from the save data causes an error.
     * @throws DukeException if the saved data has a format that is not recognised.
     */
    public ArrayList<Task> load() throws IOException, DukeException {
        ArrayList<Task> loadedData = new ArrayList<>();
        reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
        String data = reader.readLine();
        if (data == null) {
            return loadedData;
        } else {
            while (data != null) {
                loadedData.add(formatForLoad(data));
                data = reader.readLine();
            }
        }
        return loadedData;
    }

    /**
     * Saves the tasklist from the current session in a text file in the data directory.
     *
     * @param tasklist contains the tasks from the current session.
     * @throws IOException if writing to the text file fails, or initialising the writer fails.
     */
    public void save(TaskList tasklist) throws IOException {
        writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8);
        for (int i = 0; i < tasklist.size(); i++) {
            writer.write(formatForSave(tasklist.get(i)));
            writer.newLine();
        }
        writer.close();
    }

    //converts a Task Object into a string to be saved to a txt file.
    private String formatForSave(Task task) {
        String entry = "";
        entry = entry + task.getType() + "|";
        entry = entry + task.getIsDone() + "|";
        entry = entry + task.getLabel();
        if (task.getType().equals("E") || task.getType().equals("D")) {
            entry = entry + "|" + task.getDate();
        }
        return entry;
    }

    //dual of formatForSave. Converts from saved string back to Task.
    private Task formatForLoad(String savedData) throws DukeException {
        String[] arr = savedData.split("[|]");
        Task t;
        if (arr[0].equals("T")) {
            t = new Todo(arr[2]);
        } else if (arr[0].equals("D")) {
            t = new Deadline(arr[2], LocalDate.parse(arr[3]));
        } else if (arr[0].equals("E")) {
            t = new Event(arr[2], LocalDate.parse(arr[3]));
        } else {
            throw new DukeException("format of saved data is incorrect");
        }

        if (Boolean.parseBoolean(arr[1])) {
            t.setIsDone(true);
        }
        return t;
    }
}
