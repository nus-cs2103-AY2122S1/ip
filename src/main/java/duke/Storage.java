package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

/**
 * Reads and writes valid user inputs into the task list to be saved to the hard disk.
 */
public class Storage {
    private static DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy HHmm");

    private String filePath;

    /**
     * Instantiates a storage object.
     *
     * @param filePath Path where the file is stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Reads any previously saved file in the specified file path.
     * Creates an empty file if there is no previously saved file.
     *
     * @return List of task items.
     * @throws IOException If the file in the specified file path cannot be operated on.
     */
    public List<Task> load() throws IOException {

        List<Task> items = new ArrayList<>();
        File f = new File(filePath);
        if (f.exists()) {
            Scanner sc = new Scanner(f);

            while (sc.hasNext()) {
                String t = sc.nextLine();
                String[] task = t.split("\\|");
                assert task.length >= 1 : "Invalid task input.";

                switch (task[0]) {
                case "T":
                    items.add(new Todo(task[2], task[1].equals("1")));
                    break;

                case "E":
                    items.add(new Event(task[2], task[1].equals("1"),
                            LocalDateTime.parse(task[3], outputFormatter)));
                    break;

                case "D":
                    items.add(new Deadline(task[2], task[1].equals("1"),
                            LocalDateTime.parse(task[3], outputFormatter)));
                    break;

                default:
                    assert false : "Invalid input.";
                    break;
                }
            }
        } else {
            f.getParentFile().mkdir();
            boolean isNewFileCreated = f.createNewFile();
            assert isNewFileCreated : "New file not created for storage";
        }

        return items;
    }

    /**
     * Writes the list of tasks to the specified file path.
     *
     * @param tasks List of tasks.
     * @throws IOException If the file in the specified file path cannot be operated on.
     */
    public void save(List<Task> tasks) throws IOException {
        // Solution adapted from:
        // https://stackoverflow.com/questions/9658297/java-how-to-create-a-file-in-a-directory-using-relative-path
        File dukeFile = new File(filePath);

        if (!dukeFile.exists()) {
            dukeFile.getParentFile().mkdir();
            dukeFile.createNewFile();
        }

        FileWriter fw = new FileWriter(dukeFile.getAbsoluteFile());

        for (Task t : tasks) {
            fw.write(t.toSaveString() + System.lineSeparator());
        }

        fw.close();
    }
}
