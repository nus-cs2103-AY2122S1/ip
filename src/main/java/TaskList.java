import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TaskList {
    private final static String FILE_PATH = "duke.txt";
    private List<Task> tasks;

    private TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns a TaskList initialized from local file (if any).
     * If local file not provided, this function creates one.
     *
     * @return new list with tasks obtained from local file (if any).
     */
    public static TaskList init() throws FileNotFoundException, IOException {
        File file = new File(FILE_PATH);
        file.createNewFile();
        FileInputStream fstream = new FileInputStream(file);
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
        List<Task> tasks = new ArrayList<>();
        String line;
        while ((line = br.readLine()) != null) {
            try {
                tasks.add(Task.strToObj(line));
            } catch (IllegalArgumentException e) {
                continue;
            }
        }
        br.close();
        return new TaskList(tasks);
    }

    /**
     * Returns the size of the list.
     *
     * @return the size of the list
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Inserts the specified task at the end of the list.
     *
     * @param task task to be inserted
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Returns the task at the specified position in this list.
     *
     * @param idx index of the task to return
     * @return the task at the specified position in this list
     */
    public Task get(int idx) {
        return tasks.get(idx);
    }

    /**
     * Removes the task at the specified position in this list.
     *
     * @param idx index of the task to remove
     */
    public void remove(int idx) {
        tasks.remove(idx);
    }
}
