import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a file to store data, operations to write on the file.
 * 
 * @author Gordon
 * @Since 25/8/21
 */
public class DataFile {
    File file;
    FileWriter fileWriter;
    /**
     * Class constructor.
     * 
     * @param filePath the path from the project directory to the storage file.
     * @param fileName name of the storage file.
     */
    public DataFile(String filePath, String fileName) throws IOException {
        File path = new File(filePath);
        if (!path.exists()) {
            path.mkdir();
        }
        
        String fileDirectory = filePath + fileName;
        file = new File(fileDirectory);
        if (!file.exists()) {
            file.createNewFile();
        }
        
    }

    /**
     * loads all the tasks saved in the storage folder into tasks array.
     * 
     * @param tasks array of tasks.
     * @throws IOException
     */
    public void load(ArrayList<Task> tasks) throws IOException{
        if (file.length() != 0) {
            Scanner sc = new Scanner(file);
            String line;
            while (sc.hasNext()) {
                line = sc.nextLine();
                int START = 8;
                String taskInformation = line.substring(START);
                String symbol = "|";
                Task t;
                if (line.contains("D |")) {
                    t = new Deadline(getTaskDetails(taskInformation)[0],
                            getTaskDetails(taskInformation)[1]);
                } else if (line.contains("E |")) {
                    t = new Event(getTaskDetails(taskInformation)[0],
                            getTaskDetails(taskInformation)[1]);
                } else {
                    t = new Todo(taskInformation);
                }
                if (line.contains("0")) {
                    t.markAsDone();
                }
                tasks.add(t);
            }
            sc.close();
        }
    }

    /**
     * stores all the tasks from the tasks array into the storage file.
     * @param tasks the task array.
     * @throws IOException
     */
    public void store(ArrayList<Task> tasks) throws IOException{
        file.delete();
        file.createNewFile();
        fileWriter = new FileWriter(file);
        String data = "";
        for (Task t : tasks) {
            data += t.toString() + "\n";
        }
        System.out.println(file.getCanonicalPath());
        fileWriter.write(data);
        fileWriter.close();
    }
    
    private String[] getTaskDetails(String string) {
        String SYMBOL = "|";
        int index = string.indexOf(SYMBOL);
        String taskDescription = string.substring(0, index - 1);
        String timing = string.substring(index + 2);
        return new String[] {taskDescription, timing};
    }
    
    
    
    
}
