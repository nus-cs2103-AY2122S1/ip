import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner; 

public class FileManager {
    private static final String FILENAME = "tasks.txt"; 
    private static final File DATA_FILE = new File(FILENAME); 
    
    public FileManager() {
        try {
            if (DATA_FILE.createNewFile()) {
                System.out.println("A new save file has been created!"); 
            } else {
                System.out.println("Using an existing save file..."); 
            }
        } catch (IOException e) {
            System.out.println(e.getMessage()); 
        }
    }
    
    public TaskList getListFromFile() {
        ArrayList<Task> newList = new ArrayList<>(); 
        try {
            Scanner sc = new Scanner(DATA_FILE);
            while (sc.hasNextLine()) {
                Task newTask = null; 
                String[] curr = sc.nextLine().split("~S~");
                String taskIcon = curr[0];
                boolean isDone = curr[1].equals("1"); 
                switch (taskIcon) {
                    case "T":
                        newTask = new Todo(curr[2]);
                        break;
                    case "D":
                        newTask = new Deadline(curr[2], curr[3]);
                        break;
                    case "E":
                        newTask = new Event(curr[2], curr[3]);
                        break;
                }
                if (isDone) {
                    newTask.markAsDone();
                }
                newList.add(newTask); 
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return new TaskList(newList); 
    }
    
    public void writeToFile(TaskList list) {
        try {
            FileWriter writer = new FileWriter(FILENAME); 
            writer.write(list.convertToData());
            writer.close(); 
            System.out.println("Your task list has been saved"); 
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
