import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors

public class CreateFile {
    public static void main(String[] args) {
        try {
            File taskList = new File("../../data/duketasks.txt");
            if (taskList.createNewFile()) {

            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}