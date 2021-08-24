import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;


public class Storage {

    protected String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public ArrayList<Task> load(File f) throws FileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner sc = new Scanner(f);
        while (sc.hasNext()) {
            String line = sc.nextLine();
            String[] taskInfo = line.split(" ~ ");
            Task newTask = new Task("");
            if (taskInfo[0].equals("T")) {
                newTask = new ToDo(taskInfo[2]);
            } else if (taskInfo[0].equals("D")) {
                newTask = new Deadline(taskInfo[2], taskInfo[3]);
            } else if (taskInfo[0].equals("E")) {
                newTask = new Event(taskInfo[2], taskInfo[3]);
            } else {
                newTask.newTask = taskInfo[1];
            }
            if (line.contains("Yes")) {
                newTask.setIsDone();
            }
            tasks.add(newTask);
        }
        return tasks;
    }
}
