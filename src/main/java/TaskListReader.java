import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class TaskListReader {
    public static ArrayList<Task> read(String filepath) {
        try {
            FileInputStream fileIn = new FileInputStream(filepath);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            ArrayList<Task> taskList = (ArrayList<Task>) objectIn.readObject();
            objectIn.close();
            return taskList;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
