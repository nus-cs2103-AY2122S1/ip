package winston;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * A class that manages storage for the bot.
 */
public class Storage {
    private final java.nio.file.Path path;

    /**
     * Constructor for class Storage.
     */
    public Storage() {
        String pathToThisFile = System.getProperty("user.dir");
        Path dataDir = Paths.get(pathToThisFile, "data");
        final boolean isDirectoryExist = Files.exists(dataDir);
        path = java.nio.file.Paths.get(pathToThisFile, "data", "winston.txt");
        try {
            if (isDirectoryExist) {
                if (Files.exists(path)) {
                    readFromFile();
                } else {
                    Files.createFile(path);
                }
            } else {
                Files.createDirectory(java.nio.file.Paths.get(pathToThisFile, "data"));
                Files.createFile(path);
            }
        } catch (FileAlreadyExistsException e) {
            System.out.println("Something went wrong!");
        } catch (IOException e) {
            System.out.println("Something unexpected happened during the creation of files!");
        }
    }

    /**
     * Method used to readFromFile the file if it exists.
     * 
     * @return an ArrayList<Task> of the information stores in the file. If the file doesn't exist, it returns
     * an empty ArrayList<Task>.
     */
    public ArrayList<Task> readFromFile() {
        ArrayList<Task> list = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(this.path);
            for (String line : lines) {
                list.add(createTask(line));
            }
        } catch (IOException e) {
            System.out.println("Error when reading file!");
        }
        return list;
    }


    /**
     * A method that assist the readFromFile method to create tasks based on the data stored on the file.
     * 
     * @param line the lines found in the file.
     * @return A task based on the information on the given line.
     */
    private Task createTask(String line) throws IOException {
        char taskType = line.charAt(0);
        boolean isCompleted;
        String[] lineData = line.split(",");
        isCompleted = lineData[1].equals("1");
        if (taskType == 'T') {
            return new ToDoTask(lineData[2], isCompleted);
        } else if (taskType == 'E') {
            return new Event(lineData[2], lineData[3], isCompleted);
        } else if (taskType == 'D') {
            return new DeadLine(lineData[2], lineData[3], isCompleted);
        } else {
            throw new IOException("There is an error in the saved file");
        }
    }

    /**
     * A method to saveToFile the data on the TaskList to the given file.
     * 
     * @param tList The TaskList to be saved.
     * @return A boolean on whether save was successful
     */
    public boolean saveToFile(TaskList tList) {
        try {
            Files.deleteIfExists(this.path);
            PrintWriter out = new PrintWriter("data/winston.txt");
            String dataToSave = tList.listSaveDataFormatter();
            if (dataToSave != "") {
                out.println(dataToSave);
            }
            out.close();
            return true;
        } catch (IOException e) {
            System.out.println("Error overwriting file");
            return false;
        }
    }


}
