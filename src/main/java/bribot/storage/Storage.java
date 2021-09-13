package bribot.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import bribot.task.Task;

/**
 * Represents the storage that interacts with the text file to retrieve and save tasks upon starting
 * and exiting the program.
 */

public class Storage {

    private File savedTasksFile;

    /**
     * Creates a new Storage object that has its file path set to that of the file used
     * to save the tasks.
     * @param filePath Path of the text file used to store the saved tasks.
     */
    public Storage(String filePath) {
        this.savedTasksFile = new File(filePath);
        File dir = new File(savedTasksFile.getParent());
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    /**
     * Returns an ArrayList of Strings where each element is a line from the text file.
     * @return ArrayList of Strings
     */
    public ArrayList<String> load() {
        try {
            if (savedTasksFile.createNewFile()) {
                return new ArrayList<>();
            } else {
                Scanner reader = new Scanner(savedTasksFile);
                ArrayList<String> loadingStrings = new ArrayList<>();
                while (reader.hasNextLine()) {
                    String next = reader.nextLine();
                    loadingStrings.add(next);
                }
                return loadingStrings;
            }
        } catch (IOException e) {
            System.out.println("AN ERROR HAS OCCURRED WHILE LOADING");
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    /**
     * Formats and writes each task in the ArrayList of tasks into the task.txt file.
     * @param tasks The ArrayList of tasks to be saved into task.txt.
     */
    public void save(ArrayList<Task> tasks) {
        try {
            savedTasksFile.createNewFile();
            FileWriter writer = new FileWriter(savedTasksFile, false);
            for (int i = 0; i < tasks.size(); i++) {
                StringBuilder builder = new StringBuilder();
                Task task = tasks.get(i);
                builder.append(task.getType());
                builder.append("|");
                if (task.getStatusIcon() == "X") {
                    builder.append("1|");
                } else {
                    builder.append("0|");
                }
                builder.append(task.getDescription());
                builder.append("|");
                builder.append(task.getDateTimeString());
                builder.append("\n");
                writer.write(builder.toString());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("AN ERROR HAS OCCURRED WHILE SAVING");
            e.printStackTrace();
        }
    }
}
