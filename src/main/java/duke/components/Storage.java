package duke.components;
import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Storage {
    private final File dataFile;
    private final String filePath;
//    private final File dataFolder;
//    private final File dukeFolder;

    public Storage(String filePath) {
        this.dataFile = new File(filePath); // "./data/data.txt"
        this.filePath = filePath;
//        this.dukeFolder = new File(filePath.substring(0, 6)); // "./duke"
//        this.dataFolder = new File(filePath.substring(0, 11)); // "./duke/data"
    }

    public void loadInto(TaskList taskList) {

        String dataPath = "./data/data.txt";

        try {
            Path path = Paths.get(dataPath);
            Files.createDirectories(path.getParent());
            Files.createFile(path);
            System.out.println("Directories for data.txt is created.");
        } catch (FileNotFoundException e) {
            System.out.println("data.txt already exists.");
        } catch (IOException e) {
            System.out.println("Failed to create data directories" + e.getMessage());
        }

        // if duke folder does not exist, create duke folder, data folder and data file
//        if (!dukeFolder.exists()) {
//            dukeFolder.mkdir();
//            try {
//                File dataFolder = new File("./duke/data");
//                if (dataFolder.createNewFile()) {
//                    System.out.println("Data folder has been created.");
//                }
//            } catch (IOException e) {
//                System.out.println("An error occurred.");
//                e.printStackTrace();
//            }
//
//            try {
//                File dataFile = new File("./duke/data/data.txt");
//                if (dataFile.createNewFile()) {
//                    System.out.println("Data file has been created.");
//                }
//            } catch (IOException e) {
//                System.out.println("An error occurred.");
//                e.printStackTrace();
//            }
//        }
//
//        // if data folder does not exist, create folder and file
//        if (!dataFolder.exists()) {
//            dataFolder.mkdir();
//            try {
//                File dataDirectory = new File("./duke/data/data.txt");
//                if (dataDirectory.createNewFile()) {
//                    System.out.println("Data file has been created.");
//                } else {
//                    //
//                }
//            } catch (IOException e) {
//                System.out.println("An error occurred.");
//                e.printStackTrace();
//            }
//        }
//
//        // if data.txt does not exist, create file
//        if (!dataFile.exists()) {
//            try {
//                File dataDirectory = new File("./duke/data/data.txt");
//                if (dataDirectory.createNewFile()) {
//                    System.out.println("Data file has been created.");
//                } else {
//                    //
//                }
//            } catch (IOException e) {
//                System.out.println("An error occurred.");
//                e.printStackTrace();
//            }
//        }

        // a List to store all Tasks read from data.txt
        ArrayList<String> dataRead = new ArrayList<>();

        // if data.txt exists
        try {
            Scanner scanner = new Scanner(dataFile);
            while (scanner.hasNextLine()) {
                dataRead.add(scanner.nextLine());
            }
            for (int i = 0; i < dataRead.size(); i++) {
                String toAdd = dataRead.get(i);

                // case Todo
                if (toAdd.charAt(1) == 'T') {
                    String[] parts = toAdd.split("] ");
                    String todoName = parts[1];
                    Todo todoToAdd = new Todo(todoName);
                    if (toAdd.charAt(4) == 'X') {
                        todoToAdd.setDone();
                    }
                    taskList.addTaskFromDataFile(todoToAdd);
                }

                // case Deadline
                if (toAdd.charAt(1) == 'D') {
                    String[] parts1 = toAdd.split("] ");
                    String nameTime = parts1[1];
                    String[] parts2 = nameTime.split(" \\(by: ");
                    String ddlName = parts2[0];
                    String ddlTimeStr = parts2[1].substring(0, parts2[1].length() - 1);
                    DateTimeFormatter formatterDdl = DateTimeFormatter.ofPattern(
                            "EEE, dd/MMM/yyyy hh:mm a");
                    LocalDateTime dateTime = LocalDateTime.parse(ddlTimeStr, formatterDdl);
                    Deadline ddlToAdd = new Deadline(ddlName, dateTime);
                    if (toAdd.charAt(4) == 'X') {
                        ddlToAdd.setDone();
                    }
                    taskList.addTaskFromDataFile(ddlToAdd);
                }

                // case Event
                if (toAdd.charAt(1) == 'E') {
                    String[] parts1 = toAdd.split("] ");
                    String nameTime = parts1[1];
                    String[] parts2 = nameTime.split(" \\(at: ");
                    String eveName = parts2[0];
                    String eveTimeStr = parts2[1].substring(0, parts2[1].length() - 1);
                    DateTimeFormatter formatterEve = DateTimeFormatter.ofPattern(
                            "EEE, dd/MMM/yyyy hh:mm a");
                    LocalDateTime dateTime = LocalDateTime.parse(eveTimeStr, formatterEve);
                    Event eveToAdd = new Event(eveName, dateTime);
                    if (toAdd.charAt(4) == 'X') {
                        eveToAdd.setDone();
                    }
                    taskList.addTaskFromDataFile(eveToAdd);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("data.txt is not found." + e.getMessage());
        }
    }

    public void writeTaskToFile(String taskToWrite) {
        try {
            writeToFile(filePath, taskToWrite);
        } catch (IOException e) {
            System.out.println("Something is wrong... " + e.getMessage());
        }
    }

    public void appendTaskToFile(String taskToAppend) {
        try {
            appendToFile(filePath, taskToAppend);
        } catch (IOException e) {
            System.out.println("Something is wrong... " + e.getMessage());
        }
    }

    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    public void eraseFileContent() {
        try {
            new FileWriter(filePath, false).close();
        } catch (IOException e) {
            System.out.println("Something is wrong... " + e.getMessage());
        }
    }

}
