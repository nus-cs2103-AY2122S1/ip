import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;

public class Storage {
    private BufferedReader reader;
    private BufferedWriter writer;
    private Formatter formatter;
    public static final String DEFAULT_FILE_NAME = "duke.txt";
    public static final String DEFAULT_FILE_DIRECTORY = "data";
    private Path targetDirectory;

    private Storage() {
        try {
            this.targetDirectory =
                    Paths.get(".", DEFAULT_FILE_DIRECTORY, DEFAULT_FILE_NAME).toAbsolutePath().normalize();
            if (!java.nio.file.Files.exists(this.targetDirectory)) {
                Files.createDirectory(Paths.get(".", DEFAULT_FILE_DIRECTORY).toAbsolutePath().normalize());
                Files.createFile(this.targetDirectory);
            }
            this.reader = Files.newBufferedReader(this.targetDirectory, StandardCharsets.UTF_8);
            this.formatter = new Formatter();
        } catch (IOException e) {
            System.out.println("Something happened when initializing the Storage.");
            e.printStackTrace();
        }
    }

    public static Storage createStorage() {
        return new Storage();
    }

    public TaskList load(TaskList taskList) {
        try {
            String line = this.reader.readLine();
            if (line == null || line.isEmpty()) {
                System.out.println("No previous record found.");
                return taskList;
            }
            while (line != null) {
                taskList.addTask(this.formatter.formatStringToTask(line));
                line = this.reader.readLine();
            }
        } catch(IOException e) {
            System.out.println("Unable to load file.");
            e.printStackTrace();
        }
        return taskList;
    }

    public void save(TaskList taskList) {
        try{
            this.writer = Files.newBufferedWriter(this.targetDirectory, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("Exception occurred while initialising writer.");
            e.printStackTrace();
        }
        System.out.println("Saving your list now!");
        for (int i = 1; i <= taskList.size(); i++) {
            try {
                this.writer.write(this.formatter.formatTaskToString(taskList.get(i)));
                this.writer.newLine();
            } catch (IOException e) {
                System.out.println("Exception occurred while writing");
                e.printStackTrace();
            }
        }
        try {
            writer.close();
        } catch (IOException e) {
            System.out.println("Exception occurred while closing writer.");
            e.printStackTrace();
        }
    }

}
