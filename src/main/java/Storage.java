import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private String filepath;
    private File file;
    
    public Storage(String filepath) {
        this.filepath = filepath;
        try {
            File file = new File(filepath);
            if (!file.exists()) {
                file.createNewFile();
            }
            this.file = file;
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
    
    public void loadTaskListData() {
        try {
            Scanner s = new Scanner(this.file); // create a Scanner using the File as the source
            if (!s.hasNext()) {
                System.out.println("There are no items in your task list!");
            } else {
                System.out.println("Here is your current task list: ");
                while (s.hasNext()) {
                    System.out.println(s.nextLine());
                }
                System.out.println("End of task list");
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }

    public void writeToFile(String filePath, TaskList tl) {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (int i = 0; i < tl.size(); i++) {
                int num = i + 1;
                fw.write(num + ". " + tl.getTask(i).taskListOnDisk() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
    
    
}