package virushade;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import virushade.tasks.Task;

/**
 * Our storage class deals with writing data to the text file passed into Virushade.
 */
public class Storage {

    private final File STORAGE_FILE;

    /**
     * The constructor for our Storage class.
     * @param filename The file path to be written to.
     */
    public Storage(String filename) {
        STORAGE_FILE = new File(filename);
    }

    /**
     * Reads from STORAGE_FILE and updates the given ArrayList input according to the lines in STORAGE_FILE.
     * @param tasks The ArrayList input to update information from STORAGE_FILE to.
     * @throws VirushadeException Thrown when STORAGE_FILE file is not parsed correctly.
     */
    public void load(ArrayList<Task> tasks) throws VirushadeException {
        try {
            // create a Scanner using the File as the source
            Scanner s = new Scanner(STORAGE_FILE);

            if (s.hasNext()) {
                // Do not parse the line "Here are the tasks in your list: "
                s.nextLine();
            }

            while (s.hasNext()) {
                Task scannedTask = Task.parse(s.nextLine());
                tasks.add(scannedTask);
            }
        } catch (FileNotFoundException e) {
            createFilePath(STORAGE_FILE);
        }
    }

    /**
     * Creates a file path according to the input file path.
     * @param f The input file path
     * @throws VirushadeException Thrown when unable to create file.
     */
    private void createFilePath(File f) throws VirushadeException {
        try {
            f.getParentFile().mkdirs();
            f.createNewFile();
        } catch (IOException e) {
            throw new VirushadeException("Unable to create file.");
        }
    }

    /**
     * Updates STORAGE_FILE according to the input text.
     * @param text The input string to update STORAGE_FILE.
     * @throws VirushadeException Thrown when unable to create/ write to STORAGE_FILE.
     */
    public void update(String text) throws VirushadeException {
        if (!STORAGE_FILE.exists()) {
            createFilePath(STORAGE_FILE);
        }

        try {
            FileWriter fw = new FileWriter(STORAGE_FILE);
            fw.write(text);
            fw.close();
        } catch (IOException e) {
            throw new VirushadeException("Unable to write to file.");
        }
    }
}
