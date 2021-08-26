package duke;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * A class that handles the file containing data regarding tasks.
 *
 * @author Toh Wang Bin
 */
public class Storage {

    //path of file containing stored data
    private String filePath;
    //path of folder containing data file
    private String directoryPath;
    //FIle containing stored data
    private File dataFile;
    //instance of taskList used to store tasks
    private TaskList taskList;

    /**
     * Constructor for a Storage instance.
     *
     * @param filePath The path of the file containing the stored data.
     * @param directoryPath The path of the directory containing the file.
     * @param taskList The list containing the Tasks
     */
    public Storage(String filePath, String directoryPath, TaskList taskList) {
        this.filePath = filePath;
        this.directoryPath = directoryPath;
        this.taskList = taskList;
    }

    /**
     * Checks for the file, then loads the data into the taskList.
     */
    public void start() {
        //set up the file
        dataFile = new File(filePath);
        //file does not exist: attempt to create a new file.
        if (!dataFile.canRead()) {
            try {
                new File(directoryPath).mkdir();
            } catch (NullPointerException exception) {
                Ui.printUnknownError();
                return;
            }
        }

        loadData();
    }

    /**
     * Loads data from the file into the taskList.
     */
    private void loadData() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(dataFile));
            String nextLine;
            while ((nextLine = reader.readLine()) != null) {
                taskList.addTask((Parser.parseData(nextLine)));
            }
        } catch (Exception exception) {
            if (exception instanceof FileNotFoundException || exception instanceof IOException) {
                Ui.printFileError();
            } else {
                Ui.printUnknownError();
            }
        }
    }

    /**
     * Saves the Tasks in taskList into the file.
     */
    public void saveData() {
        try {
            FileWriter writer = new FileWriter(dataFile);
            StringBuilder string = new StringBuilder();
            for (int i = 0; i < taskList.getTaskNumber(); i++) {
                Task task = taskList.getTask(i);
                string.append(task.toDataString()).append("\n");
            }
            writer.write(string.toString());
            writer.close();
        } catch (IOException e) {
            Ui.printFileError();
        }
    }

}
