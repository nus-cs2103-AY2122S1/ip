package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/** Class that handles operations to and from the text file */
public class Storage {
    private String filePath;
    private File file;

    /**
     * Constructor for the Storage class
     *
     * @param filePath The path of the file that holds the contents of the list
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        File file = new File(filePath);
        this.file = file;
    }

    /**
     * Adds the Tasklist arraylist into the text file
     *
     * @param listOfTasks The TaskList that contains all the tasks added
     */
    public static void appendListToFile(TaskList listOfTasks) {
        try {
            File fileStorage = new File("data/duke.txt");
            assert fileStorage.exists(): "File to write to must exist";
            FileWriter w = new FileWriter(fileStorage);
            String str = "";
            for (Task t : listOfTasks.getTaskList()) {
                str += t.toString() + "\n";
            }
            w.write(str);
            w.close();
        } catch (IOException e) {
            System.out.println("File does not exist");
        }
    }
}


