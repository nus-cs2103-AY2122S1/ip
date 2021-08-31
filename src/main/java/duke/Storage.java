package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Storage handles the reading and writing of data to text files.
 *
 * @author Jovyn Tan
 * @version CS2103 AY21/22 Sem 1
 */
public class Storage {
    private final String dataStoragePath;

    /**
     * A constructor for Duke Storage.
     * @param dataStoragePath the path of the file where Duke data is stored.
     */
    public Storage(String dataStoragePath) {
        this.dataStoragePath = dataStoragePath;
    }

    /**
     * Reads the text content of a given file.
     * @return An ArrayList of the lines read from a given file.
     */
    public ArrayList<String> readLinesFromFile() {
        ArrayList<String> lines = new ArrayList<>();
        try {
            File file = new File(this.dataStoragePath);
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String data = sc.nextLine();
                lines.add(data);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("No save data found.");
        }
        return lines;
    }

    /**
     * Creates a file with the given filename.
     * If the file already exists, it will overwrite the existing file with a new file.
     */
    public void overwriteNewFile() {
        try {
            FileWriter fileWriter = new FileWriter(this.dataStoragePath, false);
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Writes a given String to a given file, without overwriting existing content.
     * @param content The content to be written to the file
     */
    public void writeToFile(String content) {
        try {
            FileWriter fileWriter = new FileWriter(this.dataStoragePath, true);
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}