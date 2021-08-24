import java.io.*;
import java.util.ArrayList;

public class Storage {
    private String fileName;

    public Storage(String fileName) {
        this.fileName = fileName;
    }
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

    public ArrayList<Task> loadFromFile() {
        try{
            FileInputStream readData = new FileInputStream(fileName);
            ObjectInputStream readStream = new ObjectInputStream(readData);
            ArrayList<Task> list2 = (ArrayList<Task>) readStream.readObject();
            readStream.close();
            return list2;
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        } catch(IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
