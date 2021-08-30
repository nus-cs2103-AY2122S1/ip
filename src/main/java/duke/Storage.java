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
     * @param filePath the path of the file where
     *                 data of Duke is being stored.
     */
    public Storage (String filePath) {
        Storage.filePath = filePath;
    }

    /**
     * Writes content to the given filepath with given content.
     */
    public static void writeFile() {
        ArrayList<Task> currList = TaskList.getList();
        for (Task content : currList) {
            try {
                FileWriter fw = new FileWriter(filePath);
                fw.write(content.printTask());
                fw.close();
            } catch (IOException e) {
                Ui.failToWriteFileMessage(e.getMessage());
            }
        }
    }

    /**
     * Prints out the file's contents just as Duke begins loading in the file,
     * to allow the user to see what contents are in the file that was previously saved.
     */
    public void printStartingFileContents() {
        File testFile = new File(filePath);
        Scanner s;
        try {
            s = new Scanner(testFile);
            Ui.printTasksOnLoad(s);
        } catch (FileNotFoundException e) {
            Ui.firstTimeMessage();
        }
    }

    /**
     * Loads in the file from the given filename and retrieves the data from the file,
     * processing it and copying the data into Duke and the TaskList for usage and
     * processing.
     *
     * @return an ArrayList to be stored in TaskList.
     */
    public ArrayList<Task> load() {
        ArrayList<Task> newList = new ArrayList<>();
        File newFile = new File(filePath);
        try {
            Scanner readFile = new Scanner(newFile);
            while (readFile.hasNext()) {
                // read file line by line and decipher into arraylist
                String nextLine = readFile.nextLine();
                char typeOfTask = nextLine.charAt(1);
                char isTaskDone = nextLine.charAt(4);
                String description = nextLine.substring(6);
                Task temp = null;
                if (typeOfTask == 'D') {
                    temp = new Deadline(description);
                } else if (typeOfTask == 'E') {
                    temp = new Event(description);
                } else if (typeOfTask == 'T') {
                    temp = new Task(description);
                }
                if (isTaskDone == 'X') {
                    assert temp != null;
                    temp.markSaved();
                }
                newList.add(temp);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return newList;
    }
}
