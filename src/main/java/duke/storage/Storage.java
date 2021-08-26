package duke.storage;

import duke.DukeException;
import duke.TaskList;
import duke.tasks.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

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
            Scanner s = new Scanner(f);
            ArrayList<Task> tasks = new ArrayList<Task>();
            while (s.hasNext()) {
                String item = s.nextLine();
                String[] details = item.split(",");
                boolean isDone = Boolean.parseBoolean(details[1]);
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
                }
            }
            return tasks;
        } catch (FileNotFoundException e) {
            throw new DukeException("File not found.");
        }

    }

    /**
     * Saves the TaskList data into the storage file.
     *
     * @param tasks the TaskList to be saved into file.
     */
    public void write(TaskList tasks) {
        try {
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
