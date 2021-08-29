import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class deals with loading tasks from the file
 * and saving tasks in the file.
 */
public class Storage {
    private final String filePath;

    public Storage (String filePath) {
        this.filePath = filePath;
    }

    /**
     * Writes content to a file with given content.
     */
    public void writeFile() {
        ArrayList<Task> currList = TaskList.getList();
        for (Task content : currList) {
            try {
                FileWriter fw = new FileWriter(this.filePath);
                fw.write(content.printTask());
                fw.close();
            } catch (IOException e) {
                Ui.failedWriteFile(e.getMessage());
            }
        }
    }

    /**
     *
     */
    public void printStartingFileContents() {
        File testFile = new File(this.filePath);
        Scanner s;
        try {
            s = new Scanner(testFile);
            Ui.printTasksOnLoad(s);
        } catch (FileNotFoundException e) {
            Ui.firstTimeMessage();
        }
    }

    public ArrayList<Task> load() {
        ArrayList<Task> newList = new ArrayList<>();
        File newFile = new File(this.filePath);
        try {
            Scanner readFile = new Scanner(newFile);
            while (readFile.hasNext()) {
                // read file line by line and decipher into arraylist
                String nextLine = readFile.nextLine();
                newList.add(new Task(nextLine));
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return newList;
    }
}
