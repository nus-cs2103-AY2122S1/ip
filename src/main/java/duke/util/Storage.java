package duke.util;

import duke.task.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
//import java.nio.file.Files;
//import java.nio.file.Path;
////import java.nio.file.Paths;
//import java.nio.file.*;
//import java.util.ArrayList;
//import java.util.stream.Stream;


public class Storage {
    private String filePath;
    private File dir;
    private File file;
    private FileWriter fw;


    public Storage(String filePathToStorageFile) {
        this.filePath = filePathToStorageFile;
        this.file = new File(this.filePath);
        this.dir = this.file.getParentFile(); //may be null
        //create directory and file if doesnt exist
        if (!this.dir.exists()) {
            createDir(this.dir);
        }
        if (!this.file.exists()) {
            createFile(this.file);
        }
    }

    public void createDir(File dir) {
        dir.mkdirs();
    }

    public void createFile(File file) {
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public File getFile() {
        return this.file;
    }

    public void write(String text)  {
        try {
            this.fw = new FileWriter(this.filePath);
            fw.write(text);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write(ArrayList<Task> tasks) {
        String text = tasks
                .stream()
                .map(Task::toStorageFormat)
                .reduce("",
                    (stringRes, stringTask) ->
                            stringRes + stringTask + System.getProperty("line.separator"));
        this.write(text);
    }

}
