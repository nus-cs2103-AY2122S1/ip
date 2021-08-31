package cs2103.duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class encapsulates a Storage object which saves the tasks that have been added by the user, it also
 * performs the initialization for the duke.txt and its parent folder, if they are not already present.
 */
public class Storage {
    private static ArrayList<Task> taskArrayList = new ArrayList<>();
    private final String filePath;

    public Storage(String dukeFilePath) {
        this.filePath = dukeFilePath;
    }

    /**
     * Checks the dukeFilePath for the existence of duke.txt, if the file or the folder containing it
     * does not exist, create them.
     *
     * @throws IOException If an invalid input is detected.
     */
    public void initialize() throws IOException {
        String dukeFileDirectoryPath = "./data";
        String dukeFilePath = "./data/duke.txt";
        File dukeFileDirectory = new File(dukeFileDirectoryPath);
        File dukeFile = new File(dukeFilePath);
        // creates directory if it does not exist
        if (dukeFileDirectory.mkdir()) {
            // dukeFile.mkdir();
            System.out.println("folder: 'data/' has been created");
        }
        // creates file if it does not exist
        if (dukeFile.createNewFile()) {
            // dukeFile.createNewFile();
            System.out.println("'duke.txt' has been created in the 'data/' folder ");
        }
    }

    /**
     * Loads the data stored in duke.txt into a taskArrayList.
     *
     * @return The taskArrayList representation of the data stored in duke.txt, if any.
     * @throws IOException If an invalid input is detected.
     */
    public ArrayList<Task> load() throws IOException {
        // initialize
        initialize();
        // load the data from the hard disk for dukeFile
        DukeParser p = new DukeParser(filePath);
        return p.copyFileContents();
    }

    /**
     * This method overwrites the file.
     *
     * @param filePath     The path to the file to append to.
     * @param textToAppend The text to append.
     * @throws IOException If filePath does not exist.
     */

    public void overwriteFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, false); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    /**
     * This method appends to the file instead of overwrites.
     *
     * @param filePath     The path to the file to append to.
     * @param textToAppend The text to append.
     * @throws IOException If filePath does not exist.
     */

    public void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

}
