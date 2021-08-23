package duke;

import java.io.File;
import java.io.IOException; 
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Storage {
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
            Ui.printString(e.getMessage());
        }
    }

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
                Ui.printString(e.getMessage());
            }
        }
        return new TaskList();
    }
}
