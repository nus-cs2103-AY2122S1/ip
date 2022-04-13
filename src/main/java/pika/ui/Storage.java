package pika.ui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import pika.task.TaskList;

public class Storage { //Storage class used to handle the writing of the file after each actions
    private static Path listPath;

    /**
     * Constructor for Storage Class.
     *
     * @param path the filepath where the txt file will be/is created
     */
    public Storage(String path) {
        assert path != null : "Pika Pi, this is not valid!";
        Path filePath = Paths.get(path);
        if (!Files.exists(filePath)) {
            new File(filePath.toString()).mkdirs();
        }
        listPath = Paths.get(path, "task.txt");
    }

    /**
     * Reads the existing txt file if a previous one was already made.
     *
     * @return the BufferedReader to read the file
     * @throws FileNotFoundException Handles if the files was not there previously, meaning need make new file
     */

    public BufferedReader loadTasks() throws FileNotFoundException {
        return new BufferedReader(new FileReader(listPath.toString()));
    }

    /**
     * Updates the text each time after any new action is done.
     *
     * @param tasks The list of tasks that the bot is using
     * @throws IOException handles if the filepath/file has issues
     */

    public static void updateText(TaskList tasks) throws IOException {
        BufferedWriter botList = new BufferedWriter(
                new FileWriter(listPath.toString()));
        for (int i = 0; i < tasks.getCount(); i++) {
            botList.write(tasks.get(i).write() + "\n");
        }
        botList.close();
    }
}
