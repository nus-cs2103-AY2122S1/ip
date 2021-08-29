import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a file to store data, operations to write on the file.
 * 
 * @author Gordon
 * @Since 25/8/21
 */
public class Storage {
    private String filepath;
    private File file;
    private FileWriter fileWriter;
    
    /**
     * Class constructor.
     * 
     * @param filePath the path from the project directory to the storage file.
     */
    public Storage(String filePath) {
        this.filepath = filePath;
    }

    /**
     * loads all the tasks saved in the storage folder into a tasks arraylist.
     * 
     * @return an arraylist of tasks.
     * @throws IOException
     */
    public  ArrayList<Task> load() throws DukeException{
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File tempFile = new File(filepath);
            filepath = tempFile.getCanonicalPath();
            file = new File(filepath);
            if (!file.exists()) {
                file.createNewFile();
            }
            if (file.length() != 0) {
                Scanner sc = new Scanner(file);
                String line;
                while (sc.hasNext()) {
                    line = sc.nextLine();
                    int START = 8;
                    Task t;
                    String divider = " | ";
                    int taskAndTimeDivider = line.indexOf(divider);
                    int startingIndex = line.indexOf(divider) + divider.length() + 4;
                    if (line.contains("D |")) {
                        String taskDescriptionAndTime = line.substring(startingIndex);
                        int startOfTimeIndex = taskDescriptionAndTime.indexOf(divider);
                        String task = taskDescriptionAndTime.substring(0, startOfTimeIndex);
                        String time = taskDescriptionAndTime.substring(startOfTimeIndex + divider.length());
                        t = new Deadline(task, time);
                    } else if (line.contains("E |")) {
                        String taskDescriptionAndTime = line.substring(startingIndex);
                        int startOfTimeIndex = taskDescriptionAndTime.indexOf(divider);
                        String task = taskDescriptionAndTime.substring(0, startOfTimeIndex);
                        String time = taskDescriptionAndTime.substring(startOfTimeIndex + divider.length());
                        t = new Event(task, time);
                    } else {
                        String taskDescription = line.substring(startingIndex);
                        t = new Todo(taskDescription, "");
                    }
                    if (line.contains("| 0 |")) {
                        t.markAsDone();
                    }
                    tasks.add(t);
                    file.delete();
                }
                sc.close();
            }
        } catch (IOException e) {
            file = new File(filepath);
            throw new DukeException(e);
        } catch (ParseException e) {
            throw new DukeException(e);
        }
        return tasks;
    }

    /**
     * stores all the tasks from the tasks array into the storage file.
     * @param tasks an arraylist of tasks.
     * @throws IOException
     */
    public void store(ArrayList<Task> tasks) throws IOException{
        file = new File(filepath);
        file.createNewFile();
        fileWriter = new FileWriter(file, false);
        String data = "";
        for (Task t : tasks) {
            data += t.formatToStore() + "\n";
        }
        fileWriter.write(data);
        fileWriter.close();
    }
    
}
