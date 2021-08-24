package duke;

import java.nio.charset.StandardCharsets;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.IOException;

/**
 * Stores tasks locally on the hard disk.
 */
public class Storage {


    /**
     * Appends tasks to text file.
     * @param filePath path of the text file that stores the tasks.
     * @param textToAppend the text to be appended.
     * @throws IOException
     */
    public void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    /**
     * Overwrites tasks to text file.
     * @param filePath path of the text file that stores the tasks.
     * @param textToAdd the text to be added.
     * @throws IOException
     */
    public void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Reads tasks from the text file.
     * @param filePath
     * @return
     * @throws FileNotFoundException
     */
    public Scanner readFile(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        return s;
    }
}
