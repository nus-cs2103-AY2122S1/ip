package duke.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Class encapsulating the interface between local storage and the application logic.
 * Has methods for loading from local storage and writing to local storage.
 */
public class Storage {

    /**
     * Returns a list of strings for each line read in a text file located at the given relative path.
     * @param pathResolve Array of strings to be resolved to a path.
     * @return List of strings for each line read.
     */
    public List<String> loadRelative(String... pathResolve) {
        Scanner sc = null;
        List<String> result = new ArrayList<>();
        try {
            Path filePath = Paths.get(".", pathResolve);
            File file = filePath.toFile();

            sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String line = sc.nextLine().strip();
                result.add(line);
            }
            return result;
        } catch (FileNotFoundException e) {
            System.out.println("Local data not found. Continuing as fresh application.");
            return result;
        } finally {
            if (sc != null) {
                sc.close();
            }
        }
    }

    /**
     * Stores the given content to a text file located at the given relative path.
     * @param content Content to be stored.
     * @param pathResolve Array of strings to be resolved to a relative path.
     */
    public void storeRelative(String content, String... pathResolve) {
        FileWriter f = null;
        try {
            Path filePath = Paths.get(".", pathResolve);
            Path directoryPath = filePath.getParent();
            File directory = directoryPath.toFile();
            directory.mkdirs();
            f = new FileWriter(filePath.toString());
            f.write(content);
            f.close();
        } catch (IOException e) {
            System.out.println("Error writing to file");
        }
    }
}
