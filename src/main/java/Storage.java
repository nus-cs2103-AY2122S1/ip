import java.io.*;
import java.util.ArrayList;

public class Storage {
    private String path;

    public Storage(String path) {
        this.path = path;
    }

    public void writeSave(ArrayList<Task> taskList) throws IOException {
        FileOutputStream writeData = new FileOutputStream(this.path);
        ObjectOutputStream writeStream = new ObjectOutputStream(writeData);
        writeStream.writeObject(taskList);
        writeStream.flush();
        writeStream.close();
    }


    public ArrayList<Task> readSave(ArrayList<Task> taskList) throws IOException, ClassNotFoundException {
        File f = new File(this.path);
        if (!f.createNewFile()) { // save file exists
            FileInputStream readData = new FileInputStream(this.path);
            ObjectInputStream readStream = new ObjectInputStream(readData);
            @SuppressWarnings("unchecked")
            ArrayList<Task> readList = (ArrayList<Task>) readStream.readObject();
            taskList = readList;
            readStream.close();
            return taskList;
        } else {
            taskList = new ArrayList<>();
            return taskList;
        }

    }
}
