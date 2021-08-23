package duke;

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
            taskFile.getParentFile().mkdirs();
            taskFile.createNewFile();
        } catch (IOException exception) {
            // TODO: handle IOException?
        }
    }

    public void readTasks(TaskList tasks, Ui ui) throws IrisException {
        File taskFile = new File(taskFilePath);
        try {
            Scanner scanner = new Scanner(taskFile);
            while (scanner.hasNextLine()) {
                Parser.handleCommand(scanner.nextLine(), tasks, ui, true);
            }
        } catch (FileNotFoundException exception) {
            createTaskFile();
        } catch (IrisException exception) {
            throw new Error("data.txt has been corrupted");
        }
    }

    public void writeTasks(TaskList tasks) {
        try {
            FileWriter fw = new FileWriter(taskFilePath);
            String[] commands = tasks.toCommands();
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
