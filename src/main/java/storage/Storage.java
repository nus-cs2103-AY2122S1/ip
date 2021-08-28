package storage;

import dialog.DialogException;
import parser.Parser;
import task.TaskList;
import ui.Ui;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Storage {

    public static final String DIRECTORY_PATH = "./alice";
    public static final String DATA_PATH = "/data";
    private static final String DEFAULT_FILE_NAME = "task_list";

    private String filePath;
    private TaskList taskListRead;



    public static BufferedWriter WRITER;
    public static BufferedReader READER;

    public Storage() throws DialogException {
        this(DEFAULT_FILE_NAME);
    }

    public Storage(String fileName) throws DialogException {

        try {
            if (!haveSaveLocation()) {
                createSaveLocation();
            }
            this.filePath = DIRECTORY_PATH + DATA_PATH +  "/" + fileName + ".txt";
            WRITER = new BufferedWriter(new FileWriter(filePath, true));
            READER = new BufferedReader(new FileReader(filePath));
        } catch (Exception e) {
            Ui.printError(e);
        }
    }

    public static boolean contains(String fileName) throws IOException {
        String full_file_name = fileName + ".txt";

        if (!haveSaveLocation()) {
            createSaveLocation();
        }
        return new ArrayList<>(Arrays.stream(Storage.getFilesFromDirectory(Storage.DIRECTORY_PATH + Storage.DATA_PATH))
                    .map(File::getName).collect(Collectors.toList())).contains(full_file_name);
    }

    public TaskList load() {

        taskListRead = new TaskList();

        READER.lines().forEach((line) -> {
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

    public static File getFolderFromPath(String filePath) {
        return new File(filePath);
    }

    public static File[] getFilesFromDirectory(String filePath) {
        return new File(filePath).listFiles();
    }

    public static boolean haveSaveLocation() {
        return java.nio.file.Files.exists(Paths.get(DIRECTORY_PATH)) && java.nio.file.Files.exists(Paths.get(DIRECTORY_PATH + DATA_PATH));
    }


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

    public void save(TaskList taskList) throws IOException {
        Path path = Paths.get(filePath);
        List<String> fileContent = taskList.getTasks().stream().map(Parser::taskToSaveFormat).collect(Collectors.toList());
        Files.write(path, fileContent, StandardCharsets.UTF_8);
        READER.close();
        WRITER.close();
    }
}
