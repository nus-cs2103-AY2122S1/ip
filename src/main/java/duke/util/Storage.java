package duke.util;

import duke.task.Task;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Storage {
    //File name of file to be used to write and load list of task.
    private final String fileName;

    /**
     * Constructor of Storage.
     *
     * @param fileName File name of file to write to or load from.
     */
    public Storage(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Write the inputted list onto the specified file.
     *
     * @param list ArrayList to be written onto the file.
     */
    public void writeToFile(ArrayList<Task> list) {
        try {
            FileOutputStream writeData = new FileOutputStream(fileName);
            ObjectOutputStream writeStream = new ObjectOutputStream(writeData);

            writeStream.writeObject(list);
            writeStream.flush();
            writeStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Return an ArrayList that is loaded from the file.
     *
     * @return ArrayList from file, if nothing is written on file, return new ArrayList list.
     */
    public ArrayList<Task> loadFromFile() {
        try {
            FileInputStream readData = new FileInputStream(fileName);
            ObjectInputStream readStream = new ObjectInputStream(readData);
            ArrayList<Task> list2 = (ArrayList<Task>) readStream.readObject();
            readStream.close();
            return list2;
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
