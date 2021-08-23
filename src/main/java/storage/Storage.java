package storage;

import exception.DukeException;
import exception.StorageException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<Task> load() throws StorageException {
        initialiseFilePath();
        List<String> taskLines;
        try {
            taskLines = readLines();
        } catch (ArrayIndexOutOfBoundsException exception) {
            throw new StorageException(String.format(UNABLE_TO_LOAD_PATH, taskFilePath));
        }
        return decode(taskLines);
    }

    public void initialiseFilePath() throws StorageException {
        try {
            File directory = new File(taskDirectoryPath);
            if (!directory.exists() && !directory.mkdir()) {
                throw new StorageException(String.format(UNABLE_TO_CREATE_DIRECTORY, taskDirectoryPath));
            }
            File file = new File(taskFilePath);
            if (!file.exists() && !file.createNewFile()) {
                throw new StorageException(String.format(UNABLE_TO_CREATE_FILE, taskFilePath));
            }
        } catch (IOException exception) {
            throw new StorageException(String.format(UNABLE_TO_LOAD_PATH, taskFilePath));
        }
    }
    
    public List<String> readLines() throws StorageException {
        try {
            Path filePath = Paths.get(taskFilePath);
            return Files.readAllLines(filePath);
        } catch (IOException exception) {
            throw new StorageException(String.format(UNABLE_TO_LOAD_PATH, taskFilePath));
        }
    }

    public List<Task> decode(List<String> taskLines) throws StorageException {
        List<Task> savedTaskList = new ArrayList<>();
        for (String stringTask: taskLines) {
            String[] taskAsArray = stringTask.split(Task.SPLIT_TEMPLATE);
            
            String keyword = taskAsArray[0];
            boolean isDone = taskAsArray[1].equals(Task.DONE);
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
                    throw new StorageException(String.format(INVALID_TASK_FORMAT, stringTask));
            }
        }
        return savedTaskList;
    }

    public void save(List<Task> taskList) throws StorageException {
        try {
            Path filePath = Paths.get(taskFilePath);
            List<String> taskLines = taskList.stream()
                    .map(Task::toEncodedString)
                    .collect(Collectors.toList());
            Files.write(filePath, taskLines);
        } catch (IOException exception) {
            throw new StorageException(String.format(UNABLE_TO_LOAD_PATH, taskFilePath));
        }
    }
}
