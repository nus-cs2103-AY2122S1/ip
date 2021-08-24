package pib;

import pib.Tasks.Deadline;
import pib.Tasks.Event;
import pib.Tasks.Task;
import pib.Tasks.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private String filePath;
    private File file;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.file = new File(filePath);
    }

    public void loadData(TaskList list) {
        try {
            Scanner sc = new Scanner(this.file);
            while (sc.hasNext()) {
                String[] taskDetails = sc.nextLine().split(",");
                Task newTask = null;
                switch (taskDetails[0]) {
                case "T":
                    newTask = Todo.createTodo(taskDetails[2], Integer.parseInt(taskDetails[1]));
                    break;
                case "E":
                    newTask = Event.createEvent(taskDetails[2], Integer.parseInt(taskDetails[1]), taskDetails[3], taskDetails[4]);
                    break;
                case "D":
                    newTask = Deadline.createDeadline(taskDetails[2], Integer.parseInt(taskDetails[1]), taskDetails[3], taskDetails[4]);
                    break;
                default:
                    break;
                }
                if (newTask != null) {
                    list.addSavedData(newTask);
                }
            }
        } catch (FileNotFoundException e) {
            Ui.printError("fnf-exception");
        }
    }

    public static void saveData(TaskList list, String filePath) {
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(list.convertListToSaveData());
            fw.close();
        } catch (IOException e) {
            Ui.printError("io-exception");
        }
    }
}
