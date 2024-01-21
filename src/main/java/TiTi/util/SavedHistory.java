package titi.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import titi.task.Deadline;
import titi.task.Event;
import titi.task.Task;
import titi.task.ToDo;


/**
 * Represents interface with saved data history.
 * Loads task from the file when programme starts,
 * and saves task to the file when programme terminates.
 */
public class SavedHistory {

    protected static final File DATA_FOLDER = new File("./src/main/data");
    protected static final File DATA_FILE = new File("./src/main/data/TiTi.txt");
    protected ArrayList<Task> savedTasks = new ArrayList<>();

    /**
     * Initialises a SaveHistory instance.
     * Loads task from data file.
     */
    public SavedHistory() {
        checkFile();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE));
            String line = reader.readLine();
            while (line != null) {
                savedTasks.add(read(line));
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Checks and creates necessary folder and file if needed
     */
    private void checkFile() {
        if (!DATA_FILE.exists()) {
            if (!DATA_FOLDER.exists()) {
                DATA_FOLDER.mkdirs();
            }
            try {
                DATA_FILE.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Interprets a line in stored data
     */
    private Task read(String string) {
        char type = string.charAt(1);
        char status = string.charAt(4);
        Task nextTask;
        String description;
        int lineBreaker;

        switch (type) {
        case 'T':
            description = string.substring(7);
            nextTask = new ToDo(description);
            if (status == 'X') {
                nextTask.complete();
            }
            break;

        case 'D':
            lineBreaker = string.indexOf(" (by: ");
            description = string.substring(7, lineBreaker);
            String by = string.substring(lineBreaker + 6, string.length() - 1);
            nextTask = new Deadline(description, by);
            if (status == 'X') {
                nextTask.complete();
            }
            break;

        case 'E':
            lineBreaker = string.indexOf(" (at: ");
            description = string.substring(7, lineBreaker);
            String at = string.substring(lineBreaker + 6, string.length() - 1);
            nextTask = new Event(description, at);
            if (status == 'X') {
                nextTask.complete();
            }
            break;

        default:
            nextTask = null;
        }

        return nextTask;
    }


    /**
     * Produces the list of tasks loaded from the data file.
     *
     * @return list of tasks
     */
    public ArrayList<Task> readHistory() {
        return savedTasks;
    }


    /**
     * Writes the new list of tasks into data history file.
     *
     * @param newTasks list of tasks to be recorded
     */
    public void saveHistory(TaskList newTasks) {
        try {
            FileWriter fileWriter = new FileWriter(DATA_FILE);
            String tasks = "";
            for (int i = 0; i < newTasks.size(); i++) {
                tasks += newTasks.get(i) + System.lineSeparator();
            }
            fileWriter.write(tasks);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
