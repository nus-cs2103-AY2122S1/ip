package cs2103.duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class Storage {
    private static ArrayList<Task> taskArrayList = new ArrayList<>();
    private final String filePath;

    public Storage(String dukeFilePath) {
        this.filePath = dukeFilePath;
    }

    /**
     * Checks the dukeFilePath for the existence of duke.txt, if the file or the folder containing it
     * does not exist, create them.
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
     * @return the taskArrayList representation of the data stored in duke.txt, if any.
     * @throws IOException
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
     * @param filePath     the path to the file to append to
     * @param textToAppend the text to append
     * @throws IOException if filePath does not exist
     */

    public void overwriteFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, false); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    /**
     * This method appends to the file instead of overwrites.
     *
     * @param filePath     the path to the file to append to
     * @param textToAppend the text to append
     * @throws IOException if filePath does not exist
     */

    public void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

}
