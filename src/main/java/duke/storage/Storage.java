package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.DukeException;
import duke.TaskList;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;




/**
 * Represents the file used to store tasks.
 */
public class Storage {
    public final String filePath;

    /**
     * Initialises the file.
     *
     * @param filePath the filepath string to be used
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        File f = new File(filePath);
        if (!f.exists()) {
            f.getParentFile().mkdirs();
        }

        assert f.exists() : "filePath should be initiated.";
    }

    /**
     * Loads the TaskList from the storage file to Duke's TaskList and returns it.
     *
     * @return ArrayList containing the tasks saved in the file
     * @throws DukeException if the file cannot be found
     */
    public ArrayList<Task> load() throws DukeException {
        try {
            File f = new File(filePath);
            assert f.exists() : "File to read from should exist.";
            Scanner s = new Scanner(f);
            ArrayList<Task> tasks = new ArrayList<Task>();
            while (s.hasNext()) {
                String item = s.nextLine();
                String[] details = item.split(",");
                boolean isDone = Boolean.parseBoolean(details[1]);
                assert details.length >= 2 : "Task description fields should not be empty.";
                switch (details[0]) {
                case "T":
                    tasks.add(new ToDo(details[2], isDone));
                    break;
                case "D":
                    tasks.add(new Deadline(details[2], details[3], isDone));
                    break;
                case "E":
                    tasks.add(new Event(details[2], details[3], isDone));
                    break;
                default:
                    throw new DukeException("Failed to load file data. File data is in a wrong format.");
                }
            }
            s.close();
            return tasks;
        } catch (FileNotFoundException e) {
            throw new DukeException("File not found.");
        } catch (DukeException e) {
            throw e;
        }

    }

    /**
     * Saves the TaskList data into the storage file.
     *
     * @param tasks the TaskList to be saved into file.
     */
    public void write(TaskList tasks) {
        try {
            File f = new File(filePath);
            assert f.exists() : "File to write to should be valid.";

            FileWriter fw = new FileWriter(filePath);
            for (Task item : tasks.getList()) {
                String toAdd = item.getText();
                fw.write(toAdd);
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
