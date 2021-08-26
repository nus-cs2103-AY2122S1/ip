package duke;

import java.io.*;

/**
 * Encapsulates the methods needed to read and write to a save file.
 */
public class Storage {
    private final String path;

    /**
     * Class constructor which takes in the path of the save file.
     *
     * @param path Path of the save file
     */
    public Storage(String path) {
        this.path = path;
    }

    /**
     * Writes the contents of Duke's task list to the save file
     * @param taskList The list of tasks to be saved
     * @throws IOException If the file cannot be written to
     */
    public void writeSave(TaskList taskList) throws IOException {
        FileOutputStream writeData = new FileOutputStream(this.path);
        ObjectOutputStream writeStream = new ObjectOutputStream(writeData);
        writeStream.writeObject(taskList);
        writeStream.flush();
        writeStream.close();
    }

    /**
     * Reads the contents of the save file and returns the TaskList containing
     * the saved tasks
     *
     * @return TaskList object containing saved tasks
     * @throws IOException If the reading of the file fails
     * @throws ClassNotFoundException If the save data is of a missing class
     */
    public TaskList readSave() throws IOException, ClassNotFoundException {
        File f = new File(this.path);
        if (!f.createNewFile()) { // save file exists
            FileInputStream readData = new FileInputStream(this.path);
            ObjectInputStream readStream = new ObjectInputStream(readData);
            TaskList readList = (TaskList) readStream.readObject();
            readStream.close();
            return readList;
        } else {
            return new TaskList();
        }

    }
}
