package Duke;

import Duke.task.TaskList;

import java.io.File;
import java.io.IOException;

public class Storage {
    File file;

    public Storage(String filePath) throws IOException {
        file = new File(filePath);
        if(!file.exists()){
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
    }


//    public TaskList readList(){
//        return
//    }

    public void saveList(TaskList list){

    }
}
