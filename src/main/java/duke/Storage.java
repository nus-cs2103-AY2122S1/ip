package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Reads and Writes data to the tasks' database
 */
public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<String> load() throws IOException {
        File directory = new File("data");
        if (!directory.exists()) {
            directory.mkdir();
        }
        File data = new File(filePath);
        data.createNewFile();
        Scanner s = new Scanner(data);
        ArrayList<String> tasks = new ArrayList<>();
        while (s.hasNext()) {
            tasks.add(s.nextLine());
        }
        return tasks;
    }

    public void saveTask(TaskList list) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task t : list.getList()) {
            fw.write(t.getSavedAs() + System.lineSeparator());
        }
        fw.close();
    }
}
