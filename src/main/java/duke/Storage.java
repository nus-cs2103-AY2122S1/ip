package duke;

import java.util.ArrayList;
import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Represents Duke's storage system and handles file operations.
 */
public class Storage {
    public Storage() {

    }

    /**
     * Checks if the list file exists and creates it if it doesn't.
     *
     * @return true if the file exists or was successfully created
     * and false if there was an error creating the file.
     */
    public boolean checkExistence() {
        if (!Duke.file.exists()) {
            try {
                new File("data").mkdir();
                Duke.file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
            return true;
        } else {
            return true;
        }
    }

    /**
     * Loads the file contents into the list of strings for Duke to use.
     *
     * @param filePath File path.
     * @throws FileNotFoundException If the file is missing.
     */
    void listFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        TaskListInternal.lines = new ArrayList<String>();
        while (s.hasNext()) {
            TaskListInternal.lines.add(s.nextLine());

        }
        s.close();
    }

    /**
     * Writes the contents of the list of strings Duke uses into the list file.
     *
     * @param filePath File path.
     * @throws IOException If the file cannot be written to.
     */
    void writeListToFile(String filePath) throws IOException {
        FileWriter clearer = new FileWriter(filePath);
        clearer.write(""); //clear the file
        clearer.close();
        FileWriter fw = new FileWriter(filePath, true);
        for (String line : TaskListInternal.lines) {
            fw.write(line + System.lineSeparator());
        }
        fw.close();
    }

}
