package ligma;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import ligma.task.Task;

/**
 * This class represents the storage for a particular
 * Ligma program. The file f is where all data is written to
 * and read from during the execution of the Ligma program.
 */
public class Storage {
    private File f;

    public Storage(String pathname) {
        f = new File(pathname);
    }

    /**
     * Loads in tasks previously stored in the file.
     *
     * @return              arrayList of tasks as read from file
     * @throws IOException  if there was an error in referencing files
     */
    public ArrayList<Task> load() throws IOException {
        if (!f.exists()) {
            f.getParentFile().mkdirs();
            f.createNewFile();
            return new ArrayList<>();
        } else {
            Scanner scanner = new Scanner(f);
            ArrayList<Task> res = new ArrayList<>();
            while (scanner.hasNextLine()) {
                Task t = Parser.parseFileContent(scanner.nextLine());
                res.add(t);
            }
            return res;
        }
    }

    /**
     * Saves TaskList to file.
     *
     * @param tasks         TaskList to be saved to file
     * @throws IOException  if there was an error in writing to file
     */
    public void saveTaskList(TaskList tasks) throws IOException {
        if (tasks.isEmpty()) {
            return;
        }
        FileWriter fw = new FileWriter(f);
        if (f.length() != 0) {
            fw.write(System.lineSeparator());
        }
        String[] contents = tasks.getMetaTasks();
        int len = contents.length;
        for (int i = 0; i < len - 1; i++) {
            fw.write(contents[i] + "\n");
        }
        fw.write(contents[len - 1]);
        fw.close();
    }

    /**
     * Saves Task to file.
     *
     * @param task         Task to be saved to file
     * @throws IOException  if there was an error in writing to file
     */
    public void saveTask(Task task) throws IOException {
        FileWriter fw = new FileWriter(f, true);
        if (f.length() != 0) {
            fw.write(System.lineSeparator());
        }
        fw.write(task.toString());
        fw.close();
    }

}
