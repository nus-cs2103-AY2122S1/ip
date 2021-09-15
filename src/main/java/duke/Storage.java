package duke;

import javafx.util.Pair;

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
    private String dataFilePath;
    private ArrayList<HistoryState> history = new ArrayList<HistoryState>();
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
     * Set task list and command to history
     * @param taskList
     */
    public void addToHistory(TaskList taskList, Command command) {
        history.add(new HistoryState(taskList, command));
    }

    public boolean isHistoryEmpty() {
        return history == null;
    }
    /**
     * Returns history and clears it.
     * @return last saved task list
     * @throws DukeException
     */
    public HistoryState popFromHistory() throws DukeException {
        if (history.isEmpty()) {
            throw new DukeException("There is nothing saved in the history!");
        }
        HistoryState lastHistoryState = history.remove(history.size() - 1);
        return lastHistoryState;
    }

    /**
     * Loads data from the data file, if any.
     *
     * @return ArrayList of tasks loaded from the data file.
     * @throws DukeException If IOException is thrown while creating a new datafile, data file is not found
     * or there exists an invalid file type in the data file.
     */
    public ArrayList<Task> load() throws DukeException {
        File f = getDataFile();
        assert f.exists() : "Data File should be present";
        Scanner s = getScanner(f);
        ArrayList<Task> tasks = getStoredTasks(s);
        return tasks;
    }

    private ArrayList<Task> getStoredTasks(Scanner s) {
        ArrayList<Task> tasks = new ArrayList<>();
        int count = 0;
        while (s.hasNext()) {
            count++;
            String l = s.nextLine();
            String[] taskEntry = l.split("\\|");
            switch(taskEntry[0]) {
            case "T":
                tasks.add(new Todo(getTaskDescription(taskEntry)));
                break;
            case "D":
                tasks.add(new Deadline(getTaskDescription(taskEntry), getTaskDate(taskEntry)));
                break;
            case "E":
                tasks.add(new Event(getTaskDescription(taskEntry), getTaskDate(taskEntry)));
                break;
            default:
                throw new DukeException("Invalid duke. Task Type stored in Data File");
            }
            boolean isTaskDone = taskEntry[1].equals("X");
            if (isTaskDone) {
                tasks.get(tasks.size() - 1).markAsDone();
            }
        }
        assert count == tasks.size() : "Should have the same number of tasks as lines in data file";
        return tasks;
    }

    private String getTaskDescription(String[] taskEntry) {
        return taskEntry[2];
    }

    private LocalDate getTaskDate(String[] taskEntry) {
        return LocalDate.parse(taskEntry[3]);
    }

    private Scanner getScanner(File f) {
        Scanner s;
        try {
            s = new Scanner(f);
        } catch (FileNotFoundException e) {
            throw new DukeException(e.getMessage());
        }
        return s;
    }

    private File getDataFile() throws DukeException {
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
        return f;
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
