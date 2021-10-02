package duke.storage;

import duke.task.Deadline;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.task.Task;
import duke.task.Event;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


public class StorageHandler {
    private static final String DATA_FOLDER_PATH = "./data";
    private static final String TASK_LIST_PATH = "./TaskList.txt";

    private static final String path = Paths.get(DATA_FOLDER_PATH,
            TASK_LIST_PATH).normalize().toString();
    private static String fileNewLine = System.lineSeparator();

    /**
     * Parses a string to create a Task
     *
     * @return Task Corresponding to the string
     */
    private static Task parseTaskString(String data) {
        char taskType = 'x';
        char done = ' ';
        String description = "";
        String dateString = "";
        taskType = data.charAt(1);
        done = data.charAt(4);
        if(taskType == 'E' || taskType == 'D') {
            int slashIndex = taskType == 'E'
                    ? data.indexOf("(at:")
                    : data.indexOf("(by:");
            description = data.substring(7, slashIndex - 1);
            dateString = data.substring(slashIndex + 5, data.length() - 1);
        } else {
            description = data.substring(7);
        }
        Task task;
        if (taskType == 'E') {
            task = new Event(description, dateString);
            if (done == 'X')
                task.markAsDone();
            return task;
        } else if (taskType == 'D') {
            LocalDate deadlineDate = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("MMM dd yyyy"));
            task = new Deadline(description, deadlineDate);
            if (done == 'X')
                task.markAsDone();
            return task;
        } else {
            task = new ToDo(description);
            if (done == 'X')
                task.markAsDone();
            return task;
        }
    }
    /**
     * Loads list of tasks from TaskList.txt file into TaskList.
     *
     * @return boolean True indicates that the data was successfully loaded, and vice-verso.
     */
    public static boolean loadAll() {
        try {
            Path dataFolderPath = Paths.get(DATA_FOLDER_PATH);
            if (Files.notExists(dataFolderPath)) {
                dataFolderPath = Files.createDirectory(dataFolderPath);
            }

            Path taskListPath = Paths.get(dataFolderPath.toString(),
                    TASK_LIST_PATH);

            File taskListCSV = taskListPath.toFile();
            boolean fileExists = taskListCSV.createNewFile();
            if (fileExists) {
                //new file created
                //do nothing
            } else {
                //file was there alr
                //load data into TaskList
                Scanner myReader = new Scanner(taskListCSV);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    TaskList.getTaskList().addTask(parseTaskString(data));
                }
                myReader.close();
            }
        } catch (IOException E) {
            return false;
        }

        return true;
    }

    /**
     * Inserts a list of tasks from TaskList to TaskList.txt.
     *
     * @return boolean True indicates that the data was successfully transferred, and vice-verso.
     */
    public static boolean insertTasks() {
        String linebreak = System.lineSeparator();
        boolean rewrite = true;
        for(Task task: TaskList.getTaskList().getTasks()) {
            try {
                if(rewrite) {
                    FileWriter myWriter = new FileWriter(path);
                    myWriter.write(task.getTaskString());
                    myWriter.write(linebreak);
                    myWriter.close();
                    rewrite = false;
                } else {
                    FileWriter myWriter = new FileWriter(path, true);
                    myWriter.write(task.getTaskString());
                    myWriter.write(linebreak);
                    myWriter.close();
                }

            } catch (IOException e) {
                return false;
            }
        }
        return true;
    }
}
