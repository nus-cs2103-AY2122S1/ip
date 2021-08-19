package duke;

import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.File;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.time.LocalDate;
import duke.task.*;

/**
 * Class responsible for saving and loading the data at the start and end of application runs.
 */
public class Storage {
    BufferedWriter writer;
    BufferedReader reader;
    Path path;

    /**
     * When a Storage object is initialised, it will create the data directory in the source directory.
     * Currently filename represents where the data is to be saved, and it is hard-coded in Duke.java.
     * @param filename is the file the application will write to, in the data folder.
     */
    public Storage (String filename, Ui ui) {
        path = FileSystems.getDefault().getPath("data", filename);
        File directory = new File("data");
        if (directory.mkdir()) {
            ui.notifyFolderCreated();
        } else {
            ui.notifyFolderFound();
        }
    }

    /**
     * Load locates the saved data if it exists, and parses it to create a TaskList representing the saved sessions
     * data. Uses a Ui object to print notices informing a user if errors occur, when the load begins, and when it ends.
     * @param ui to print notices to user
     * @return a list of tasks saved from previous session.
     * @throws IOException if initialising the reader fails, or reading from the save data causes an error.
     * @throws DukeException if the saved data has a format that is not recognised.
     */
    public ArrayList<Task> load(Ui ui) throws IOException, DukeException {
        ArrayList<Task> loadedData = new ArrayList<>();
        reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
        String data = reader.readLine();
        if (data == null) {
            return loadedData;
        } else {
            ui.notifyLoadingBegin();
            while(data != null) {
                loadedData.add(formatForLoad(data));
                data = reader.readLine();
            }
            ui.notifyLoadingComplete();
        }
        return loadedData;
    }

    /**
     * Given a TaskList from the current session and a Ui object, method attempts to save the session in a text file.
     * Method prints notices to the user for when the saving begins and ends.
     * @param tasklist contains the tasks from the current session.
     * @param ui Ui object to print notices to the user.
     * @throws IOException if writing to the text file fails, or initialising the writer fails.
     */
    public void save(TaskList tasklist, Ui ui) throws IOException {
        ui.notifySavingBegin();
        writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8);
        for (int i = 0; i < tasklist.size(); i++) {
            writer.write(formatForSave(tasklist.get(i)));
            writer.newLine();
        }
        writer.close();
        ui.notifySavingComplete();
    }

    //converts a Task into a string to be saved to a txt file.
    private String formatForSave(Task e) {
        String entry = "";
        entry = entry + e.getType() + "|";
        entry = entry + e.getFlag() + "|";
        entry = entry + e.getLabel();
        if (e.getType().equals("E") || e.getType().equals("D")) {
            entry = entry + "|" + e.getDate();
        }
        return entry;
    }

    //dual of formatForSave. Converts from saved string back to Task.
    private Task formatForLoad(String s) throws DukeException {
        String[] arr = s.split("[|]");
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
            t.setFlag(true);
        }
        return t;
    }
}
