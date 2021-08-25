package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    public static List<String> lines;
    private Ui ui = new Ui();

    public TaskList(){
    }

    public boolean initialise(File file,Storage storage){
        try {
            storage.listFileContents(file.getPath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    void makeDone(Storage storage, int taskNo){
        String toBeDone = lines.get(taskNo);
        if(toBeDone.contains("[ ]")){
            toBeDone=toBeDone.substring(0,4)+"X"+toBeDone.substring(5);
            lines.set(taskNo,toBeDone);
            try {
                storage.writeListToFile(Duke.file.getPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
            ui.showMarkedAsDone(toBeDone);
        }else{
            ui.showAlreadyDone();
        }
    }

    void delete(Storage storage, int taskNo){
        String toBeDeleted=TaskList.lines.get(taskNo);
        TaskList.lines.remove(taskNo);
        try {
            storage.writeListToFile(Duke.file.getPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        ui.showDeletionMsg(toBeDeleted,TaskList.lines.size());
    }

    void add(Storage storage, String toBeAdded){
        if(!TaskList.lines.contains(toBeAdded)){
            TaskList.lines.add(toBeAdded);
            ui.showTaskAdded(toBeAdded);
            ui.showListSize(TaskList.lines.size());
        }else{
            ui.showAlreadyInList(toBeAdded);
        }
    }
}
