import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class Storage {
    private final File DATA_FILE;

    public Storage(String filepath) {
        DATA_FILE = new File("data/duke.txt");

        //Create data file if missing
        if (!DATA_FILE.exists()) {
            try {
                Files.createDirectory(Path.of("data"));
                Files.createFile(Path.of("data/duke.txt"));
            } catch (IOException e) {
                System.out.println("WARNING: Cannot create save file");
            }
        }
    }
    public ArrayList<Task> readFromDisk() {
        // Return empty list if file is empty
        if (DATA_FILE.length() == 0)
            return new ArrayList<>();

        // Else, deserialize data
        try {
            FileInputStream f = new FileInputStream(DATA_FILE);
            ObjectInputStream i = new ObjectInputStream(f);

            return (ArrayList<Task>) (i.readObject());
        } catch (FileNotFoundException e) {
            System.out.println("WARNING: Task list's save file missing.\n" + e.getMessage());
        } catch (IOException e) {
            System.out.println("WARNING: Task List not properly retrieved.\n" + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("WARNING: Missing Class ArrayList<Task>.\n" + e.getMessage());
        }

        return new ArrayList<>();
    }

    public void writeToDisk(ArrayList<Task> taskList) {
        try {
            FileOutputStream f = new FileOutputStream(DATA_FILE);
            ObjectOutputStream o = new ObjectOutputStream(f);

            o.writeObject(taskList);
            o.close();
        } catch (FileNotFoundException e) {
            System.out.println("WARNING: Task list's save file missing.\n" + e.getMessage());
        } catch (IOException e) {
            System.out.println("WARNING: Task List not properly saved.\n" + e.getMessage());
        }
    }

}
