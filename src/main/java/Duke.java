import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Represents the Duke program. Manages tasks based on commands received.
 */
public class Duke {
    /** Storage that can write to or retrieve data from a file on hard disk */
    private Storage storage;
    /** List of tasks added */
    private TaskList taskList;
    /** UI of the program */
    private Ui ui;

    /**
     * Constructor of the class 'Duke'.
     *
     * @param filePath Path of the file to retrieve data.
     */
    public Duke(Path filePath) {
        this.taskList = new TaskList();
        this.storage = new Storage(filePath, this.taskList);
    }

    public void addToList(Task task) {
        this.taskList.addTask(task);
        this.storage.addToFile(task);
    }

    public void removeFromList(Task task) {
        this.taskList.removeFromList(task);
        this.storage.removeFromFile(this.taskList.indexOf(task));
    }

    public int getNumOfTasks() {
        return this.taskList.getNumOfTasks();
    }

    public ArrayList<Task> getTasks() {
        return this.taskList.getTasks();
    }

    public Task getTaskAt(int index) {
        return this.taskList.get(index);
    }

    public void rewriteFile() {
        this.storage.rewriteFile();
    }

    /**
     * Runs the duke program.
     */
    public void run() {
        this.ui = new Ui(this);
        boolean isRunning = true;
        while (isRunning) {
            isRunning = this.ui.getCommandOutput();
        }
    }

    /**
     * Runs the Duke program, prints out messages based on commands received.
     *
     * @param args The command line parameters.
     */
    public static void main(String[] args) {
        String filePath = System.getProperty("user.dir");
        new Duke(Paths.get(filePath, "data", "duke.txt")).run();
    }
}
