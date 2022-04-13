package hyddd.saveloadmanager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import hyddd.exceptions.ExceptionType;
import hyddd.exceptions.HydddException;
import hyddd.logics.Parser;
import hyddd.task.Task;
import hyddd.task.TaskList;

/**
 * @@author Hang Zelin
 *
 * Programme that allows hyddd to save any changes after execution and read data when it is initially invoked.
 * In other words, programme allows hyddd to save and read data from a file.
 */
public class Storage {
    private final String filePath;
    private final ArrayList<Task> list;

    /**
     * Initializes filePath and the TaskList for Storage to read data.
     *
     * @param filePath FilePath of the hyddd data storage.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        this.list = new ArrayList<>();
    }

    /**
     * Returns a List of Tasks loaded from the local file.
     *
     * @return TaskList loaded from the local file.
     * @throws HydddException Throws when the file cannot be loaded.
     */
    public ArrayList<Task> load() throws HydddException {
        try {
            readDataFromFile();
        } catch (FileNotFoundException e) {
            throw new HydddException(ExceptionType.FILE_READ_ERROR);
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
            String data = s.nextLine();
            char done;
            createATask(data);
            done = returnIsDone(data);
            if (done == '1') {
                this.list.get(index - 1).markDone();
            }
            index++;
        }
    }

    /**
     * Allows users to save data from a TaskList to a specific file.
     *
     * @param tasks TaskList tobe saved into the local file in the filePath.
     * @throws IOException Throws when data cannot be written into local file.
     */
    public void saveListDataToFile(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (int i = 0; i < tasks.size(); i++) {
            fw.write(tasks.get(i).getSaveDataInfo() + "\n");
        }
        fw.close();
    }

    private void createATask(String data) {
        Parser p = new Parser(data);
        char taskType = data.charAt(0);
        String task;
        String time;

        task = p.getSaveTask();
        time = p.getSaveTime();

        LocalDateTime parsedTime = p.parseTime(time);
        TaskList.OperationType[] taskTypes = TaskList.OperationType.values();
        for (TaskList.OperationType t : taskTypes) {
            boolean isCorrectType = t.toString().charAt(0) == taskType;
            boolean isTaskType = t.toString().equals("TODO") || t.toString().equals("DEADLINE")
                    || t.toString().equals("EVENT");

            if (isCorrectType && isTaskType) {
                Task newTask = t.assignTaskType(t, task, parsedTime);
                this.list.add(newTask);
            }
        }
    }

    /**
     * Returns char for "0" or "1". The value indicates whether the task is done or not.
     * It also deals with the local file data and convert them into task and store into TaskList.
     *
     * @param data A line of command in the save file to be parsed.
     * @return a Char indicates if the task is done or not.
     */
    private char returnIsDone(String data) {
        char done = data.charAt(4);
        return done;
    }
}
