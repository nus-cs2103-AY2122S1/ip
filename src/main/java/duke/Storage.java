package duke;

import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.task.Event;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Storage {

    public String path;
    public static String pathFolder = "." +  File.separator + "data" + File.separator;
    public File file;
    
    public Storage(String filePath){
        this.path = filePath;
        file = new File(path);
        
        
    }
    
    public List<Task> loadIntoDuke() {
        List<Task> items = new ArrayList<>();
        try{
            if (!file.createNewFile()) {
                Scanner sc = new Scanner(file);
                while (sc.hasNextLine()) {
                    String task = sc.nextLine();
                    if (task.startsWith("T")) {
                        items.add(new ToDo(
                                task.substring(8), Integer.parseInt(task.substring(4, 5))));
                    } else if (task.startsWith("D")) {
                        String taskSubstring = task.substring((8));
                        items.add(new Deadline(
                                taskSubstring.substring(0, taskSubstring.indexOf("|")),
                                taskSubstring.substring(taskSubstring.indexOf("|") + 2),
                                Integer.parseInt(task.substring(4, 5))
                        ));
                    } else {
                        String taskSubstring = task.substring((8));
                        items.add(new Event(
                                taskSubstring.substring(0, taskSubstring.indexOf("|")),
                                taskSubstring.substring(taskSubstring.indexOf("|") + 2),
                                Integer.parseInt(task.substring(4, 5))
                        ));
                    }
                }
            }
            return items;
        } catch (Exception e){
            fixFileFolderProblems();
            return items;
        }
    }
    
    public void addNewTask(Task task) {
        try {
            String input = task.toStorageString();
            Files.write(Paths.get(path), input.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    public void writeToFile(TaskList taskList){
        try{
            FileWriter fw = new FileWriter(path, false);
            PrintWriter pw= new PrintWriter(fw, false);
            pw.flush();
            pw.close();
            fw.close();
            for(int i = 0; i < taskList.getSize(); i++){
                String input = taskList.getTask(i + 1).toStorageString();
                Files.write(Paths.get(path), input.getBytes(), StandardOpenOption.APPEND);
            }
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void fixFileFolderProblems(){
        try{
            if(!Files.isDirectory(Path.of(pathFolder))){
                Files.createDirectory(Path.of(pathFolder));
            } if(!Files.isRegularFile(Path.of(path))){
                Files.createFile(Path.of(path));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
