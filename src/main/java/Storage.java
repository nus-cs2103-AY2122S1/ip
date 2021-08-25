import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Storage {
    private String taskListFileName;
    private File taskListFile;

    public Storage(String taskListFileName) {
        this.taskListFileName = taskListFileName;
        try {
            Files.createDirectories(Paths.get(this.taskListFileName).getParent().getFileName());
        } catch (IOException e) {
            System.out.println("cannot create directory");
        }
        this.taskListFile = new File(this.taskListFileName);
    }

    public TaskList initialise() {
        TaskList taskList = new TaskList();
        try {
            if (!this.taskListFile.createNewFile()) {
                FileInputStream fis = new FileInputStream(this.taskListFileName);
                ObjectInputStream ois = new ObjectInputStream(fis);
                taskList = (TaskList) ois.readObject();
                ois.close();
            } else{
                System.out.println("Looks like it is your first time here");
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
