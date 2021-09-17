package banana;

import java.io.FileNotFoundException;
import java.io.File;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Storage class stores the
 * filePath and loads the file info
 * to be assigned to the tasks.
 *
 * @author: Ravi Ananya
 */
public class Storage {

    private String filePath;

    /**
     * Constructor for the Storage class.
     *
     * @param filePath the file path.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Gets the file path.
     *
     * @return the file path.
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * Gets the tasks from the file.
     *
     * @param f the file.
     * @return the loaded list of tasks.
     * @throws FileNotFoundException if the file does not exist.
     */
    public TaskList load(File f) throws FileNotFoundException {
        TaskList tasks = new TaskList(new ArrayList<>());
        Scanner sc = new Scanner(f);
        while (sc.hasNext()) {
            String line = sc.nextLine();
            assert line.contains(" ~ ");
            String[] taskInfo = line.split(" ~ ");
            Task newTask;
            if (taskInfo[0].equals("T")) {
                newTask = new ToDo(taskInfo[2]);
            } else if (taskInfo[0].equals("D")) {
                newTask = new Deadline(taskInfo[2], taskInfo[3]);
            } else if (taskInfo[0].equals("E")) {
                newTask = new Event(taskInfo[2], taskInfo[3]);
            } else {
                newTask = new Task(taskInfo[1]);
            }
            if (line.contains("Yes")) {
                newTask.setIsDone(true);
            }
            tasks.addTask(newTask);
        }
        return tasks;
    }

}
