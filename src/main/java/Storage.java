import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {
    public String FileName;

    public Storage() {
        this.FileName = "storage.txt";
    }

    public void Save(ArrayList<Task> arr) {
        try {
            FileWriter fileWriter = new FileWriter(this.FileName);
            for (Task task : arr) {

            }
        } catch (IOException e) {

        }




        // if does not exist, create txt file

        // else open txt file


        // write to file current Duke lists
    }

    public void Load() {

    }
}
