/**
 * @author Hang Zelin
 *
 * Programme that allows Duke to save any changes after execution and read data when it is initially invoked.
 * In other words, programme allows Duke to save and read data from a file.
 */

package duke.saveloadmanager;

import duke.command.Parser;
import duke.excpetions.DukeException;
import duke.task.Task;
import duke.task.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    String filePath;

    private ArrayList<Task> list;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.list = new ArrayList<>();
    }

    /**
     * Returns a List of Tasks loaded from the local file.
     *
     * @return ArrayList<Task> TaskList loaded from the local file.
     * @throws DukeException Throws when the file cannot be loaded.
     */
    public ArrayList<Task> load() throws DukeException {
        try {
            readDataFromFile();
        } catch (FileNotFoundException e) {
            throw new DukeException("Cannot Read From Data.");
        }

        return this.list;
    }

    /**
     * Allows users to read all the info of tasks stored in local files, and write them into TaskList.
     *
     * @throws FileNotFoundException Throws when the file cannot be found.
     */
    public void readDataFromFile() throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f);
        int index = 1;
        while (s.hasNext()) {
            String Data = s.nextLine();
            char done = handleTaskText(Data);
            if (done == '1') {
                this.list.get(index - 1).markDone();
            }
            index++;
        }
    }

    /**
     * Allows users to save data from a TaskList to a specific file.
     *
     * @param Tasks TaskList tobe saved into the local file in the filePath.
     * @throws IOException Throws when data cannot be written into local file.
     */
    public void saveListDataToFile(TaskList Tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (int i = 0; i < Tasks.size(); i++) {
            fw.write(Tasks.get(i).getSaveDataInfo() + "\n");
        }
        fw.close();
    }

    /**
     * Returns char for "0" or "1". The value indicates whether the task is done or not.
     * It also deals with the local file data and convert them into task and store into TaskList.
     *
     * @param Data A line of command in the save file to be parsed.
     * @return a Char indicates if the task is done or not.
     */
    public char handleTaskText(String Data) {
        Parser p = new Parser(Data);
        char done = Data.charAt(4);
        char taskType = Data.charAt(0);
        String task;
        String time;


        task = p.getSaveTask();
        time = p.getSaveTime();

        LocalDateTime parsedTime = p.parseTime(time);
        TaskList.OperationType[] taskTypes = TaskList.OperationType.values();
        for (TaskList.OperationType t : taskTypes) {
            if (t.toString().toUpperCase().charAt(0) == taskType && (t.toString().equals("todo") || t.toString().equals("deadline") ||
                    t.toString().equals("event"))) {
                Task newTask = t.assignTaskType(t, task, parsedTime);
                this.list.add(newTask);
                break;
            }
        }

        return done;
    }
}
