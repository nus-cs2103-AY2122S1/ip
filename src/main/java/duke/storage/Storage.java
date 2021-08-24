package duke.storage;

import duke.TaskArrayList;
import duke.exceptions.DukeException;
import duke.exceptions.DukeReadSaveException;
import duke.tasks.Task;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;


public class Storage {
    /**
     * Create store file if required
     * @param path path to store file
     */
    public static void createStore(Path path){
        if (Files.exists(path)){
            return;
        }
        try{
            Files.createDirectories(path.getParent());
            Files.createFile(path);
        } catch (Exception e){
            System.out.println("failed to make store : " + e.toString());
        }
    }

    /**
     * Load store file as a duke.TaskArrayList
     * @param path path to store file
     * @return duke.TaskArrayList of stored duke.tasks
     */
    public static TaskArrayList load(Path path) throws DukeException,IOException{
        createStore(path);
        TaskArrayList taskList =  new TaskArrayList();
        Scanner sc = new Scanner(path);
        SaveParser saveParser = new SaveParser(sc);
        while (saveParser.hasNextLine()) {
            try {
                taskList.add(saveParser.getNextTask());
            } catch (DukeReadSaveException e) {
                // skip corrupted save block
            }
        }
        return taskList;
    }

    /**
     * Dump a taskList to a store file
     * @param taskList duke.TaskArrayList to store
     * @param path location to store taskList
     */
    public static void dump(TaskArrayList taskList,Path path){
        createStore(path);
        ArrayList<String> f = new ArrayList<>();
        for (Task task : taskList){
            String toWrite = "" ;
            f.add(task.serialize());
        }
        try {
            Files.write(path, f);
        } catch (IOException e){
            System.out.println("error writing data");
        }
        return;
    }
}
