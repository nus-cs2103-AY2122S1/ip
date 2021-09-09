package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the storage of the Duke application that deals with loading from and updating the tasks in the data file.
 */
public class Storage {
    private String dataFolderPath;
    private String dataFilePath;

    /**
     * Constructor for Storage.
     * Sets the data folder and file path.
     *
     * @param dataFilePath Path to the data file.
     */
    public Storage(String dataFilePath) {
        this.dataFilePath = dataFilePath;
    }

    /**
     * Loads data from the data file, if any.
     *
     * @return ArrayList of tasks loaded from the data file.
     * @throws DukeException If IOException is thrown while creating a new datafile, data file is not found
     * or there exists an invalid file type in the data file.
     */
    public ArrayList<Task> load() throws DukeException {
        File f;
        try {
            f = new File(dataFilePath);
            if (!f.exists()) {
                File directory = new File(f.getParent());
                directory.mkdir();
                f.createNewFile();
            }
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
        assert f.exists() : "Data File should be present";
        Scanner s;
        try {
            s = new Scanner(f); // create a Scanner using the File as the source
        } catch (FileNotFoundException e) {
            throw new DukeException(e.getMessage());
        }
        ArrayList<Task> tasks = new ArrayList<>();
        int count = 0;
        while (s.hasNext()) {
            count++;
            String l = s.nextLine();
            String[] taskEntry = l.split("\\|");
            switch(taskEntry[0]) {
            case "T":
                tasks.add(new Todo(taskEntry[2]));
                break;
            case "D":
                tasks.add(new Deadline(taskEntry[2], LocalDate.parse(taskEntry[3])));
                break;
            case "E":
                tasks.add(new Event(taskEntry[2], LocalDate.parse(taskEntry[3])));
                break;
            default:
                throw new DukeException("Invalid duke. Task Type stored in Data File");
            }
            if (taskEntry[1].equals("X")) {
                tasks.get(tasks.size() - 1).markAsDone();
            }
        }
        assert count == tasks.size() : "Should have the same number of tasks as lines in data file";
        return tasks;
    }

    /**
     * Updates the data file with the updated task list.
     *
     * @param tasks Updated task list.
     * @throws DukeException If there is an IOException while writing to the data file.
     */
    public void updateTasks(TaskList tasks) throws DukeException {
        try {
            FileWriter fw = new FileWriter(dataFilePath);
            for (int i = 0; i < tasks.getLength(); i++) {
                fw.write(tasks.getTask(i).toStringData() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Error writing data file:" + e.getMessage());
        }
    }
}
