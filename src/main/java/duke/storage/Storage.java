package duke.storage;

import duke.commands.Task;
import duke.data.TaskList;
import duke.ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static final String FOLDER_NAME = "data";
    private static final String FILE_NAME = "duke.txt";

    public void createFile(){
        File file = new File(String.format("%s/%s",FOLDER_NAME, FILE_NAME));
        boolean created = false;
        try {
            created = file.createNewFile();
        } catch (IOException e){
            System.out.println("Something went wrong: " + e.getMessage());
        } finally {
            if (created){
                System.out.println("    New data file has been created.");
            } else {
                System.out.println("Data file already exist.");
            }
        }
    }

    public void createFolder(){
        File folder = new File(FOLDER_NAME);
        boolean created = folder.mkdir();
        if (!created){
            System.out.println("Folder could not be created.");
        }
    }

    public ArrayList<Task> load(){
        ArrayList<Task> taskList = new ArrayList<>();
        File file = new File(String.format("%s/%s", FOLDER_NAME, FILE_NAME));
        try {
            Scanner sc = new Scanner(file);
            Ui.printMessageWithFormat("I have found past data in your local storage, type 'list' to view the previous tasks.");
            while (sc.hasNextLine()){
                String curr = sc.nextLine();
                Task task = TaskList.stringToTask(curr);
                taskList.add(task);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            Ui.printMessageWithFormat("No past data found in your local storage, initializing from blank state.");
            createFolder();
            createFile();
        }
        return taskList;
    }

    public void save(ArrayList<Task> taskList){
        try {
            FileWriter writer = new FileWriter(String.format("%s/%s", FOLDER_NAME, FILE_NAME));
            for (Task task: taskList){
                writer.write(task.toString() + "\n");
            }
            writer.close();
        } catch (IOException e){
            System.out.println("Something went wrong: " + e.getMessage());
        }

    }
}
