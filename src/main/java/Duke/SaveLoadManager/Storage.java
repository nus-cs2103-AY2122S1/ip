/**
 * @author Hang Zelin
 * @description Programme that allows Duke to save any changes after execution and read data when it is initially invoked.
 * In other words, programme allows Duke to save and read data from a file.
 */

package Duke.SaveLoadManager;

import Duke.Command.Parser;
import Duke.Excpetions.DukeException;
import Duke.Task.Task;
import Duke.Task.TaskList;

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
     * @author Hang Zelin
     *
     * @description Return the size of the TaskList
     *
     *
     * @param
     * @return ArrayList<Task></Task>
     * @throws DukeException
     */
    public ArrayList<Task> load() throws DukeException {
        try {
            ReadDataFromFile();
        } catch (FileNotFoundException e) {
            throw new DukeException("Cannot Read From Data.");
        }

        return this.list;
    }

    /**
     * @author Hang Zelin
     *
     * @description Allow users to read all the info of tasks stored in local files, and write them into TaskList.
     *
     * @param
     * @return void
     * @throws FileNotFoundException
     */
    public void ReadDataFromFile() throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f);
        int index = 1;
        while (s.hasNext()) {
            String Data = s.nextLine();
            char done = HandleTaskText(Data);
            if (done == '1') {
                this.list.get(index - 1).MarkDone();
            }
            index++;
        }
    }

    /**
     * @author Hang Zelin
     *
     * @description Allow users to save data from a TaskList to a specific file.
     *
     * @param Tasks
     * @return void
     * @throws IOException
     */
    public void SaveListDataToFile(TaskList Tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (int i = 0; i < Tasks.size(); i++) {
            fw.write(Tasks.get(i).getSaveDataInfo() + "\n");
        }
        fw.close();
    }


    /**
     * @author Hang Zelin
     *
     * @description return char which can be "0" or "1". The value indicates whether the task is done or not.
     * It also deals with the local file data and convert them into task and store into TaskList.
     *
     * @param Data
     * @return char
     */
    public char HandleTaskText(String Data) {
        Parser p = new Parser(Data);
        char done = Data.charAt(4);
        char taskType = Data.charAt(0);
        String task = "";
        String time = "";


        task = p.getSaveTask();
        time = p.getSaveTime();

        LocalDateTime parsedTime = p.ParseTime(time);
        TaskList.OperationType[] taskTypes = TaskList.OperationType.values();
        for (TaskList.OperationType t : taskTypes) {
            if (t.toString().toUpperCase().charAt(0) == taskType && (t.toString().equals("todo") || t.toString().equals("deadline") ||
                    t.toString().equals("event"))) {
                Task newTask = t.AssignTaskType(t, task, parsedTime);
                this.list.add(newTask);
                break;
            }
        }

        return done;
    }

}
