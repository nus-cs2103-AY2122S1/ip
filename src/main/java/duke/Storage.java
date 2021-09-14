package duke;

import duke.task.Task;

import java.io.BufferedReader;
import java.io.File;
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
    //File containing stored data
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
     *
     * @throws IOException Thrown when there is an issue loading data.
     */
    public void start() throws IOException {
        //set up the file
        dataFile = new File(filePath);
        //file does not exist: attempt to create a new file.
        if (!dataFile.canRead()) {
            try {
                new File(directoryPath).mkdir();
                dataFile.createNewFile();
            } catch (NullPointerException exception) {
                System.out.println(Ui.getUnknownError());
                return;
            }
        }
        assert dataFile.canRead() && dataFile.canWrite() : "File should be readable and writable at this point.";
        loadData();
    }

    /**
     * Loads data from the file into the taskList.
     *
     * @throws IOException Thrown when data is unable to be loaded from the file.
     */
    private void loadData() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(dataFile));
        String nextLine;

        //repeatedly add tasks from the file into the ArrayList
        while ((nextLine = reader.readLine()) != null) {
            taskList.addTask((Parser.parseData(nextLine)));
        }
    }

    /**
     * Saves the Tasks in taskList into the file.
     *
     * @throws IOException Thrown when data is unable to be saved into the file.
     */
    public void saveData() throws IOException {
        FileWriter writer = new FileWriter(dataFile);
        StringBuilder string = new StringBuilder();
        //repeatedly append the task strings until all tasks have been saved
        for (int i = 0; i < taskList.getTotalTasks(); i++) {
            Task task = taskList.getTask(i);
            string.append(task.toDataString()).append("\n");
        }
        //save the string into the file
        writer.write(string.toString());
        writer.close();
    }

}
