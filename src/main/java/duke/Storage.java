package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class deals with loading tasks from the file
 * and saving tasks in the file for Duke to process and use later.
 */
public class Storage {

    /**
     * The filepath used to track where the data is being saved to.
     **/
    private static final String FILE_LOCATION = "data\\duke.txt";

    /**
     * Constructor for Storage object.
     */
    public Storage() {
    }

    /**
     * Writes content to the given filepath with given content.
     */
    public static String writeFile() {
        ArrayList<Task> currList = TaskList.getList();
        for (Task content : currList) {
            try {
                FileWriter fw = new FileWriter(FILE_LOCATION);
                fw.write(content.printTask());
                fw.close();
            } catch (IOException e) {
                return Ui.failToWriteFileMessage(e.getMessage());
            }
        }
        return Ui.successfulWriteFileMessage();
    }

    /**
     * Prints out the file's contents just as Duke begins loading in the file,
     * to allow the user to see what contents are in the file that was previously saved.
     */
    public static String printStartingFileContents() {
        File testFile = new File(FILE_LOCATION);
        Scanner s;
        try {
            s = new Scanner(testFile);
            return Ui.displayTasksOnLoad(s);
        } catch (FileNotFoundException e) {
            return Ui.emptyListMessage();
        }
    }

    /**
     * Loads in the file from the given filename and retrieves the data from the file,
     * processing it and copying the data into Duke and the TaskList for usage and
     * processing.
     *
     * @return ArrayList to be stored in TaskList.
     */
    public TaskList load() {
        ArrayList<Task> newList = new ArrayList<>();
        try {
            File newFile = new File(FILE_LOCATION);
            Scanner readFile = new Scanner(newFile);
            while (readFile.hasNext()) {
                // read file line by line and decipher into arraylist
                String nextLine = readFile.nextLine();
                char typeOfTask = nextLine.charAt(1);
                char taskStatus = nextLine.charAt(4);
                String description = nextLine.substring(6);
                Task temp;

                boolean isDeadLine = typeOfTask == 'D';
                boolean isEvent = typeOfTask == 'E';
                boolean isTaskDone = taskStatus == 'X';

                if (isDeadLine) {
                    temp = new Deadline(description);
                } else if (isEvent) {
                    temp = new Event(description);
                } else {
                    temp = new Task(description);
                }

                assert temp != null : "Temp will be a Deadline, Event or Task";

                if (isTaskDone) {
                    temp.markSaved();
                }
                newList.add(temp);
            }
            return new TaskList(newList);
        } catch (FileNotFoundException e) {
            // Creating new parent directory if does not exist
            File dukeFile = new File(FILE_LOCATION);
            File parentDir = dukeFile.getParentFile();
            if (!parentDir.exists()) {
                parentDir.mkdirs();
            }
            System.out.println("Failed to create storage file: " + e.getMessage());
            return new TaskList(new ArrayList<>());
        }
    }
}
