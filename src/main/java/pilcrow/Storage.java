package pilcrow;

import java.io.File;
import java.io.FileNotFoundException;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

// Abstraction to handle data stored onto the hard disk
public class Storage {
    private String filePath;

    /**
     * Constructor for Storage object.
     * Takes in a filePath String to locate and manipulate a file.
     * @param filePath String representing location of storage.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the contents of a Storage object as an ArrayList of Tasks.
     * @return ArrayList of Tasks stored in Storage object.
     */
    public ArrayList<Task> load() {
        File pilcrowFile = new File(this.filePath);
        Scanner scanner;
        try {
            scanner = new Scanner(pilcrowFile);
        } catch (FileNotFoundException exception) {
            // Create the folder, if it doesn't exist
            File dataDir = new File("data");
            if (!dataDir.exists()) {
                dataDir.mkdir();
            }
            scanner = new Scanner("");
        }
        ArrayList<Task> taskList = new ArrayList<>();

        while (scanner.hasNext()) {
            taskList.add(Task.convertFromStoredTask(scanner.nextLine()));
        }
        scanner.close();
        return taskList;
    }

    /**
     * Save specified TaskList into Storage for later loading.
     * @param taskList TaskList to be stored.
     */
    public void save(TaskList taskList){
        String storedTasks = "";
        for (int i = 1; i <= taskList.getNumberOfTasks(); i++) {
            storedTasks += taskList.getTask(i).convertToStoredTask() + "\n";
        }

        try {
            FileWriter pilcrowFileWriter = new FileWriter(this.filePath);
            pilcrowFileWriter.write(storedTasks);
            pilcrowFileWriter.close();
        }  catch (IOException exception) {

        }
    }
}
