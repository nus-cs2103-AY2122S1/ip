import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private static final String taskFilePath = "src/data.txt";

    private static void createTaskFile() {
        try {
            File taskFile = new File(taskFilePath);
            taskFile.createNewFile();
        } catch (IOException exception) {
            // TODO: handle IOException?
        }
    }

    public static void readFromFile() {
        File taskFile = new File(taskFilePath);
        try {
            Scanner scanner = new Scanner(taskFile);
            while (scanner.hasNextLine()) {
                Parser.handleCommand(scanner.nextLine(), true);
            }
        } catch (FileNotFoundException exception) {
            createTaskFile();
        } catch (IrisException exception) {
            Ui.say("data.txt has been corrupted");
        }
    }

    public static void appendToFile(String command) {
        try {
            FileWriter fw = new FileWriter(taskFilePath, true);
            fw.write(command);
            fw.close();
        } catch (IOException exception) {
            // TODO: handle IOException?
        }
    }
}
