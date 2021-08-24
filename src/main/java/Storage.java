import java.io.*;
import java.util.Scanner;

public class Storage {
    private static final String OUTER_DIR = "data";
    private static final String FILE = "taskList.txt";
    private static final String[] FILE_PATH_ARR =  {".", OUTER_DIR , FILE};
    private static final String CREATE_FILE_ERROR = "An error occurred. Unable to create taskList file.";
    private static final String CLOSE_WRITER_ERROR = "An error occurred. Unable to close writer to taskList.";
    private static final String FILE_NOT_FOUND_MESSAGE = "An error occurred. Unable to find file.";
    private static final String INVALID_TASKLIST_MESSAGE = "Error reading taskLst. TaskList is probably invalid.";
    private final File taskFile;
    private boolean didTaskFileExist = false;
    private BufferedWriter bufferedWriter;

    public Storage() {
        taskFile = new File(getPath());
        this.createOuterDirectory();
        try {
            if (!taskFile.createNewFile()) {
                this.didTaskFileExist = true;
            }
            FileWriter fileWriter = new FileWriter(taskFile, true);
            this.bufferedWriter = new BufferedWriter(fileWriter);
        } catch (IOException err) {
            System.out.println(CREATE_FILE_ERROR);
        }
    }

    public void closeWriter() {
        try {
            this.bufferedWriter.close();
        } catch (IOException err) {
            System.out.println(CLOSE_WRITER_ERROR);
        }
    }

    public boolean didTaskFileExist() {
        return this.didTaskFileExist;
    }

    public void writeTaskToFile(Task task) {
        try {
            bufferedWriter.append(task.getSaveFormat());
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException err) {
            System.out.println("Error writing task to file.");
        }
    }

    public void readTaskFile(TaskList taskList) {
        try {
            Scanner taskScanner = new Scanner(this.taskFile);
            while (taskScanner.hasNextLine()) {
                String taskLine = taskScanner.nextLine();
                taskList.addSavedTask(taskLine);
            }
            taskScanner.close();
        } catch (FileNotFoundException err) {
            Message.display_message(FILE_NOT_FOUND_MESSAGE);
            System.out.println(err);
        } catch (ArrayIndexOutOfBoundsException err) {
            System.out.println(INVALID_TASKLIST_MESSAGE);
        } catch (DukeException err) {
            Message.display_message(err.getMessage());
        }
}

    private String getPath() {
        return String.join(File.separator, FILE_PATH_ARR);
    }

    private void createOuterDirectory() {
        File directory = new File(OUTER_DIR);
        directory.mkdirs();
    }
}
