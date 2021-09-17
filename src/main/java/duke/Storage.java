package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
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
     * @throws DukeDataException If IOException is thrown while creating a new datafile, data file is not found
     * or there exists an invalid file type in the data file.
     */
    public ArrayList<Task> load() throws DukeDataException {
        File f = getDataFile();
        assert f.exists() : "Data File should be present";
        Scanner s = getScanner(f);
        ArrayList<Task> tasks = getStoredTasks(s);
        return tasks;
    }

    private ArrayList<Task> getStoredTasks(Scanner s) throws DukeDataException {
        String divider = "\\|";
        String taskDoneStatus = "X";
        int minEntryLength = 2;

        ArrayList<Task> tasks = new ArrayList<>();
        int count = 0;
        while (s.hasNext()) {
            count++;
            String l = s.nextLine();
            String[] taskEntry = l.split(divider);
            if (taskEntry.length < minEntryLength) {

            }
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
                throw new DukeDataException("Invalid Task Type stored in Data File");
            }
            boolean isTaskDone = taskEntry[1].equals(taskDoneStatus);
            if (isTaskDone) {
                tasks.get(tasks.size() - 1).markAsDone();
            }
        }
        assert count == tasks.size() : "Should have the same number of tasks as lines in data file";
        return tasks;
    }

    private String getTaskDescription(String[] taskEntry) throws DukeDataException {
        int descriptionIndex = 2;
        if (taskEntry.length < descriptionIndex + 1) {
            throw new DukeDataException("No description for task.");
        }
        return taskEntry[descriptionIndex];
    }

    private LocalDate getTaskDate(String[] taskEntry) throws DukeDataException {
        int dateIndex = 3;
        if (taskEntry.length < dateIndex + 1) {
            throw new DukeDataException("No date provided.");
        }
        try {
            return LocalDate.parse(taskEntry[dateIndex]);
        } catch (DateTimeParseException e) {
            throw new DukeDataException("Invalid date provided.");
        }
    }

    private Scanner getScanner(File f) {
        Scanner s;
        try {
            s = new Scanner(f);
        } catch (FileNotFoundException e) {
            throw new DukeDataException(e.getMessage());
        }
        return s;
    }

    private File getDataFile() throws DukeDataException {
        File f;
        try {
            f = new File(dataFilePath);
            if (!f.exists()) {
                File directory = new File(f.getParent());
                directory.mkdir();
                f.createNewFile();
            }
        } catch (IOException e) {
            throw new DukeDataException(e.getMessage());
        }
        return f;
    }

    /**
     * Updates the data file with the updated task list.
     *
     * @param tasks Updated task list.
     * @throws DukeDataException If there is an IOException while writing to the data file.
     */
    public void updateTasks(TaskList tasks) throws DukeDataException {
        try {
            FileWriter fw = new FileWriter(dataFilePath);
            for (int i = 0; i < tasks.getLength(); i++) {
                fw.write(tasks.getTask(i).toStringData() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeDataException("Error writing data file:" + e.getMessage());
        }
    }
}
