package duke.storage;

import duke.parser.Parser;
import duke.task.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class represents the storage of the task list and handles all the
 * interaction between the user and the file contents.
 *
 * @author yeo-yiheng
 */
public class Storage {

    private final File file;
    private final String filePath;

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
    public String printTaskFile() {
        try {
            Scanner sc = new Scanner(file);
            boolean isEmpty = !sc.hasNext();
            StringBuilder sb = new StringBuilder();
            String listContents = "Oh no! Your list is empty!";
            int index = 1;

            while (sc.hasNext()) {
                sb.append(index).append(". ").append(sc.nextLine()).append("\n");
                index++;
            }

            if (!isEmpty) {
                listContents = sb.toString();
            }

            return listContents;

        } catch (FileNotFoundException e) {
            return e.getMessage();
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
            e.printStackTrace();
        }
    }

    /**
     * Loads the contents of the file into the local array list. Prints
     * a warning if the file is not found in the specified path.
     */
    public void loadFile() {
        if (!(file.exists())) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String line = sc.nextLine();
                Parser.parseFromFile(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
