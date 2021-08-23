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
     * Load store file as a TaskArrayList
     * @param path path to store file
     * @return TaskArrayList of stored tasks
     */
    public static TaskArrayList load( Path path){
        createStore(path);
        TaskArrayList taskList =  new TaskArrayList();
        try {
            Scanner sc = new Scanner(path);
            Parser parser = new Parser(sc);
            while (parser.hasNextLine()) {
                try {
                    taskList.add(parser.getNextTask());
                } catch (DukeException e){
                    System.out.println(e);
                }


            }
        } catch (IOException e){

        }

        return taskList;
    }

    /**
     * Dump a taskList to a store file
     * @param taskList TaskArrayList to store
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
