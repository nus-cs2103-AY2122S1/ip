package storage;

import java.io.*;
import java.util.ArrayList;
import duke.DukeException;
import tasks.DeadLineTask;
import tasks.EventTask;
import tasks.Task;
import tasks.ToDoTask;
import ui.Ui;

/**
 * The Storage Class is responsible for reading and loading
 * of data stored locally on the computer.
 */

public final class Storage{
    private final static String FILE_PATH = "./data/duke.txt";

    /**
     * Checks if folder and text files for where the information
     * related to the Duke assistant already exists. If not,
     * create them.
     *
     * @throws DukeException if there were unexpected errors
     * while creating or checking files
     */
    public void createFiles() throws DukeException {
        File folder = new File("./data");
        File saves = new File(FILE_PATH);
        try {
            if (!folder.exists() || !folder.isDirectory()) {
              boolean isFolderCreated = folder.mkdir();
            }
            if (!saves.exists()) {
              boolean isSavesCreated = saves.createNewFile();
            }
        } catch (IOException e) {
            Ui.showInput(e.getMessage());
            throw new DukeException("Unexpected issue encountered");
        }
    }

    /**
     * Saves the tasks to specified folder and file.
     *
     * @param currTasks the list of tasks to be saved
     */
    public void resetFile(ArrayList<Task> currTasks) {
        File file = new File(FILE_PATH);
        if (!file.delete()) {
            Ui.showInput("something went wrong");
        }
        try {
            boolean isFileCreated = file.createNewFile();
            FileWriter writer = new FileWriter(FILE_PATH, true);
            for (Task t : currTasks) {
                writer.write(t.getSaveFormat() + "\n");
                writer.flush();
            }
            writer.close();
        } catch (IOException e) {
            Ui.showInput("Unable to write, something went wrong");
        }
    }

    /**
     * Loads the previously saved list of tasks.
     *
     * @return the stored list of tasks
     */
    public ArrayList<Task> loadSaves() {
        ArrayList<Task> tasksLoaded = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
            String line = reader.readLine();
            while (line != null) {
                String[] words = line.split("\\|");
                if (words[0].equals("T")) {
                    ToDoTask t = new ToDoTask(words[1]);
                    if (words[words.length - 1].equals("1")) {
                        t.markAsDone();
                    }
                    tasksLoaded.add(t);
                }
                if (words[0].equals("D")) {
                    DeadLineTask d = new DeadLineTask(words[1], words[2]);
                    if (words[words.length - 1].equals("1")) {
                        d.markAsDone();
                    }
                    tasksLoaded.add(d);
                }
                if (words[0].equals("E")) {
                    EventTask e = new EventTask(words[1], words[2]);
                    if (words[words.length - 1].equals("1")) {
                        e.markAsDone();
                    }
                    tasksLoaded.add(e);
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (FileNotFoundException e) {
            Ui.showInput(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tasksLoaded;
    }
}
