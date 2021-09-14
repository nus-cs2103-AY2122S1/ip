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

    /** The filepath used to track where the data is being saved to. **/
    private static String filePath;

    /**
     * Constructor for Storage object.
     *
     * @param filePath Path of the file where
     *                 data of Duke is being stored.
     */
    public Storage (String filePath) {
        Storage.filePath = filePath;
    }

    /**
     * Writes content to the given filepath with given content.
     */
    public static String writeFile() {
        ArrayList<Task> currList = TaskList.getList();
        for (Task content : currList) {
            try {
                FileWriter fw = new FileWriter(filePath);
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
        File testFile = new File(filePath);
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
    public static ArrayList<Task> load() throws FileNotFoundException {
        ArrayList<Task> newList = new ArrayList<>();
        File newFile = new File(filePath);
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
        return newList;
    }
}
