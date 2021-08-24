package Duke;

import Duke.task.TaskList;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

public class Storage {
    private String filePath;
    private File file;

    public Storage(String filePath) throws IOException {
        this.filePath = filePath;
        this.file = new File(filePath);
        if(!file.exists()){
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
    }


//    public TaskList readList(){
//        return
//    }

    public void writeList(TaskList list) throws IOException{
        FileWriter fw = new FileWriter(filePath);
        fw.write(list.toStorageString());
        fw.close();
    }
}
