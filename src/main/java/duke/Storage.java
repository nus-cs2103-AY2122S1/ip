package duke;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Represents a storage which you can retrieve and save from.
 * @author Nikki
 */
public class Storage {

    /**
     * Saves the ArrayList representing tasks into hard drive.
     *
     * @param saveThis Any ArrayList of Tasks to save.
     */
    public void fileSaver(ArrayList<Task> saveThis) {
        try {
            FileOutputStream fileOut = new FileOutputStream("saveFile");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(saveThis);
            out.flush();
            out.close();
            System.out.println("Saved Successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Write failed");
        }
    }

    /**
     * Reads the ArrayList representing tasks from the hard drive.
     * Creates a new file if there is no file to read from.
     *
     * @return ArrayList of tasks for the current program to use.
     */
    public ArrayList<Task> fileReader() {
        ArrayList<Task> result = new ArrayList<Task>();
        File tempFile = new File("saveFile");

        if (tempFile.exists()) {
            try {
                FileInputStream file = new FileInputStream("saveFile");
                ObjectInputStream in = new ObjectInputStream(file);
                result = (ArrayList<Task>) in.readObject();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Read failed");
            }
        } else {
            System.out.println("New Save File Created");
        }
        return result;
    }
}
