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
 * @author Samay Sagar
 * @version CS2103 AY21/22 Sem 1
 */
//Solution below adapted from https://github.com/jovyntls/ip
public class Storage {
    private final String dataStoragePath;

    /**
     * Constructor for Storage class that initializes
     * a new file if the file doesn't exist beforehand.
     * @param dataStoragePath the path to the storage file.
     */
    public Storage(String dataStoragePath) {
        // https://stackoverflow.com/questions/26239151/
        File file = new File("data/duke_storage.txt");
        try {
            file.getParentFile().mkdirs(); // Create data folder if not exists
            file.createNewFile(); // Create data file if not exists
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        this.dataStoragePath = dataStoragePath;
    }

    /**
     * Reads the text content of a given file.
     * @return An ArrayList of the lines read from a given file.
     */
    public ArrayList<String> readLines() {
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
