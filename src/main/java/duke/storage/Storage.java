package duke.storage;

import duke.task.TaskList;
import duke.ui.UserInterface;
import duke.parser.Parser;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class represents the storage of the task list and handles all the
 * interaction between the user and the file contents.
 */
public class Storage {

    private final File file;
    private final String filePath;
    private final static UserInterface USER_INTERFACE = new UserInterface();

    /**
     * Creates an instance of the Storage class.
     *
     * @param path the input path where the file is to be found / created
     */
    public Storage(String path) {
        filePath = path;
        file = new File(filePath);
    }

    /**
     * Prints the content of the task file. Prints a notification to alert the user if the
     * file is empty or a warning if the file is not found.
     */
    public void printTaskFile() {
        try {
            Scanner sc = new Scanner(file);
            boolean isEmpty = !sc.hasNext();
            int index = 1;

            while (sc.hasNext()) {
                System.out.println(index + ". " + sc.nextLine());
                index++;
            }

            if (isEmpty) {
                USER_INTERFACE.emptyListWarning();
            }

        } catch (FileNotFoundException e) {
            USER_INTERFACE.fileNotFoundWarning();
        }
    }

    /**
     * Saves the input task into the txt file that is found in the path specified.
     *
     * @param task the task to be saved into the txt file
     * @throws IOException if file is not found in the path specified
     */
    public void saveTask(TaskList task) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath, true);
        fileWriter.write(task.toString() + "\n");
        fileWriter.close();
    }

    /**
     * Overwrites the entire file with all the tasks in the given array list.
     *
     * @param taskArrayList the input array list containing all the tasks to be written
     *                      into the file
     */
    public void overwriteList(ArrayList<TaskList> taskArrayList) {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            for (TaskList t : taskArrayList) {
                fileWriter.write(t.toString() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            USER_INTERFACE.generalErrorWarning(e.getMessage());
        }
    }

    /**
     * Loads the contents of the file into the local array list. Prints
     * a warning if the file is not found in the specified path.
     */
    public void loadFile() {
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String line = sc.nextLine();
                Parser.parseFromFile(line);
            }
        } catch (FileNotFoundException e) {
            USER_INTERFACE.fileNotFoundWarning();
        }
    }
}
