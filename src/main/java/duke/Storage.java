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
        assert filePath.length() != 0 : "Path of file cannot be empty";
        this.filePath = filePath;
        File file = new File(filePath);
        assert file.exists();
        this.file = file;
    }

    /**
     * Adds the Task list arraylist into the text file
     *
     * @param listOfTasks The TaskList that contains all the tasks added
     */
    public static void appendListToFile(TaskList listOfTasks) {
        //below try-catch block adapted from https://zetcode.com/java/filewriter/
        try {
            File fileStorage = new File("data/duke.txt");
            assert fileStorage.exists(): "File to write to must exist";
            FileWriter fileWriter = new FileWriter(fileStorage);
            String stringOfContents = "";
            for (Task t : listOfTasks.getTaskList()) {
                stringOfContents += t.toString() + "\n";
            }
            fileWriter.write(stringOfContents);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("File does not exist");
        }
    }
}


