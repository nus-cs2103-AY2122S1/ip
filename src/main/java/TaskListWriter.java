import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class TaskListWriter {
    public static void write(String filepath, ArrayList<Task> taskList) {
        try {
            FileOutputStream fos = new FileOutputStream(filepath);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(taskList);
            oos.close();
        } catch(IOException e) {
            System.out.println("Oops! Data file not found!");
        }
    }
}
