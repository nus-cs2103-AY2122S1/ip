import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class DataFile {

    private File dataFile;

    public DataFile(String filePath) {
        try {
            this.dataFile = new File(filePath);
            this.dataFile.createNewFile();
        } catch (IOException e) {
            System.out.println("Error creating/loading data file");
            this.dataFile = null;
        }
    }

    public ArrayList<Task> ParseTasks() {

        return new ArrayList<>();
    }

    public String toString() {
        try {
            Scanner reader = new Scanner(this.dataFile);
            String contents = "";
            while (reader.hasNext()) {
                contents += reader.nextLine();
            }
            return contents;
        } catch (IOException e) {
            return "";
        }
    }

}
