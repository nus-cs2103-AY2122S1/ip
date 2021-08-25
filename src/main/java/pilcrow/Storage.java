package pilcrow;

import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;

import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

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
     *
     * @param taskList
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
