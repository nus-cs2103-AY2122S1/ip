package aoi.storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import aoi.data.TaskList;
import aoi.parser.Parser;
import aoi.task.Task;

/**
 * Encapsulates a Storage object that handles loading and saving of Tasks.
 *
 * @author Owen Tan
 * @version aoi.Aoi Level-9
 */
public class Storage {
    private String path;
    private TaskList tasks;

    /**
     * Public constructor of Storage.
     *
     * @param path Path of text file stored.
     * @param tasks Associated TaskList.
     */
    public Storage(String path, TaskList tasks) {
        this.path = path;
        this.tasks = tasks;
    }

    /**
     * Loads the file into Storage and parses Tasks into TaskList.
     */
    public void load() {
        try {
            File fileDir = new File(path).getParentFile();
            File file = new File(path);

            if (!fileDir.exists()) {
                fileDir.mkdirs();
            }

            if (!file.createNewFile()) {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;

                while ((line = reader.readLine()) != null) {
                    tasks.add(Parser.parseTask(line));
                }
                reader.close();
            }
        } catch (IOException e) {
            System.out.println("Something went wrong with loading");
        }
    }

    /**
     * Saves Tasks from TaskList into text file.
     */
    public void save() {
        try {
            FileWriter writer = new FileWriter(path);
            ArrayList<Task> lst = tasks.getList();
            for (Task task: lst) {
                writer.write(task.printInSaveFormat());
                writer.write("\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Something went wrong with saving");
        }
    }

}
