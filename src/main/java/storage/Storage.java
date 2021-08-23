package storage;

import exception.DukeException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Storage {

    // Default task file directory.
    private String taskDirectoryPath = "data";
    private String taskFilePath = taskDirectoryPath + "/duke.txt";
    
    // Error message templates.
    private static final String UNABLE_TO_CREATE_DIRECTORY = "Load/save directory ./%s cannot be created.";
    private static final String UNABLE_TO_CREATE_FILE = "Load/save file ./%s cannot be created.";
    private static final String UNABLE_TO_LOAD_PATH = "Path ./%s cannot be accessed/loaded.";
    private static final String INVALID_TASK_FORMAT = "'%s' is an invalid Task entry.";

    public void setFilePathAndDirectory(String taskFilePath) {
        setTaskFilePath(taskFilePath);
        int splitIdx = taskFilePath.lastIndexOf('/');
        setTaskDirectoryPath(taskFilePath.substring(splitIdx + 1));
    }

    public void setTaskDirectoryPath(String taskDirectoryPath) {
        this.taskDirectoryPath = taskDirectoryPath;
    }

    public void setTaskFilePath(String taskFilePath) {
        this.taskFilePath = taskFilePath;
    }

    public List<Task> load() throws DukeException {
        initialiseFilePath();
        return decode();
    }

    public void initialiseFilePath() throws DukeException {
        try {
            File directory = new File(taskDirectoryPath);
            if (!directory.exists() && !directory.mkdir()) {
                throw new DukeException(String.format(UNABLE_TO_CREATE_DIRECTORY, taskDirectoryPath));
            }

            File file = new File(taskFilePath);
            if (!file.exists() && !file.createNewFile()) {
                throw new DukeException(String.format(UNABLE_TO_CREATE_FILE, taskFilePath));
            }
            
        } catch (IOException exception) {
            throw new DukeException(String.format(UNABLE_TO_LOAD_PATH, taskFilePath));
        }
    }

    public List<Task> decode() throws DukeException {
        Path filePath;
        List<String> taskLines;
        List<Task> savedTaskList = new ArrayList<>();
        
        try {
            filePath = Paths.get(taskFilePath);
            taskLines = Files.readAllLines(filePath);
        } catch (IOException exception) {
            throw new DukeException(String.format(UNABLE_TO_LOAD_PATH, taskFilePath));
        }
        
        for (String stringTask: taskLines) {
            String splitRegex = " \\" + Task.SPLIT_CHAR + ' '; // split based on regex '|'
            String[] taskAsArray = stringTask.split(splitRegex);
            
            String keyword = taskAsArray[0];
            boolean isDone = taskAsArray[1].equals("1"); // if not 1, just set to false
            String desc = taskAsArray[2];
            LocalDate date;
            LocalTime time;

            switch (keyword) {
                case Todo.KEYWORD:
                    savedTaskList.add(new Todo(desc, isDone));
                    break;
                    
                case Event.KEYWORD:
                    date = LocalDate.parse(taskAsArray[3]);
                    time = (taskAsArray.length == 4) ? null : LocalTime.parse(taskAsArray[4]);
                    savedTaskList.add(new Event(desc, isDone, date, time));
                    break;
                    
                case Deadline.KEYWORD:
                    date = LocalDate.parse(taskAsArray[3]);
                    time = (taskAsArray.length == 4) ? null : LocalTime.parse(taskAsArray[4]);
                    savedTaskList.add(new Deadline(desc, isDone, date, time));
                    break;
                    
                default:
                    throw new DukeException(String.format(INVALID_TASK_FORMAT, stringTask));
            }
        }
        return savedTaskList;
    }

    public void saveToFile(Task newTask) throws IOException {
        FileWriter fileWriter = new FileWriter(taskFilePath, true);
        fileWriter.write(newTask.toEncodedString() + '\n');
        fileWriter.close();
    }

    public void updateToFile(int taskNumber, Task newTask) throws IOException {
        Path filePath = Paths.get(taskFilePath);
        List<String> taskLines = Files.readAllLines(filePath);
        taskLines.set(taskNumber - 1, newTask.toEncodedString()); // 0-indexing
        Files.write(filePath, taskLines);
    }

    public void removeFromFile(int taskNumber) throws IOException {
        Path filePath = Paths.get(taskFilePath);
        List<String> taskLines = Files.readAllLines(filePath);
        taskLines.remove(taskNumber - 1); // 0-indexing
        Files.write(filePath, taskLines);
    }
}
