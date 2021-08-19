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

public class Storage {
    BufferedWriter writer;
    BufferedReader reader;
    Path path;

    public Storage(String filename) {
        path = FileSystems.getDefault().getPath("data", filename);
        File directory = new File("data");
        directory.mkdir();
    }

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


    public void save(TaskList tasklist, Ui ui) throws IOException {
        ui.notifySavingBegin();
        writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8);
        for (int i = 0; i < tasklist.size(); i++) {
            writer.write(formatForSave(tasklist.get(i)));
            writer.newLine();
        }
        writer.close();
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
