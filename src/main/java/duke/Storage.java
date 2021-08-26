package duke;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Storage {
    private final String path;

    public Storage(String path) {
        this.path = path;
    }

    public void writeSave(TaskList taskList) throws IOException {
        FileOutputStream writeData = new FileOutputStream(this.path);
        ObjectOutputStream writeStream = new ObjectOutputStream(writeData);
        writeStream.writeObject(taskList);
        writeStream.flush();
        writeStream.close();
    }

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