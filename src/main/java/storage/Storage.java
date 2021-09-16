package storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import model.task.TaskList;
import parser.Parser;

/**
 * Class for dealing with the storage system saving and loading save file.
 * Deal with reading and writing to file and can load TaskList from specified file.
 *
 * @author Kan Jitpakdi
 * @author GitHub: kanjitp
 * @version 0.03
 * @since 0.02
 */
public class Storage {

    /** the directory to store every information to be in alice */
    public static final String DIRECTORY_PATH = "./alice";
    /** the directory to store all the save file to be called data */
    public static final String DATA_PATH = "/data";

    /** writer for writing to file */
    protected static BufferedWriter writer;
    /** reader for reading the file */
    protected static BufferedReader reader;

    /** default name for the file */
    private static final String DEFAULT_FILE_NAME = "task_list";

    /** the filePath for the save file */
    protected String filePath;
    /** the taskList read from the save file */
    private TaskList taskListRead;


    /**
     * Constructor using default file location to use as storage.
     *
     * @throws IOException when there is error registering writer and reader to certain file location.
     */
    public Storage() throws IOException {
        this(DEFAULT_FILE_NAME);
    }

    /**
     * Constructor using the specified fileName as the name of the save file to read and write to.
     *
     * @param fileName the name of the file without file type suffix.
     * @throws IOException there is error registering writer and reader.
     */
    public Storage(String fileName) throws IOException {
        // the save file to be used as simple text file in this update
        this.filePath = DIRECTORY_PATH + DATA_PATH + "/" + fileName + ".txt";
        writer = new BufferedWriter(new FileWriter(filePath, true));
        reader = new BufferedReader(new FileReader(filePath));
    }

    /**
     * Check if within the data folder of alice contain the following fileName or not.
     *
     * @param fileName the file name of the file in data folder.
     * @return if the file with specified name exist or not.
     * @throws IOException if there is any error dealing with the system IO.
     */
    public static boolean contains(String fileName) throws IOException {
        String fullFileName = fileName + ".txt";

        if (!haveSaveLocation()) {
            createSaveLocation();
        }
        return new ArrayList<>(Arrays.stream(Storage.getFilesFromDirectory(Storage.DIRECTORY_PATH + Storage.DATA_PATH))
            .map(File::getName).collect(Collectors.toList())).contains(fullFileName);
    }

    /**
     * load taskList from where the reader and writer is currently at.
     *
     * @return TaskList read from the save file.
     */
    public TaskList loadTaskList() {

        taskListRead = new TaskList();

        reader.lines().forEach((line) -> {
            TaskList.TaskType type = Parser.stringToTaskType(line.substring(0, 2));
            int index1 = line.indexOf("|");
            String isDoneString = line.substring(index1 + 2, index1 + 3);
            int index2 = line.indexOf("|", index1 + 1);
            String description;
            String time = "";
            if (type == TaskList.TaskType.DEADLINE || type == TaskList.TaskType.EVENT) {
                int index3 = line.indexOf("|", index2 + 1);
                description = line.substring(index2 + 2, index3 - 1);
                time = line.substring(index3 + 2);
            } else {
                description = line.substring(index2 + 2);
            }

            TaskList.addTaskByType(taskListRead, type, isDoneString.equals("1"), description, time);
        });

        return taskListRead;
    }

    /**
     * Return folder from the specified file path.
     *
     * @param folderPath the folder path.
     * @return the folder as File.
     */
    public static File getFolderFromPath(String folderPath) {
        return new File(folderPath);
    }

    /**
     * Return an array of files from a directory from the file path.
     *
     * @param folderPath the file path of the directory.
     * @return File[] of the files in the folder including hidden files.
     */
    public static File[] getFilesFromDirectory(String folderPath) {
        return new File(folderPath).listFiles();
    }

    /**
     * Check if the directory to store the save file exist or not.
     *
     * @return whether the directory path and the folder for the save file exist or not.
     */
    public static boolean haveSaveLocation() {
        return java.nio.file.Files.exists(Paths.get(DIRECTORY_PATH))
            && java.nio.file.Files.exists(Paths.get(DIRECTORY_PATH + DATA_PATH));
    }

    /**
     * Create save location from the directory to the folder to store the save files.
     *
     * @throws IOException if there is any error dealing with the system IO.
     */
    public static void createSaveLocation() throws IOException {
        if (!haveSaveLocation()) {
            if (!java.nio.file.Files.exists(Paths.get(Storage.DIRECTORY_PATH))) {
                Files.createDirectory(Paths.get(Storage.DIRECTORY_PATH));
            }
            if (!java.nio.file.Files.exists(Paths.get(Storage.DIRECTORY_PATH + Storage.DATA_PATH))) {
                Files.createDirectory(Paths.get(Storage.DIRECTORY_PATH + Storage.DATA_PATH));
            }
        }
    }

    /**
     * Store the taskList into the save file the reader and writer are currently at.
     *
     * @param taskList the taskList to be stored.
     * @throws IOException if there is any error dealing with the system IO.
     */
    public void save(TaskList taskList) throws IOException {
        Path path = Paths.get(filePath);
        List<String> fileContent = taskList.getTasks().stream().map(Parser::taskToSaveFormat)
            .collect(Collectors.toList());
        Files.write(path, fileContent, StandardCharsets.UTF_8);
        reader.close();
        writer.close();
    }

    /**
     * Static method to delete save file in alice/data
     *
     * @param fileName file name without ".filetype"
     */
    public static void deleteFile(String fileName) {
        try {
            Path path = Paths.get(DIRECTORY_PATH + DATA_PATH + "/" + fileName + ".txt");
            Files.deleteIfExists(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
