package duke;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Storage {
    private String taskListFileName;
    private File taskListFile;

    /**
     * Handles saving of taskLists between Duke runtime sessions to text file by serialisation.
     * @param taskListFileName path to text file to store taskList in.
     */
    public Storage(String taskListFileName) {
        this.taskListFileName = taskListFileName;
        try {
            Files.createDirectories(Paths.get(this.taskListFileName).getParent().getFileName());
        } catch (IOException e) {
            System.out.println("cannot create directory");
        }
        this.taskListFile = new File(this.taskListFileName);
    }

    /**
     * Loads taskList from textFile configured in constructor if it exists, else initialises empty taskList.
     * @return stored taskList if present, or empty taskList.
     */
    public TaskList initialise() {
        TaskList taskList = new TaskList();
        try {
            if (!this.taskListFile.createNewFile()) {
                FileInputStream fis = new FileInputStream(this.taskListFileName);
                ObjectInputStream ois = new ObjectInputStream(fis);
                taskList = (TaskList) ois.readObject();
                ois.close();
            }
        } catch (IOException e) {
            System.out.println("IOException");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("classnotfound");
            e.printStackTrace();
        }
        return taskList;
    }

    /**
     * Serialises a taskList into a text file for use in future sessions.
     * @param taskList taskList to be serialised.
     */
    public void store(TaskList taskList) {
        try {
            FileOutputStream fos = new FileOutputStream(this.taskListFileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(taskList);
            oos.close();
        } catch (IOException e) {
            System.out.println("Problem storing");
            e.printStackTrace();
        }
    }
}
