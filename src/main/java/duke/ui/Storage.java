package duke.ui;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.BufferedReader;
import java.nio.file.Paths;

public class Storage { //Storage class used to handle the writing of the file after each actions
    private static Path filePath;
    private static Path txtPath;

    /**
     * Constructor for Storage Class
     * @param path the filepath where the txt file will be/is created
     */
    public Storage(String path) {
        filePath = Paths.get(path);
        if (!Files.exists(filePath)) {
            new File(filePath.toString()).mkdirs();
        }
        txtPath = Paths.get(path, "task.txt");
    }

    /**
     * Function to read the existing txt file if a previous one was already made
     *
     * @return the BufferedReader to read the file
     * @throws FileNotFoundException Handles if the files was not there previously, meaning need make new file
     */
    public BufferedReader load() throws FileNotFoundException {
        return new BufferedReader(new FileReader(txtPath.toString()));
    }

    /**
     * function to update the text each time after any new action is done
     *
     * @param listArray The list of tasks that the bot is using
     * @throws IOException handles if the filepath/file has issues
     */

    public static void updateText(TaskList listArray) throws IOException {
        BufferedWriter botList = new BufferedWriter(
                new FileWriter(txtPath.toString()));
        for (int i = 0; i < listArray.getCount(); i++) {
            botList.write(listArray.get(i).write() + "\n");
        }
        botList.close();
    }
}
