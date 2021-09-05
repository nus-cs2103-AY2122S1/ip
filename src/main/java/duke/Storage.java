package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * A Storage class that handles the loading and saving of list items in the program to the
 * required data.txt file.
 */
public class Storage {
    static final String COMPLETE_TAG = "1";
    static final String INCOMPLETE_TAG = "0";
    private final String filePath;
    /**
     * Class constructor specifying the file path for data.txt file.
     *
     * @param filePath the file path for data.txt file given as a string.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Method to initialize the Done state of the task.
     *
     * @param t The task to be initialized.
     * @param s The initialization value. 0 is not done. 1 is done.
     */
    private void initializeDone(Task t, String s) {
        assert(s.equals("1") || s.equals("0"));
        if (s.equals(COMPLETE_TAG)) {
            t.setDone();
        }
    }

    /**
     * Method to load the list items in data.txt file into the application.
     *
     * @return An ArrayList of type Task containing all the tasks saved in data.txt.
     * @throws DukeException In the event that there are issues with the loading of the file.
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> taskArr = new ArrayList<>();
        File file = new File(filePath);
        if (!file.isDirectory()) {
            Path path = Paths.get(filePath);
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                System.out.println("Failed to create directory: " + e.getMessage());
            }
        }
        try {
            File fCurr = new File(filePath);
            Scanner reader = new Scanner(fCurr);
            while (reader.hasNextLine()) {
                String taskString = reader.nextLine();
                String[] strArr = taskString.split(" \\| ", 4);
                Task t;
                switch (strArr[0]) {
                case "T":
                    t = new ToDo(strArr[2]);
                    break;
                case "E":
                    t = new Event(strArr[2], strArr[3]);
                    break;
                case "D":
                    t = new Deadline(strArr[2], strArr[3]);
                    break;
                default:
                    throw new DukeException("initialization error");
                }
                initializeDone(t, strArr[1]);
                taskArr.add(t);
            }
        } catch (FileNotFoundException e) {
            throw new DukeException("file not found");
        }

        return taskArr;
    }

    /**
     * Method to save the current list of tasks into the data.txt file.
     *
     * @param t The list of tasks to be saved into the data.txt file.
     */
    public void save(TaskList t) {
        assert(t != null);
        try {
            File fOld = new File(filePath);
            fOld.delete();
            File fNew = new File(filePath);
            fNew.createNewFile();
            FileWriter myWriter = new FileWriter(filePath);
            String savedString = "";
            for (int i = 0; i < t.getSize(); i++) {
                Task task = t.get(i);
                String completionState = task.isDone() ? COMPLETE_TAG : INCOMPLETE_TAG;
                savedString += task.getTag() + " | " + completionState + " | "
                        + task.getTaskName() + " | " + task.getAdditionalInfo() + "\n";
            }
            myWriter.write(savedString);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("File creation error: " + e.getMessage());
        }
    }
}
