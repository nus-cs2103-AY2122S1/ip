import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private final java.nio.file.Path path;
    
    public Storage() {
        String pathToThisFile = System.getProperty("user.dir");
        Path dataDir = Paths.get(pathToThisFile, "data");
        this.path = java.nio.file.Paths.get(pathToThisFile, "data", "winston.txt");
        try {
            if (Files.exists(dataDir)) {
                if (Files.exists(this.path)) {
                    read();
                } else {
                    Files.createFile(this.path);
                }
            } else {
                Files.createDirectory(java.nio.file.Paths.get(pathToThisFile, "data"));
                Files.createFile(this.path);
            }
        } catch (FileAlreadyExistsException e) {
            System.out.println("Something went wrong!");
        } catch (IOException e) {
            System.out.println("Something unexpected happened during the creation of files!");
        }
    }

    public ArrayList<Task> read() {
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



    private Task createTask(String line) throws IOException {
        char taskType = line.charAt(0);
        String[] lineData = line.split(",");
        boolean isCompleted;
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
    
    public void save(TaskList tList) {
        try {
            Files.deleteIfExists(this.path);
            PrintWriter out = new PrintWriter("data/winston.txt");
            out.println(tList.getListData());
            out.close();
        } catch (IOException e) {
            System.out.println("Error overwriting file");
        }
    }


}
