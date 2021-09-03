package duke;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import duke.exception.DukeException;
import duke.exception.LoadingError;
import duke.exception.SavingError;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

/**
 * Class that handles laoding and saving tasks to a file.
 */
public class Storage {

    private enum Type {
        EVENT,
        DEADLINE
    }

    private final String filepath;

    /**
     * Storage constructor.
     *
     * @param filepath The filepath of the task list from the project root
     */
    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Returns an ArrayList of loaded Tasks from the file.
     *
     * @return ArrayList of Tasks
     * @throws DukeException that indicates what the issue was in opening/parsing the file
     */
    public ArrayList<Task> load() throws DukeException {
        String home = System.getProperty("user.dir");
        Path path = Paths.get(home, this.filepath);
        boolean isDirectoryFound = Files.exists(path);

        if (!isDirectoryFound) { // Guard clause
            throw new LoadingError("File not found :(");
        }

        try {
            List<String> lines = Files.readAllLines(path);
            ArrayList<Task> tasks = new ArrayList<>();
            for (String line: lines) {
                String[] data = line.split(Pattern.quote(" | "));
                Task t;

                if (data.length < 2) { // Guard Clause
                    continue;
                }

                switch (data[1]) {
                case "T":
                    t = new ToDo(data[3]);
                    break;
                case "E":
                    t = parseDateTime(data, Type.EVENT);
                    break;
                case "D":
                    t = parseDateTime(data, Type.DEADLINE);
                    break;
                default:
                    // Invalid task found when loading, skipped!
                    continue;
                }

                if (data[2].equals("1")) {
                    t.markAsDone();
                }

                tasks.add(t);
            }
            return tasks;
        } catch (IOException e) {
            throw new LoadingError("Couldn't load file :(");
        }
    }

    private static Task parseDateTime(String[] data, Type type) {
        LocalDate date = LocalDate.parse(data[4]);
        if (data.length == 5) {
            if (type == Type.EVENT) {
                return new Event(data[3], date);
            } else {
                return new Deadline(data[3], date);
            }
        } else {
            String hour = data[5].replace(":", "").substring(0, 2);
            String minute = data[5].replace(":", "").substring(2, 4);
            LocalTime time = LocalTime.of(Integer.parseInt(hour), Integer.parseInt(minute));
            if (type == Type.EVENT) {
                return new Event(data[3], date, time);
            } else {
                return new Deadline(data[3], date, time);
            }
        }
    }

    /**
     * Saves the provided TaskList in an appropriate format.
     *
     * @param taskList TaskList which is to be saved.
     */
    public void save(TaskList taskList) throws DukeException {
        String home = System.getProperty("user.dir");
        String text = taskList.stringifyTasksForSave();
        Path path = Paths.get(home, this.filepath);
        boolean isDirectoryFound = Files.exists(path);

        if (!isDirectoryFound) {
            try {
                Files.createDirectories(path.getParent());
            } catch (IOException e) {
                throw new SavingError("Could not create directory path :(");
            }
        }

        try {
            Files.write(Paths.get(home, this.filepath), text.getBytes());
        } catch (IOException e) {
            throw new SavingError("Could not write to file :(");
        }
    }
}
