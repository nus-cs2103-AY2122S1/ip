package pib;

import pib.pibexception.PibException;
import pib.tasks.Deadline;
import pib.tasks.Event;
import pib.tasks.Task;
import pib.tasks.Todo;

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

    public void loadData(TaskList list) throws PibException {
        try {
            Scanner sc = new Scanner(this.file);
            Ui.printDataLoading();
            while (sc.hasNext()) {
                String[] taskDetails = sc.nextLine().split(",");
                Task newTask = null;
                switch (taskDetails[0]) {
                case "T":
                    newTask = Todo.createTodo(taskDetails[2], Integer.parseInt(taskDetails[1]), false);
                    break;
                case "E":
                    newTask = Event.createEvent(taskDetails[2], Integer.parseInt(taskDetails[1]), taskDetails[3], taskDetails[4], false);
                    break;
                case "D":
                    newTask = Deadline.createDeadline(taskDetails[2], Integer.parseInt(taskDetails[1]), taskDetails[3], taskDetails[4], false);
                    break;
                default:
                    break;
                }
                if (newTask != null) {
                    list.addSavedData(newTask);
                }
            }
            Ui.printDataLoadSuccess();
            Ui.printList(list);
        } catch (FileNotFoundException e) {
            throw new PibException("fnf-exception");
        }
    }

    public static void saveData(TaskList list, String filePath) throws PibException {
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(list.convertListToSaveData());
            fw.close();
        } catch (IOException e) {
            throw new PibException("io-exception");
        }
    }
}
