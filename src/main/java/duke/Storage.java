package duke;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

// Duke Exceptions
import duke.exception.DukeException;
// Duke Tasks
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

/**
 * Reads and writes the task list on the disk.
 */
public class Storage {
    private static final String DONE_FLAG = "1";
    private static final String STRING_SEPERATOR = "\\|";
    private String path;

    /**
     * Sole constructor for invocation by Duke.
     *
     * @param path Path to the log file to read from or write to.
     */
    public Storage(String path) {
        assert path != null;

        this.path = path;
    }

    /**
     * Reads the file to a TaskList.
     *
     * @return TaskList of the tasks stored in the file.
     */
    protected TaskList load() {
        TaskList tasks = new TaskList();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(this.path));
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                String[] arr;
                boolean isDone;

                arr = line.split(STRING_SEPERATOR);
                isDone = arr[1].equals(DONE_FLAG);

                switch (arr[0]) {
                case "T":
                    tasks.add(new ToDo(isDone, arr[2]));
                    break;
                case "E":
                    tasks.add(new Event(isDone, arr[2], LocalDate.parse(arr[3])));
                    break;
                case "D":
                    tasks.add(new Deadline(isDone, arr[2], LocalDate.parse(arr[3])));
                    break;
                default:
                    throw new DukeException("Unrecognized task flag");
                }
            }
        } catch (IOException e) {
            System.out.println(e.toString());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        return tasks;
    }

    /**
     * Writes the input TaskList to the specified file.
     *
     * @param tasks TaskList to be written to file.
     */
    public void saveFile(TaskList tasks) {
        try {
            File f = new File(this.path);
            if (!f.exists()) {
                f.getParentFile().mkdirs();
                f.createNewFile();
            }

            FileWriter fileWriter = new FileWriter(this.path);
            fileWriter.write(tasks.toString());
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
