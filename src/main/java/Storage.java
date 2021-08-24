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
            scanner = new Scanner("");
        }
        ArrayList<Task> taskList = new ArrayList<>();

        while (scanner.hasNext()) {
            taskList.add(Task.convertFromStoredTask(scanner.nextLine()));
        }
        return taskList;
    }

    /**
     *
     * @param taskList
     */
    public void save(ArrayList<Task> taskList) {
        String storedTasks = "";
        for (int i = 0; i < taskList.size(); i++) {
            storedTasks += taskList.get(i).convertToStoredTask();
        }

        // IOException will be handled in Pilcrow.run()
        FileWriter pilcrowFileWriter = new FileWriter(this.filePath);
        pilcrowFileWriter.write(storedTasks);
        pilcrowFileWriter.close();
    }
}
