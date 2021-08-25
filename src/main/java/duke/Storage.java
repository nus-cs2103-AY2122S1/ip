package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Represents a storage class that contains methods to read from
 * and write to the save file.
 */
public class Storage {
    private String user_dir;
    private DukeTaskList dukeTaskList;

    /**
     * Constructor of the Storage class.
     *
     * @param user_dir current directory where the user runs Duke from
     * @param dukeTaskList a dukeTaskList object of the current run of Duke.
     */
    public Storage(String user_dir, DukeTaskList dukeTaskList) {
        this.user_dir = user_dir;
        this.dukeTaskList = dukeTaskList;
    }

    /**
     * Method to read data from save file. If the save file and dir don't exist,
     * create them.
     *
     * @throws IOException if file or dir cannot be created
     */
    public void loadDataFile() throws IOException {
        File dataDir = new File(user_dir + "/data");
        File dataFile = new File(user_dir + "/data/dataFile.txt");
        if (!dataDir.exists()) {
            dataDir.mkdir();
            dataFile.createNewFile();// Throws IOException is something goes wrong
        } else {
            if (!dataFile.exists()) {
                dataFile.createNewFile();
            }
        }

        Scanner scanner = new Scanner(dataFile);
        while (scanner.hasNext()) {
            String currLine = scanner.nextLine();
            String[] currLineSplit = currLine.split("\\|", 3);
            dukeTaskList.loadDataToList(currLineSplit[0], currLineSplit[1], currLineSplit[2]);
        }
    }

    /**
     * Method to write data into the file.
     *
     * @throws IOException if file cannot be written to.
     */
    public void saveToDataFile() throws IOException {
        FileWriter fileWriter = new FileWriter(user_dir + "/data/dataFile.txt"); // Throws IOException is something goes wrong
        fileWriter.write(dukeTaskList.sendListToFile());
        fileWriter.close();
    }
}
