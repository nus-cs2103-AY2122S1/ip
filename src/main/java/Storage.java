import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Scanner;


public class Storage {
    
    public static String path = "." +  File.separator + "data" + File.separator + "duke.txt";
    public static String pathFolder = "." +  File.separator + "data" + File.separator;
    public static File file = new File(path);
    
    public static void loadIntoDuke(List<Task> items) {
        try{
            if (!file.createNewFile()) {
                Scanner sc = new Scanner(file);
                while (sc.hasNextLine()) {
                    String task = sc.nextLine();
                    if (task.startsWith("T")) {
                        items.add(new ToDo(task.substring(8), Integer.parseInt(task.substring(4, 5))));
                    } else if (task.startsWith("D")) {
                        String taskSubstring = task.substring((8));
                        items.add(new Deadline(taskSubstring.substring(0, taskSubstring.indexOf("|")),
                                taskSubstring.substring(taskSubstring.indexOf("|") + 2),
                                Integer.parseInt(task.substring(4, 5))
                        ));
                    } else {
                        String taskSubstring = task.substring((8));
                        items.add(new Event(taskSubstring.substring(0, taskSubstring.indexOf("|")),
                                taskSubstring.substring(taskSubstring.indexOf("|") + 2),
                                Integer.parseInt(task.substring(4, 5))
                        ));
                    }
                }
            }
        } catch (Exception e){
            fixFileFolderProblems();
        }
    }
    
    public static void addNewTask(Task task) {
        try {
            String input = task.toStorageString();
            Files.write(Paths.get(path), input.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    public static void writeToFile(List<Task> items){
        try{
            FileWriter fw = new FileWriter(path, false);
            PrintWriter pw= new PrintWriter(fw, false);
            pw.flush();
            pw.close();
            fw.close();
            for (Task currentTask : items) {
                String input = currentTask.toStorageString();
                Files.write(Paths.get(path), input.getBytes(), StandardOpenOption.APPEND);
            }
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void fixFileFolderProblems(){
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
