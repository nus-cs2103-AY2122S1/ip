package virushade;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import virushade.tasks.Task;

public class Storage {

    private final File STORAGE_FILE;

    public Storage(String filename) {
        STORAGE_FILE = new File(filename);
    }

    public void load(ArrayList<Task> tasks) throws VirushadeException{
        try {
            // create a Scanner using the File as the source
            Scanner s = new Scanner(STORAGE_FILE);

            if (s.hasNext()) {
                // Do not parse the line "Here are the tasks in your list: "
                s.nextLine();
            }

            while (s.hasNext()) {
                Task scannedTask = Task.parse(s.nextLine());
                tasks.add(scannedTask);
            }
        } catch (FileNotFoundException e) {
            Ui.showCreatingFiles();
            createFilePath(STORAGE_FILE);
        }
    }

    private void createFilePath(File f) throws VirushadeException {
        try {
            if (f.getParentFile().mkdirs()) {
                Ui.showCreatingDirectory();
            }

            if (f.createNewFile()) {
                Ui.showCreatingFiles();
            }
        } catch (IOException e) {
            throw new VirushadeException("Unable to create file.");
        }
    }

    public void update(String text) throws VirushadeException {
        if (!STORAGE_FILE.exists()) {
            createFilePath(STORAGE_FILE);
        }

        try {
            FileWriter fw = new FileWriter(STORAGE_FILE);
            fw.write(text);
            fw.close();
        } catch (IOException e) {
            throw new VirushadeException("Unable to write to file.");
        }
    }
}
