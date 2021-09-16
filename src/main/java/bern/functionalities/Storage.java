package bern.functionalities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import bern.Command;
import bern.exception.BernException;
import bern.model.Task;

/**
 * A class to encapsulates all methods storage/file related.
 */
public class Storage {
    /**
     * A method to write into file from an ArrayList of Tasks.
     *
     * @param arListTask An ArrayList of Tasks to be written into a file.
     */
    public void writeIntoFile(ArrayList<Task> arListTask) {
        try {
            new Storage().reinitialiseFile();
            FileWriter fw = new FileWriter("savedList.txt");
            if (arListTask.isEmpty()) {
                fw.close();
            }
            fw.write(new Ui().getReply(Command.LIST, "", arListTask));
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (BernException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * A method to initialise the ArrayList of Tasks.
     *
     * @param arListTask An ArrayList of Tasks to be initialised.
     */
    public void initialiseArListTask(ArrayList<Task> arListTask) {
        File file = new File("savedList.txt");
        if (!file.exists()) {
            return;
        } else {
            try {
                Scanner s = new Scanner(file);
                while (s.hasNext()) {
                    String taskStr = s.nextLine();
                    assert taskStr.length() > 0 : "task is empty";
                    taskStr = taskStr.substring(taskStr.indexOf("["));
                    Task temp = new Parser().taskFromString(taskStr);
                    new TaskList().addTask(temp, arListTask);
                }
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * A method to reinitialise the file (delete and make a new empty file).
     */
    public void reinitialiseFile() {
        try {
            File file = new File("savedList.txt");
            file.delete();
            file.createNewFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
