package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    public List<String> lines = new ArrayList<String>();
    private File file;
    private Ui ui = new Ui();

    public TaskList(){
    }

    void initialise(File file,Storage storage){
        try {
            storage.listFileContents(lines, file.getPath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.file=file;
        //System.out.println("hi");
        for (String line:lines){
            System.out.println(line);
        }
    }

    void makeDone(Storage storage, int taskNo){
        String toBeDone = lines.get(taskNo);
        if(toBeDone.contains("[ ]")){
            toBeDone=toBeDone.substring(0,4)+"X"+toBeDone.substring(5);
            lines.set(taskNo,toBeDone);
            try {
                storage.writeListToFile(lines,file.getPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
            ui.showMarkedAsDone(toBeDone);
        }else{
            ui.showAlreadyDone();
        }
    }
}
