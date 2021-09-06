package duke;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;

/**
 * Storage class handles the reading and writing of data
 * from the local file system.
 */
public class Storage {
    private final DateTimeFormatter DATETIMEFORMAT;
    private File file;

    /**
     * Public constructor to initialise the storage for the Duii Bot.
     */
    public Storage() {
        this.DATETIMEFORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");
    }

    /**
     * Searches the current file system for existing saved files.
     * Creates a new data directory consisting of the text file to write
     * into if saved file does not exist.
     *
     * @return The abstract representation of the pathname of the created file
     */
    public File getFiles() {
        String home = System.getProperty("user.home");
        Path path = Paths.get(home, "ip", "data");
        File directory = new File(path.toString());
        if (!directory.exists()) {
            Ui.notifyDirNotFound();
            boolean directoryCreated = directory.mkdir();
            assert(directoryCreated) : "Error Creating file.";
            Ui.notifyCreatedDir();
        }

        Path filepath = Paths.get(home, "ip", "data", "duke.txt");
        File file = new File(filepath.toString());
        if (!file.exists()) {
            Ui.notifyFileNotFound();
            try {
                boolean fileCreated = file.createNewFile();
                assert(fileCreated) : "Error Creating file.";
                Ui.notifyCreatedFile();
            } catch (IOException e) {
                System.out.println("IO error occurred");
            }
        }
        return file;
    }

    /**
     * Writes the saved tasks from previous sessions (if any) into
     * the local TaskList object.
     *
     * @return An ArrayList initialised with the previously saved tasks.
     * @throws DukeException If an IOException occurs upon reading of file, which is passed on to
     *                       the main() method of Duke class.
     */
    public ArrayList<Task> load() throws DukeException {
        try {
            file = this.getFiles();
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);

            String text;
            ArrayList<Task> saved = new ArrayList<>();

            while ((text = bufferedReader.readLine()) != null) {
                String[] lineArr = text.split(" \\| ");
                switch (lineArr.length) {
                case 3:
                    Task toDo = loadToDo(lineArr);
                    saved.add(toDo);
                    break;
                case 4:
                    if (lineArr[0].equals("D")) {
                        Deadline deadline = loadDeadline(lineArr);
                        saved.add(deadline);
                    } else if (lineArr[0].equals("E")) {
                        Event event = loadEvent(lineArr);
                        saved.add(event);
                    }
                    break;
                default:
                    throw new DukeException("Error loading input file.");
                }
            }
            return saved;
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }

    public Task loadToDo(String[] lineArr) {
        Task toDo = new Task(lineArr[2]);
        if (lineArr[1].equals("1")) {
            toDo.complete();
        }
        return toDo;
    }

    public Deadline loadDeadline(String[] lineArr) {
        Deadline deadline = new Deadline(lineArr[2], LocalDateTime.parse(lineArr[3],
                this.DATETIMEFORMAT));
        if (lineArr[1].equals("1")) {
            deadline.complete();
        }
        return deadline;
    }

    public Event loadEvent(String[] lineArr) {
        Event event = new Event(lineArr[2], LocalDateTime.parse(lineArr[3], this.DATETIMEFORMAT));
        if (lineArr[1].equals("1")) {
            event.complete();
        }
        return event;
    }

    /**
     * Writes the tasks of the current session into the dynamically created file
     * on the local filesystem.
     *
     * @param list The task list to be written into the local file system.
     * @throws DukeException If an IOException occurs during writing of file, which
     *                       is passed on to the main() method of Duke class.
     */
    public void save(TaskList list) throws DukeException {
        try {
            FileWriter writer = new FileWriter(file, false);
            for (Task item : list.getAllTasks()) {
                writer.write(item.getSaveInfo() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }
}
