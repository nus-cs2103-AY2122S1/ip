package duke;

import exceptions.DukeInvalidStorageTaskException;
import tasks.Task;
import tasks.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A class that deals with loading and saving tasks files from the Computer's memory.
 */
public class Storage {

    /** Name of the file to save the task history in local storage. The file is saved in
     * the project's root directory.
     * */
    protected static String fileName = "taskList.txt";

    /**
     * Loads previously saved task from memory. If no saved tasks is found, create a new file called
     * taskList.txt to store newly added tasks. The file is stored in the project's root directory.
     *
     * @param taskList The taskList to store previously saved tasks.
     */
    protected static void initialise(TaskList taskList) {
        if (!createFile()) {
            // Found previously saved task
            readTaskFile(new File(fileName), taskList);
        }
    }

    /**
     * Creates a file called taskList.txt in storage. The file is only created if it does not exist in the
     * project root directory.
     *
     * @return True if a file is created. False if the file already exists.
     */
    private static boolean createFile() {
        File file = new File(fileName);
        try {
            return file.createNewFile();
        } catch (IOException e) {
            System.out.println("An error occurred during initialisation.");
            System.out.println(e.getMessage());
            System.exit(1);
            return false;
        }
    }

    /**
     * Saves an arrayList of tasks to local storage.
     *
     * @param taskListArr The arrayList containing the tasks to save.
     * @return True if the save operation is successful. False if it produces an unexpected error.
     */
    public static boolean saveTaskList(ArrayList<? extends Task> taskListArr) {
        FileWriter fileWriter = Storage.createFileWriter(fileName);
        assert fileWriter != null : "Unable to open storage file.";
        for (int i = 0; i < taskListArr.size(); i++) {
            Task task = taskListArr.get(i);
            String saveText = task.taskSaveString();
            try {
                Storage.writeLineToFile(fileWriter, saveText);
            } catch (IOException e) {
                System.out.println("Unable to save task " + i);
            }
        }
        Storage.closeFileWriter(fileWriter);
        return true;
    }

    /**
     * Writes a line to a specified file using the provided fileWriter. A line separator is added after
     * writing a line.
     *
     * @param fileWriter The fileWriter to write to the file.
     * @param lineToAdd The text to add to the file.
     * @throws IOException If an error occurred while trying to write to the file.
     */
    private static void writeLineToFile(FileWriter fileWriter, String lineToAdd) throws IOException {
        try {
            fileWriter.write(lineToAdd + System.lineSeparator());
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    private static void readTaskFile(File file, TaskList taskList) {
        try {
            Scanner scanner = new Scanner(file);
            Storage.readTaskFromScanner(scanner, taskList);
        } catch (FileNotFoundException e) {
            // Should not happen since the file is created if it does not exist before this method is called.
            System.out.println("File not found.");
            System.exit(1);
        }
    }

    private static void readTaskFromScanner(Scanner scanner, TaskList taskList) {
        int count = 0;
        while (scanner.hasNext()) {
            try {
                Task task = Task.storageStringToTask(scanner.nextLine());
                taskList.addSavedTaskFromStorage(task);
                count++;
            } catch (DukeInvalidStorageTaskException e) {
                System.out.println("Unable to read a task from storage.");
            }

        }
        if (count != 0) {
            System.out.println(count + " saved tasks were loaded from memory.\n");
        }
    }

    private static FileWriter createFileWriter(String filePath) {
        try {
            return new FileWriter(filePath);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void closeFileWriter(FileWriter fileWriter) {
        try {
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
