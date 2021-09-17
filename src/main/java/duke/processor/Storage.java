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

    /**￼
     * Save the current taskList to disk.
     * 
     * @param taskList Current list of tasks.
     */
    public static void save(TaskList taskList) {
        File file_dir = new File("data");
        if (! file_dir.exists()) {
            file_dir.mkdir();
        }
        File file = new File("data/TaskList.ser");
        if (! file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                assert 1 == 0 : "IO ERROR " + e.toString();
                e.printStackTrace();
            }
        }
        try {
            FileOutputStream fileOut = new FileOutputStream("data/TaskList.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(taskList);
            out.close();
            fileOut.close();
        } catch (IOException e) {
            assert 1 == 0 : "IO ERROR " + e.toString();
            e.printStackTrace();
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
                    return tasks; 
                } catch (ClassNotFoundException e) {
                    assert 1 == 0 : "IO ERROR " + e.toString();
                    e.printStackTrace();
                } finally {
                    in.close();
                }
            } catch (IOException e) {
                assert 1 == 0 : "IO ERROR " + e.toString();
                e.printStackTrace();
            }
        }
        return new TaskList();
    }
}
