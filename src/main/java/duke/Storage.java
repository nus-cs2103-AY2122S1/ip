package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    private String filePath;
    private File file;

    public Storage(String filePath) {
        this.filePath = filePath;
        File file = new File(filePath);
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


