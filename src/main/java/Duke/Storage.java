package duke;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private static final String ERR_SAVE = "Unexpected error occured. Could not save Tasks to file.";
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns a List of Tasks initialized from local file (if any).
     * If local file not provided, this function creates one.
     *
     * @return new list of tasks obtained from local file (if any).
     * @throws FileNotFoundException if file is not found
     * @throws IOException if file cannot be created
     */
    public List<Task> load() throws IOException {
        File file = new File(filePath);
        file.createNewFile();
        FileInputStream fstream = new FileInputStream(file);
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
        List<Task> tasks = new ArrayList<>();
        String line;
        while ((line = br.readLine()) != null) {
            tasks.add(Task.strToObj(line));
        }
        br.close();
        return tasks;
    }

    /**
     * Adds a new task to the end of the file.
     *
     * @param task task to be added to the end of the file
     */
    public void appendToFile(Task task) {
        try {
            File file = new File(filePath);
            PrintWriter pw = new PrintWriter(new FileOutputStream(file, true));
            pw.append(task.toFile());
            pw.close();
        } catch (FileNotFoundException e) {
            System.out.println(ERR_SAVE);
        }
    }

    /**
     * Rewrites the contents of the file with all tasks.
     *
     * @param tasks tasks to be written to the file
     */
    public void saveToFile(List<Task> tasks) {
        try {
            File file = new File(filePath);
            PrintWriter pw = new PrintWriter(new FileOutputStream(file));
            for (Task task : tasks) {
                pw.println(task.toFile());
            }
            pw.close();
        } catch (FileNotFoundException e) {
            System.out.println(ERR_SAVE);
        }
    }
}
