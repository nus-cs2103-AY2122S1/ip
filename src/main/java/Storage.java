import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private File file;

    public Storage(File file){
        this.file = file;
    };

    public static Storage initStorage(String directory, String filePath) throws DukeException{
        try {
            Files.createDirectories(Paths.get("data/"));
            File file = new File("data/duke.txt");

            //Check if the file exists
            if (!file.exists()) {
                boolean r = file.createNewFile();
            }
            return new Storage(file);
        }
        catch(IOException e){
            throw new DukeException("☹ OOPS!!! Error occurs when try to create a new data file");
        }
    }

    public void saveToFile(TaskList taskList) throws DukeException {
        try {
            FileWriter fw = new FileWriter(file, false);
            for(int i=0; i<taskList.size(); i++) {
                Task task= taskList.get(i);
                fw.write(task.saveTask());
                fw.write("\r\n");
            }
            fw.close();
        }
        catch(IOException e) {
            throw new DukeException("☹ OOPS!!! Error occurs when save the data");
        }
    }

    public void readFile(TaskList taskList) throws DukeException{
        try {
            Scanner s = new Scanner(file);
            String current, desc, name;
            LocalDate time;
            char type;
            while(s.hasNext()) {
                current = s.nextLine();
                type = current.charAt(1);
                if(type == 'T') {
                    Task newTask = new Todo(current.substring(7));
                    newTask.setCompleted(current.charAt(4)=='X');
                    taskList.add(newTask);
                }
                else if(type == 'E') {
                    desc = current.substring(7);
                    String[] split = desc.split("->at: ", 2);
                    name = split[0];
                    time = LocalDate.parse(split[1]);
                    Task newTask = new Event(name, time);
                    newTask.setCompleted(current.charAt(4)=='X');
                    taskList.add(newTask);
                }
                else {
                    desc = current.substring(7);
                    String[] split = desc.split("->by: ", 2);
                    name = split[0];
                    time = LocalDate.parse(split[1]);
                    Task newTask = new Deadline(name, time);
                    newTask.setCompleted(current.charAt(4)=='X');
                    taskList.add(newTask);
                }
            }
        }
        catch(IOException e) {
            throw new DukeException("OOPS!!! Error occurs when reload the data");
        }
    }

}
