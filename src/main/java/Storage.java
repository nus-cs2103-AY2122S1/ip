import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The Storage class deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    private String dirPath;
    private String fileName;
    private File file;

    // tasklist addtask/deletetask/markdone -> make changes to storage

    public Storage(String dirPath, String fileName) {
        // suppose we init Storage on first setup of Aisu and ask for the filepath to save the list in. and ask for the filename to save it as.
        // but we will hardcode the file name and location first. eg. "data/tasklist.txt"
        this.dirPath = dirPath;
        this.fileName = fileName;
        this.createFile();
    }

    private boolean createFile() {
        System.out.println("Checking for old directories...");
        System.out.println(new File(this.dirPath).mkdirs() ? "New directory created." : "Directory already exists.");
        // if directory does not exist, create it.
        // using .exists() to check is not a good solution! just use .mkdirs() directly,
        // it will not throw an error if it does exist and will not overwrite an existing directory.

        try {
            this.file = new File(this.dirPath + "\\" + this.fileName);
            System.out.println("Checking for existing file: " + this.fileName + "...");
            System.out.println(this.file.createNewFile() ? "New " + this.fileName + " created." : this.fileName + " already exists.");
            return true;
        } catch (IOException e) {
            System.out.println("Error: File cannot be created.");
            return false;
        }
    }

    public List<Task> load() throws FileNotFoundException, AisuException { // need to fix exceptions later
        // loads list from file in format
        List<Task> result = new ArrayList<>();
        Scanner s = new Scanner(this.file);
        while (s.hasNext()) {
            String[] temp = s.nextLine().split(";;");
            //depending on T D or E, create a task and add to the list.
            Task tempTask;
            switch(temp[0]) {
                case "T":
                    tempTask = new Todo(temp[2]);
                    break;
                case "D":
                    tempTask = new Deadline(temp[2], temp[3]);
                    break;
                case "E":
                    tempTask = new Event(temp[2], temp[3]);
                    break;
                default:
                    throw new AisuException("No such type of task! >:( unchecked exception error!");
            }
            result.add(tempTask);
        }
        return result;
    }

    public void save(List<Task> currList) {
        // save list to file in format with ';;' as dividers and on a new line for each entry. Rewrites the entire file.
        try {
            FileWriter fw = new FileWriter(this.dirPath + "\\" + this.fileName);
            for (Task t : currList) {
                fw.write(t.ParseData() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error saving file. Attempting to create the file...");
            // if file/folder doesn't exist, create a new one and call save again.
            if (this.createFile()) {
                this.save(currList);
            } else {
                System.out.println("There is something wrong with the system.");
            }
        }
    }

    public void changeFilePath(String newDirPath, String newFileName) throws IOException { // have the Ui handle this
        // allow users to change where they want to store the list.
        Files.copy(Paths.get(this.dirPath + "\\" + this.fileName), Paths.get(newDirPath + "\\" + newFileName));
        Files.delete(Paths.get(this.dirPath + "\\" + this.fileName));
    }

}
