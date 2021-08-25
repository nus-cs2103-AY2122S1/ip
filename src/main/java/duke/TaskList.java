package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the list of strings Duke uses to make changes to the list file.
 */
public class TaskList {
    public static List<String> lines;
    private Ui ui = new Ui();

    public TaskList(){
    }

    /**
     * Initialises the list and loads the existing contents of the task list file,
     * with each line at a separate index.
     * @param file File representing the list file.
     * @return True if initialisation was successful, false if the list file is missing.
     */
    public boolean initialise(File file,Storage storage){
        try {
            storage.listFileContents(file.getPath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Marks a task as done in the list of strings and the task list file.
     * @param storage Storage object used by Duke to assist with file handling.
     * @taskNo Index of the task that is being marked as done in the list.
     */
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

    /**
     * Deletes a task from the list of strings and the task list file.
     * @param storage Storage object used by Duke to assist with file handling.
     * @param taskNo Index of the task that is being deleted in the list.
     */
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

    /**
     * Adds a task to the list of strings and the task list file.
     * @param storage Storage object used by Duke to assist with file handling.
     * @param toBeAdded Task that is being added.
     */
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
