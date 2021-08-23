import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Duke class which handles interactions with user files for storage purposes
 */
public class DukeIOManager {
    /**
     * Gets the file which stores tasks from the user's session
     * If the file exists, it returns the file
     * If the file does not exist, it creates and returns it
     * @return Text file containing user session data
     */
    public static File getDataFile() {
        File dataFile = new File("data/duke.txt");
        dataFile.getParentFile().mkdirs();
        try {
            dataFile.createNewFile();
        } catch (IOException e) {
            System.out.println("There was an error.");
        }
        return dataFile;
    }

    /**
     * Gets the user task file and writes a string to it
     * Erases existing data
     * @param string String to be written
     */
    public static void writeToDataFile(String string) {
        File dataFile = getDataFile();
        try {
            FileWriter fw = new FileWriter(dataFile);
            fw.write(string);
            fw.close();
        } catch (IOException e) {
            System.out.println("IOException");
        }
    }

    /**
     * Gets the user task file and appends a string to it
     * @param string String to be appended
     */
    public static void appendToDataFile(String string) {
        File dataFile = getDataFile();
        try {
            FileWriter fw = new FileWriter(dataFile, true);
            fw.write(string);
            fw.close();
        } catch (IOException e) {
            System.out.println("IOException");
        }
    }
}
