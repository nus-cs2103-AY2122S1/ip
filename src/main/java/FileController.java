import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileController {

    String filePath;

    public FileController(String filePath) {
        this.filePath = filePath;
    }

    public void createFile() {
        try {
            File newFile = new File(this.filePath);
            newFile.createNewFile();
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }


    public void writeToFile(List<Task> items) {
        try {
            FileWriter myWriter = new FileWriter(this.filePath);
            String content = "";
            for (Task item : items) {
                content += item.toString() + "\n";
            }
            myWriter.write(content);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }

}
