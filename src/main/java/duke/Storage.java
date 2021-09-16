package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private final File file;

    /**
     * Constructs a Storage instance.
     *
     * @param pathToFile Path to .txt file used for storage.
     */
    public Storage(String pathToFile) {
        file = new File(pathToFile);
        try {
            createDirectory(file.getParentFile());
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("unable to create file");
            e.printStackTrace();
        }
        assert file.exists();
        assert file.isFile();
    }

    private void createDirectory(File file) {
        if (file.exists()) {
            return;
        } else {
            createDirectory(file.getParentFile());
            file.mkdir();
        }
    }

    /**
     * Retrieves Tasks stored in text file.
     * Adds Tasks to task list.
     *
     * @param tasklist Task list to store retrieved Tasks.
     */
    public void retrieveTasks(TaskList tasklist) {
        try {
            ArrayList<String> lines = new ArrayList<>();
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                lines.add(data);
            }
            myReader.close();
            lines.forEach(s -> tasklist.add(s));
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Saves Tasks in task list to test file.
     *
     * @param tasklist Task list containing Tasks to store.
     */
    public void saveToFile(TaskList tasklist) {
        try {
            String txt = tasklist.saveTasklist();
            FileWriter myWriter = new FileWriter(file);
            myWriter.write(txt);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("Unable to write to file");
        }
    }

}
