package duke;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import duke.command.CommandKeyword;
import duke.task.Task;

/**
 * Deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    private File tasksFile;
    private File commandsFile;

    /**
     * Constructs a storage to store tasks. It takes in an Ui instance.
     */
    public Storage() {
        try {
            String dir = System.getProperty("user.dir");
            Path path = Paths.get(dir, "data");

            // create the data folder if it does not exist
            if (!Files.exists(path)) {
                path = Files.createDirectory(path);
            }

            File tasksFile = path.resolve("duke.txt").toFile();
            File commandsFile = path.resolve("commands.txt").toFile();
            this.tasksFile = tasksFile;
            this.commandsFile = commandsFile;
            if (!this.tasksFile.exists()) {
                this.tasksFile.createNewFile();
            }
            if (!this.commandsFile.exists()) {
                this.commandsFile.createNewFile();
                this.createDefaultCommands(this.commandsFile);
            }
            assert this.tasksFile != null && this.commandsFile != null : "File not created";
        } catch (IOException e) {
            // this exception should not occur because the input(path) is fixed
            assert false : e.getMessage();
        }
    }

    /**
     * Loads tasks from the file and add each line to an arraylist.
     *
     * @return An arraylist of strings, each representing a task information.
     */
    public ArrayList<String> loadTasks() throws IOException {
        try {
            Scanner sc = new Scanner(this.tasksFile);
            ArrayList<String> tasksInfo = new ArrayList<>();
            while (sc.hasNextLine()) {
                tasksInfo.add(sc.nextLine());
            }
            return tasksInfo;
        } catch (IOException e) {
            throw e;
        }
    }

    /**
     * Saves tasks in the file according to the arraylist of tasks given.
     *
     * @param tasks An arraylist of tasks to be saved.
     */
    public void saveTasks(ArrayList<Task> tasks) {
        if (tasks != null) {
            try {
                FileWriter fw = new FileWriter(this.tasksFile);
                for (int i = 0; i < tasks.size(); i++) {
                    String taskInfo = tasks.get(i).stringToStore();
                    fw.write(taskInfo);
                }
                fw.flush();
                fw.close();
            } catch (IOException e) {
                System.out.println("Failed to update storage.");
            }
        } else {
            // there is no task to save
        }
    }

    /**
     * Creates a file with a hashmap with default commands.
     *
     * @param file The file to store the hashmap of commands.
     */
    public void createDefaultCommands(File file) {
        HashMap<String, CommandKeyword> map = new HashMap<>();
        map.put("TODO", CommandKeyword.TODO);
        map.put("T", CommandKeyword.TODO);
        map.put("DEADLINE", CommandKeyword.DEADLINE);
        map.put("D", CommandKeyword.DEADLINE);
        map.put("EVENT", CommandKeyword.EVENT);
        map.put("E", CommandKeyword.EVENT);
        map.put("DELETE", CommandKeyword.DELETE);
        map.put("DEL", CommandKeyword.DELETE);
        map.put("DONE", CommandKeyword.DONE);
        map.put("BYE", CommandKeyword.BYE);
        map.put("FIND", CommandKeyword.FIND);
        map.put("F", CommandKeyword.FIND);
        map.put("LIST", CommandKeyword.LIST);
        map.put("L", CommandKeyword.LIST);

        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(map);
            oos.flush();
            oos.close();
            fos.close();
        } catch (Exception e) {
            //
        }
    }

    /**
     * Loads the commands from the file and return the hashmap.
     *
     * @return The hashmap containing keys of the commands user can input.
     */
    public HashMap<String, CommandKeyword> loadCommands() {
        try {
            FileInputStream fis = new FileInputStream(this.commandsFile);
            ObjectInputStream ois = new ObjectInputStream(fis);

            @SuppressWarnings("unchecked")
            // It is safe to typecast because what is added to the file is a hashmap.
            HashMap<String, CommandKeyword> mapInFile = (HashMap<String, CommandKeyword>) ois.readObject();
            ois.close();
            fis.close();
            return mapInFile;
        } catch (Exception e) {
            return null;
        }
    }
}
