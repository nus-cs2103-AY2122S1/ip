import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private final String taskFilePath;

    public Storage(String taskFilePath) {
        this.taskFilePath = taskFilePath;
    }

    private void createTaskFile() {
        try {
            File taskFile = new File(taskFilePath);
            taskFile.createNewFile();
        } catch (IOException exception) {
            // TODO: handle IOException?
        }
    }

    public void readTasks(TaskList taskList) {
        File taskFile = new File(taskFilePath);
        try {
            Scanner scanner = new Scanner(taskFile);
            while (scanner.hasNextLine()) {
                Parser.handleCommand(scanner.nextLine(), taskList, true);
            }
        } catch (FileNotFoundException exception) {
            createTaskFile();
        } catch (IrisException exception) {
            Ui.say("data.txt has been corrupted");
        }
    }

    public void writeTasks(TaskList taskList) {
        try {
            FileWriter fw = new FileWriter(taskFilePath);
            String[] commands = taskList.toCommands();
            for (String command : commands) {
                fw.write(command);
            }
            fw.close();
        } catch (IOException exception) {
            // TODO: handle IOException?
        }
    }

    public void appendToFile(String command) {
        try {
            FileWriter fw = new FileWriter(taskFilePath, true);
            fw.write(command);
            fw.close();
        } catch (IOException exception) {
            // TODO: handle IOException?
        }
    }
}
