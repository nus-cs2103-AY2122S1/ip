package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *  Class to deal with loading tasks from the file and saving tasks in the file.
 */
public class Storage {

    private String filePath;

    /**
     * Constructor.
     *
     * @param filePath the path of the save file
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads a file based on the path given when calling the constructor.
     *
     * @return a list of tasks that are in the save file, or an empty list if
     *         the file cannot be found for whatever reason
     */
    public TaskList load() {
        TaskList myTasks = new TaskList();
        Parser myParser = new Parser();

        // Tries to read from a save file to initialize a saved taskList
        try {
            // Ensure directory and file are both there
            File myFile = new File("data");
            myFile.mkdirs();
            myFile = new File(filePath);
            myFile.createNewFile();

            assert myFile.exists(); // Make sure that the file exists

            // Reads from file is there is anything in it
            Scanner fileReader = new Scanner(myFile);
            while (fileReader.hasNext()) {
                myParser.scanInputs(fileReader, myTasks);
            }
            fileReader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return myTasks;
    }

    /**
     * Saves a list of tasks to a file for future reading.
     *
     * @param taskList the list of tasks
     */
    public void saveToFile(ArrayList<Task> taskList) {
        try {
            FileWriter myWriter = new FileWriter(filePath);
            for (int i = 0; i < taskList.size(); i++) {
                taskList.get(i).writeToFile(myWriter);
                myWriter.write("\n");

                // Write a done command if task is done
                if (taskList.get(i).isDone) {
                    myWriter.write("done " + (i + 1));
                    myWriter.write("\n");
                }

                // Write all tags
                for (int j = 0; j < taskList.get(i).tags.size(); j++) {
                    myWriter.write("tag " + (i + 1) + " " + taskList.get(i).tags.get(j));
                    myWriter.write("\n");
                }
            }
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
