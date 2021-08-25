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

/**
 * Class to handle loading the saved data and saving data to external file
 */
public class Storage {
    private String filePath;
    private File file;

    /**
     * Public constructor to instantiate a Storage object containing a file from the specified filePath
     *
     * @param filePath String to locate the stored data
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        this.file = new File(filePath);
    }

    /**
     * Gets saved tasks from file located via the filePath and populates the TaskList
     *
     * @param list TaskList to add previously saved tasks to
     * @throws PibException when FileNotFoundException is thrown by system when trying to locate the saved data file
     */
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

    /**
     * Takes a TaskList and writes the data to a file to be saved
     *
     * @param list     TaskList containing tasks to be saved to the file
     * @param filePath path of file to be written to
     * @throws PibException when IOException is thrown by system
     */
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
