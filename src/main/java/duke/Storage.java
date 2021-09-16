package duke;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class that encapsulates the file reading/writing for the storage of tasks.
 */
public class Storage {
    private File file;

    /**
     * Class Constructor that takes in the file path of the storage file.
     *
     * @param filePath where the storage file is at.
     */
    public Storage (String filePath) {
        try {
            File temp = new File(filePath);
            temp.getParentFile().mkdir();
            temp.createNewFile();
            this.file = temp;
        } catch (Exception e) {
            System.out.println("Can't create file");
        }
    }

    /**
     * Reads the file of the storage with the help of a stream
     * and returns a ArrayList of Tasks from the storage.
     *
     * @return ArrayList of Task that is stored.
     * @throws IOException When the file can not be accessed.
     */
    public ArrayList<Task> parseFile () throws IOException {
        ArrayList<Task> history = new ArrayList<Task>();
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String currentLine;
        while ((currentLine = bufferedReader.readLine()) != null) {
            Task temp = parseTask(currentLine);
            history.add(temp);
        }
        bufferedReader.close();
        return history;
    }

    /**
     * Parses the line that is stored in the storage file.
     *
     * @param task line in the file that represents a task.
     * @return Task object corresponding to the line in the file.
     */
    public Task parseTask(String task) {
        String[] temp = task.split(" \\| ");
        if (temp.length == 3) {
            Todo toReturn = new Todo(temp[2]);
            if (temp[1].equalsIgnoreCase("1")) {
                toReturn.markAsDone();
            }
            return toReturn;
        } else if (temp[0].equalsIgnoreCase("E")) {
            Event toReturn = new Event(temp[2], temp[3]);
            if (temp[1].equalsIgnoreCase("1")) {
                toReturn.markAsDone();
            }
            return toReturn;
        } else if (temp[0].equalsIgnoreCase("D")) {
            Deadline toReturn = new Deadline(temp[2], temp[3]);
            if (temp[1].equalsIgnoreCase("1")) {
                toReturn.markAsDone();
            }
            return toReturn;
        } else {
            Activity toReturn = new Activity(temp[2], temp[3]);
            if (temp[1].equalsIgnoreCase("1")) {
                toReturn.markAsDone();
            }
            return toReturn;
        }
    }

    /**
     * Clears the file to avoid appending of the same tasks.
     */
    public void fileClear () {
        try {
            FileWriter fileWriter = new FileWriter(file.getAbsoluteFile(), false);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.close();
        } catch (Exception e) {
            System.out.println("Can't clear file.");
        }
    }

    /**
     * Writes a line to file.
     *
     * @param text text to be written.
     */
    public void writeToFile (String text) {
        try {
            FileWriter fileWriter = new FileWriter(file.getAbsoluteFile(), true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(text);
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("Cant write to file");
        }
    }
}
