package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

public class Storage {

    private String filePath;

    /**
     * Constructor for Storage.
     * @param filePath String of the file path leading to the data storage file
     */
    public Storage(String filePath) {
        this.filePath = filePath;

    }

    /**
     * Parses a line of input from the data storage, creates the corresponding task
     * and adds it to the taskList.
     *
     * @param input String input from data storage
     * @param taskList ArrayList of Tasks that contains the tasks
     * @throws DukeException when invalid data exists in the data storage
     */
    private static void parseAndCreateTask(String input, ArrayList<Task> taskList) throws DukeException {
        String[] inputArr = input.split(",");
        Task currentTask;
        switch (inputArr[0]) {
        case "T" :
            currentTask = new Todo(inputArr[2]);
            if (inputArr[1].equals("true")) {
                currentTask.markAsDone();
            }
            taskList.add(currentTask);
            break;

        case "E" :
            currentTask = new Event(inputArr[3], inputArr[1]);
            if (inputArr[2].equals("true")) {
                currentTask.markAsDone();
            }
            taskList.add(currentTask);
            break;

        case "D" :
            currentTask = new Deadline(inputArr[3], inputArr[1]);
            if (inputArr[2].equals("true")) {
                currentTask.markAsDone();
            }
            taskList.add(currentTask);
            break;

        default:
            throw new DukeException("invalid data");
        }

    }

    /**
     * Returns an ArrayList containing the Tasks retrieved from
     * the data file.
     * @return ArrayList containing Tasks retrieved from data file
     * @throws DukeException
     */
    public ArrayList<Task> loadData() throws DukeException {
        File dataFile = new File(this.filePath);
        ArrayList<Task> taskList = new ArrayList<>();
        try (Scanner fileScanner = new Scanner(dataFile);) {

            while (fileScanner.hasNext()) {
                parseAndCreateTask(fileScanner.nextLine(), taskList);
            }

        } catch (FileNotFoundException fileException) {
            try {
                File parentDir = new File(dataFile.getParent());

                if (!parentDir.exists()) {
                    parentDir.mkdir();
                }

                dataFile.createNewFile();
            } catch (IOException ioException) {
                throw new DukeException("failed database creation");
            }
        }
        return taskList;
    }

    /**
     * Saves the tasks in the given TaskList into data file.
     * @param taskList containing tasks to be saved
     * @throws DukeException
     */
    public void saveData(TaskList taskList) throws DukeException {
        try (FileWriter fw = new FileWriter(this.filePath)) {
            StringBuilder dataString = new StringBuilder();

            for (int i = 0; i < taskList.size(); i++) {
                Task currentTask = taskList.get(i);
                dataString.append(currentTask.getData() + "\n");
            }

            fw.write(dataString.toString());

        } catch (IOException e) {
            throw new DukeException("failed data save");
        }
    }
}
