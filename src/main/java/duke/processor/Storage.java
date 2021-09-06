package duke.processor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException; 

/**
 * Methods to save and load tasklists of duke to disk.
 * 
 * @author Tianqi-Zhu
 */
public class Storage {

    /**
     * Save the current taskList to disk.
     * 
     * @param taskList Current list of tasks.
     */
    public static void save(TaskList taskList) {
        File file =  new File("data/TaskList.ser");
        if (! file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                Ui.printString(e.toString());
            }
        }
        try {
            FileOutputStream fileOut = new FileOutputStream("data/TaskList.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(taskList);
            out.close();
            fileOut.close();
        } catch (IOException e) {
            Ui.printString(e.toString());
        }
    }

    /**
     * Load the taskList saved on disk. Called when Duke is started. 
     * 
     * @return The taskList stored on the disk.
     */
    public static TaskList load() {
        File file =  new File("data/TaskList.ser");
        if (! file.exists()) {
            return new TaskList(); 
        } else {
            try {
                FileInputStream fileIn = new FileInputStream("data/TaskList.ser");
                ObjectInputStream in = new ObjectInputStream(fileIn);
                try {
                    TaskList tasks = (TaskList) in.readObject();
                    in.close();
                    return tasks; 
                } catch (ClassNotFoundException e) {
                    in.close();
                    Ui.printString(e.getMessage());
                }
            } catch (IOException e) {
                Ui.printString(e.toString());
            }
        }
        return new TaskList();
    }
}
