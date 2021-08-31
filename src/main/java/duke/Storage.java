package duke;

import duke.exception.DukeException;
import duke.exception.LoadingError;
import duke.exception.SavingError;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.time.LocalDate;
import java.time.LocalTime;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Class that handles laoding and saving tasks to a file.
 */
public class Storage {

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
     * Returns an ArrayList<Task> of loaded Tasks from the file.
     *
     * @return ArrayList<Task> of tasks
     * @throws DukeException that indicates what the issue was in opening/parsing the file
     */
    public ArrayList<Task> load() throws DukeException {
        String home = System.getProperty("user.dir");
        Path path = Paths.get(home, this.filepath);
        boolean isDirectoryFound = Files.exists(path);

        if (isDirectoryFound) {
            try {
                List<String> lines = Files.readAllLines(path);
                ArrayList<Task> tasks = new ArrayList<>();
                for (String x: lines) {
                    String[] data = x.split(Pattern.quote(" | "));
                    Task t;
                    if (data.length < 2) {
                        continue;
                    }
                    switch (data[1]) {
                    case "T":
                        t = new ToDo(data[3]);
                        break;
                    case "E":
                        LocalDate eventDate = LocalDate.parse(data[4]);
                        if (data.length == 5) {
                            t = new Event(data[3], eventDate);
                        } else {
                            String hour = data[5].replace(":", "").substring(0, 2);
                            String minute = data[5].replace(":", "").substring(2, 4);
                            LocalTime time = LocalTime.of(Integer.parseInt(hour), Integer.parseInt(minute));
                            t = new Event(data[3], eventDate, time);
                        }
                        break;
                    case "D":
                        LocalDate deadlineDate = LocalDate.parse(data[4]);
                        if (data.length == 5) {
                            t = new Deadline(data[3], deadlineDate);
                        } else {
                            String hour = data[5].replace(":", "").substring(0, 2);
                            String minute = data[5].replace(":", "").substring(2, 4);
                            LocalTime time = LocalTime.of(Integer.parseInt(hour), Integer.parseInt(minute));
                            t = new Deadline(data[3], deadlineDate, time);
                        }
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
        } else {
            throw new LoadingError("File not found :(");
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
            Files.write(Paths.get(home,this.filepath), text.getBytes());
        } catch (IOException e) {
            throw new SavingError("Could not write to file :(");
        }
    }
}
