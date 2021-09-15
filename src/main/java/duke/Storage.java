package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Represent a storage class that contains methods to read from
 * and write to the save file.
 */
public class Storage {
    private String userDir;
    private DukeTaskList dukeTaskList;

    /**
     * Constructor of the Storage class.
     *
     * @param userDir      current directory where the user runs Duke from
     * @param dukeTaskList a dukeTaskList object of the current run of Duke.
     */
    public Storage(String userDir, DukeTaskList dukeTaskList) {
        this.userDir = userDir;
        this.dukeTaskList = dukeTaskList;
    }

    /**
     * Reads data from save file. If the save file and dir don't exist,
     * create them.
     */
    public void loadDataFile() {
        try {
            File dataDir = new File(userDir + "/data");
            File dataFile = new File(userDir + "/data/dataFile.txt");
            if (!dataDir.exists()) {
                dataDir.mkdir();
                dataFile.createNewFile(); // Throws IOException is something goes wrong
            } else {
                if (!dataFile.exists()) {
                    dataFile.createNewFile();
                }
            }
            // After above, both dataDir and dataFile should exist.
            assert dataDir.exists() : "Data Dir not created!";
            assert dataFile.exists() : "Data File not created";

            Scanner scanner = new Scanner(dataFile);
            while (scanner.hasNext()) {
                String currLine = scanner.nextLine();
                String[] currLineSplit = currLine.split("\\|", 3);
                dukeTaskList.loadDataToList(currLineSplit[0], currLineSplit[1], currLineSplit[2]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes data into the file.
     */
    public void saveToDataFile() {
        try {
            FileWriter fileWriter = new FileWriter(userDir + "/data/dataFile.txt");
            fileWriter.write(dukeTaskList.sendListToFile()); // Throws IOException if something goes wrong
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
