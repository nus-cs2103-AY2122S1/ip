package duke.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    /** File to read and write saved data */
    private File f;
    /** Scanner to read saved data from file */
    private Scanner s;
    /** Intermediate data structure to modify saved data */
    private ArrayList<String> taskListString;

    /**
     * Returns a new Storage object initialized with data from the file in filePath.
     *
     * @param filePath Path to the file containing storage data.
     */
    public Storage (String filePath) {
        this.f = new File(filePath);
        this.taskListString = new ArrayList<>();
    }

    /**
     * Returns an ArrayList of String arrays, where each array specifies a single stored Task.
     *
     * @return ArrayList of String arrays representing Tasks.
     * @throws FileNotFoundException  If file specified in filePath does not exists.
     */
    public ArrayList<String[]> load() throws FileNotFoundException {
        ArrayList<String[]> taskList = new ArrayList<>();
        s = new Scanner(f);
        while (s.hasNext()) {
            String line = s.nextLine();
            taskListString.add(line);
            taskList.add(line.split("[|]"));
        }
        return taskList;
    }

    /**
     * Saves current tasks from the intermediate data structure to file specified in filePath.
     *
     * @throws IOException  If file specified in filePath cannot be written to.
     */
    public void save() throws IOException {
        FileWriter fw = new FileWriter(f);
        for (int i = 0; i < this.taskListString.size() - 1; i++) {
            fw.write(this.taskListString.get(i) + "\n");
        }
        fw.write(this.taskListString.get(taskListString.size() - 1));
        fw.close();
    }

    /**
     * Saves non-timed task to the intermediate data structure.
     *
     * @param type Type of task to be saved.
     * @param status Status of task to be saved.
     * @param description Description of task to be saved.
     */
    public void saveTask(String type, String status, String description) {
        taskListString.add(type + "|" + status + "|" + description);
    }

    /**
     * Saves timed task to the intermediate data structure.
     *
     * @param type Type of task to be saved.
     * @param status Status of task to be saved.
     * @param description Description of task to be saved.
     * @param timeframe Time limit of task to be saved
     */
    public void saveTask(String type, String status, String description, LocalDateTime timeframe) {
        taskListString.add(type + "|" + status + "|" + description + "|"
                + timeframe.format(DateTimeFormatter.ISO_DATE_TIME));
    }

    /**
     * Modifies the status of task in intermediate data structure to done.
     *
     * @param index Index of task to be modified.
     */
    public void saveTaskDone(int index) {
        String data = taskListString.get(index - 1);
        taskListString.set(index - 1, data.substring(0, 2) + 'X' + data.substring(3));
    }

    /**
     * Removes task from intermediate data structure.
     *
     * @param index Index of task to be removed.
     */
    public void removeTask(int index) {
        taskListString.remove(index);
    }
}
