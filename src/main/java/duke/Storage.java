package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    private String filePath;
    private File file;

    public Storage(String filePath) {
        assert filePath.length() != 0 : "Path of file cannot be empty";
        this.filePath = filePath;
        File file = new File(filePath);
        assert file.exists();
        this.file = file;
    }



    /**
     * Method that adds the Tasklist arraylist into the text file
     *
     * @param listOfTasks The TaskList that contains all the tasks added
     *
     * @return void
     */
    public static void appendListToFile(TaskList listOfTasks) {
        try {
            File fileStorage = new File("data/duke.txt");
            assert fileStorage.exists(): "File to write to must exist";
            FileWriter w = new FileWriter(fileStorage);
            String stringOfContents = "";
            for (Task t : listOfTasks.getTaskList()) {
                stringOfContents += t.toString() + "\n";
            }
            w.write(stringOfContents);
            w.close();
        } catch (IOException e) {
            System.out.println("File does not exist");
        }
    }
}


