package Duke;

import Duke.task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Scanner;

public class Storage {
    private final String filePath;
    private final File file;

    public Storage(String filePath) throws IOException {
        this.filePath = filePath;
        this.file = new File(filePath);
        if(!file.exists()){
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
    }


    public TaskList readList() throws FileNotFoundException {
        Scanner s = new Scanner(file);
        TaskList list = new TaskList();
        while (s.hasNext()) {
            String[] arr = s.nextLine().split(" \\| ", 4);
            String taskType = arr[0];
            switch(taskType){
                case "T":
                    Task t = new Todo(arr[2]);
                    if(arr[1]== "1"){
                        t.markComplete();
                    }
                    list.addTask(t);
                    break;
                case "D":
                    Task d = new Deadline(arr[2],arr[3]);
                    if(arr[1]== "1"){
                        d.markComplete();
                    }
                    list.addTask(d);
                    break;
                case "E":
                    Task e = new Event(arr[2],arr[3]);
                    if(arr[1]== "1"){
                        e.markComplete();
                    }
                    list.addTask(e);
                    break;
                default:
                    break;
            }
        }
        return list;
    }

    public void writeList(TaskList list) throws IOException{
        FileWriter fw = new FileWriter(filePath);
        fw.write(list.toStorageString());
        fw.close();
    }
}
