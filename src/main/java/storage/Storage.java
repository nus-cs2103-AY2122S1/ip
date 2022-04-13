package storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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

public final class Storage {
    private static final String DATA_PATH = "./data/duke.txt";

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
        File saves = new File(DATA_PATH);
        try {
            if (!folder.exists() || !folder.isDirectory()) {
                folder.mkdir();
            }
            if (!saves.exists()) {
                saves.createNewFile();
            }
        } catch (IOException e) {
            new Ui().showInput(e.getMessage());
            throw new DukeException("Unexpected issue encountered");
        }
    }

    /**
     * Saves the tasks to specified folder and file.
     *
     * @param tasks the list of tasks to be saved
     */
    public void resetFile(ArrayList<Task> tasks) {
        File file = new File(DATA_PATH);
        boolean deleted = file.delete();
        if (deleted) {
            try {
                file.createNewFile();
                FileWriter writer = new FileWriter(DATA_PATH, true);
                for (Task t : tasks) {
                    writer.write(t.getSaveFormat() + "\n");
                    writer.flush();
                }
                writer.close();
            } catch (IOException e) {
                new Ui().showInput("Unable to write, something went wrong");
            }
        } else {
            new Ui().showInput("Something went wrong... could not overwrite");
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
            BufferedReader reader = new BufferedReader(new FileReader(DATA_PATH));
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
            new Ui().showInput(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tasksLoaded;
    }
}
