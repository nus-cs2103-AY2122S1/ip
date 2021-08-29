package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Task;

/**
 * Storage is the class that handles reading and writing to the save file.
 *
 * @author Loh Wen Hao Aaron
 *
 */
public class Storage {
    private Scanner s;
    private final String filePath;
    private File savefile;

    /**
     * Storage constructor that takes in the file path for the save file.
     *
     * @param filepath the file path of the save file.
     */
    public Storage(String filepath) {
        this.filePath = filepath;
        this.initialiseSaveFile();
    }

    /**
     * Checks if save file exist, and if it does not, creates it.
     *
     */
    public void initialiseSaveFile() {
        this.savefile = new File(this.filePath);

        try {
            if (savefile.getParentFile().mkdir()) {
                System.out.println("File path created.");
                if (savefile.createNewFile()) {
                    System.out.println("File created.");
                }
            }
        } catch (IOException e) {
            throw new DukeException("File could not be found!");
        }
    }

    /**
     * Reads the save file and stores each word in an ArrayList.
     *
     * @return  An ArrayList of words in the save file.
     */
    public ArrayList<String> produceReadableString() {
        try {
            s = new Scanner(savefile);
            ArrayList<String> output = new ArrayList<>();

            while (s.hasNextLine()) {
                String thisLine = s.nextLine();
                String[] segments = thisLine.split(" \\| | ");
                for (int i = 0; i < segments.length; i++) {
                    output.add(segments[i]);
                }
            }
            s.close();

            return output;
        } catch (IOException e) {
            throw new DukeException("File could not be found!");
        }

    }

    /**
     * Updates the save file's list of tasks.
     *
     */
    public void updateSavefile(TaskList tl) {
        try {
            FileWriter myWriter = new FileWriter("saves/saves.txt");
            System.out.println("Updating Save File");

            for (int i = 0; i < tl.numberOfTasks(); i++) {
                Task thisTask = tl.getTask(i);
                myWriter.write(thisTask.getReadableString());
            }

            myWriter.close();

        } catch (Exception e) {
            throw new DukeException("File could not be found!");
        }
    }
}
