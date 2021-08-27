import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private static final String FOLDER = "data";
    private static final String FILE = "duke.txt";

    public void createFolder(){
        File folder = new File(FOLDER);
        boolean folderCreated = folder.mkdir();
        if (!folderCreated) {
            System.out.println("Folder could not be created.");
        }
    }

    public void createFile(){
        File file = new File(FOLDER + "/" + FILE);
        boolean fileCreated = false;
        try {
            fileCreated = file.createNewFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            if (fileCreated) {
                System.out.println("New file has been created");
            } else {
                System.out.println("File already exist.");
            }
        }
    }

    public ArrayList<Task> load(){
        ArrayList<Task> taskList = new ArrayList<>();
        File file = new File(FOLDER + "/" + FILE);
        try {
            Scanner sc = new Scanner(file);
            System.out.println("Tasks have been loaded into the list");
            while (sc.hasNextLine()) {
                String curr = sc.nextLine();
                Task task = TaskList.stringToTask(curr);
                taskList.add(task);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("No data found, initializing empty list.");
            createFolder();
            createFile();
        }
        return taskList;
    }

    public void save(ArrayList<Task> taskList) {
        try {
            FileWriter fileWriter = new FileWriter(FOLDER + "/" + FILE);
            for (Task task: taskList) {
                fileWriter.write(task.toString() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Save Error: " + e.getMessage());
        }
    }
}
