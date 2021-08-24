package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {

    private String filePath;

    /**
     * initialises the storage, which creates the necessary files
     * @param filePath
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        try {
            int lastSlash = filePath.lastIndexOf("/");
            File myDirectory = new File(filePath.substring(0, lastSlash) + "/");
            if (!myDirectory.exists()) {
                myDirectory.mkdir();
            }

            File myFile = new File(filePath);
            myFile.createNewFile();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * saves the output param into the file initialised by storage
     * @param output
     */
    public void saveTasks(String output) {
        try {
            FileWriter myWriter = new FileWriter(filePath);
            myWriter.write(output);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
