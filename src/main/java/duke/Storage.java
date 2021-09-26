package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Class that deals with loading files and storing files
 */
public class Storage {
    String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Save the given list of task to a txt file.
     *
     * @param tasks TaskList of task to save.
     */
    public void save (TaskList tasks) {
        try {
            // Check for valid directory
            File f = new File(filePath);
            if (!f.getParentFile().exists()) {
                f.getParentFile().mkdirs();
            }

            FileWriter fw = new FileWriter("data/save.txt");
            fw.write(tasks.saveString() + "\n");
            fw.close();
        } catch (IOException err) {
            System.out.println(err.getMessage());
        }

    }

    /**
     * Load the task from a save file and returns TaskList.
     * Returns empty Tasklist if save file does not exist.
     *
     * @return TaskList of loaded save file
     * @throws DukeException Error with load.
     */
    public TaskList load() throws DukeException {
        TaskList tasks = new TaskList();
        try {
            File f = new File(filePath);
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String nextLine = s.nextLine();
                String[] nextItem = nextLine.split("\\Q|\\E", 0);
                switch (nextItem[0]) {
                case "T":
                    tasks.addTask(new ToDo(nextItem[2].trim(), nextItem[1].trim().equals("1")));
                    break;
                case "D":
                    tasks.addTask(
                            new Deadline(
                                    nextItem[2].trim(),
                                    nextItem[1].trim().equals("1"),
                                    LocalDate.parse(nextItem[3].trim())
                            )
                    );
                    break;
                case "E":
                    tasks.addTask(new Event(nextItem[2].trim(), nextItem[1].trim().equals("1"), nextItem[3].trim()));
                    break;
                default:
                    throw new DukeException("Unrecognized save file content");
                }
            }
            s.close();

            return tasks;
        } catch (FileNotFoundException e) {
            throw new DukeException("No save file found.");
        }
    }
}
