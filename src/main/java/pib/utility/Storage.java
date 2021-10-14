package pib.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import pib.pibexception.PibException;
import pib.tasks.Deadline;
import pib.tasks.Event;
import pib.tasks.Task;
import pib.tasks.Todo;

/**
 * Class to handle loading the saved data and saving data to external file
 */
public class Storage {
    private File file;

    /**
     * Constructs a Storage object containing a file from the specified filePath
     *
     * @param filePath String to locate the stored data
     */
    public Storage(String filePath) throws PibException {
        assert filePath != null;
        assert !filePath.isBlank();
        this.file = new File(filePath);
        new File("./data").mkdirs();
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new PibException("io-exception");
        }
    }

    /**
     * Gets saved tasks from file located via the filePath and populates the TaskList
     *
     * @param list TaskList to add previously saved tasks to
     * @throws PibException when FileNotFoundException is thrown by system when trying to locate the saved data file
     */
    public String loadData(TaskList list) throws PibException {
        assert list != null;
        try {
            String response = "";
            Scanner sc = new Scanner(this.file);
            if (!sc.hasNext()) {
                return response.concat(Ui.printNoSavedDataFound());
            }
            response = response.concat(Ui.printDataLoading());
            while (sc.hasNext()) {
                Task newTask = addSavedDataToList(sc.nextLine());
                list.addSavedData(newTask);
            }
            return response.concat(Ui.printDataLoadSuccess()).concat(Ui.printList(list));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new PibException("fnf-exception");
        }
    }

    private Task addSavedDataToList(String rawData) {
        String[] taskDetails = rawData.split(",");
        Task newTask = null;
        switch (taskDetails[0]) {
        case "T":
            newTask = Todo.createTodo(taskDetails[2],
                    Integer.parseInt(taskDetails[1]), false);
            break;
        case "E":
            newTask = Event.createEvent(taskDetails[2],
                    Integer.parseInt(taskDetails[1]), taskDetails[3], taskDetails[4], false);
            break;
        case "D":
            newTask = Deadline.createDeadline(taskDetails[2],
                    Integer.parseInt(taskDetails[1]), taskDetails[3], taskDetails[4], false);
            break;
        default:
            break;
        }
        return newTask;
    }

    /**
     * Takes a TaskList and writes the data to a file to be saved
     *
     * @param list     TaskList containing tasks to be saved to the file
     * @param filePath path of file to be written to
     * @throws PibException when IOException is thrown by system
     */
    public static void saveData(TaskList list, String filePath) throws PibException {
        assert list != null;
        assert filePath != null;
        assert !filePath.isBlank();
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(list.convertListToSaveData());
            fw.close();
        } catch (IOException e) {
            throw new PibException("io-exception");
        }
    }
}
