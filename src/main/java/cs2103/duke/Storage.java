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

    public ArrayList<Task> load() throws IOException {
        // load the data from the hard disk for dukeFile
        File dukeFile = new File(filePath);

        // creates directory if it does not exist
        if (dukeFile.mkdir()) {
            // dukeFile.mkdir();
            System.out.println("folder: 'data/' has been created");
        }
        // creates file if it does not exist
        if (dukeFile.createNewFile()) {
            // dukeFile.createNewFile();
            System.out.println("'duke.txt' has been created in the 'data/' folder ");
        }

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
