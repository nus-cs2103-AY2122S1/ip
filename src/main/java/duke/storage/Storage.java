package duke.storage;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Contains methods which saves/deletes the list to a file.
 */
public class Storage {
    private static final DateTimeFormatter FORMAT_TIME_FILE = DateTimeFormatter.ofPattern("MMM d yyyy hh:m a");
    private static final DateTimeFormatter FORMAT_NO_TIME_FILE = DateTimeFormatter.ofPattern("MMM d yyyy");
    File dir;
    File txtFile;
    String filePath;

    /**
     * Constructor for the Storage class.
     *
     * @param dirPath  Path of the directory the file is in.
     * @param fileName Name of the file.
     */
    public Storage(String dirPath, String fileName) {
        this.dir = new File(dirPath);
        dir.mkdir();
        this.filePath = dirPath + fileName;
        this.txtFile = new File(this.filePath);
    }

    /**
     * Loads the file containing a list into the program.
     *
     * @return An ArrayList with the tasks loaded.
     * @throws IOException If file is not found.
     */
    public ArrayList<Task> loadFile() throws IOException {
        ArrayList<Task> arr = new ArrayList<>();
        if (!this.txtFile.createNewFile()) {
            Scanner s = new Scanner(this.txtFile);
            while (s.hasNext()) {
                String line = s.nextLine();
                boolean isDone = line.charAt(7) == 'X';
                switch (line.charAt(4)) {
                case 'T':
                    arr.add(new Todo(line.substring(10), isDone));
                    break;
                case 'D': {
                    int index = line.indexOf(" (by: ");
                    String description = line.substring(10, index);
                    String dateTime = line.substring(index + 6, line.length() - 1);
                    String[] dateTimeArray = dateTime.split(" ");
                    if (dateTimeArray.length > 3) {
                        arr.add(new Deadline(description, dateTime, FORMAT_TIME_FILE, true, isDone));
                    } else {
                        arr.add(new Deadline(description, dateTime, FORMAT_NO_TIME_FILE, false, isDone));
                    }
                    break;
                }
                case 'E':
                    int index = line.indexOf(" (at: ");
                    String description = line.substring(10, index);
                    String dateTime = line.substring(index + 6, line.length() - 1);
                    String[] dateTimeArray = dateTime.split(" ");
                    if (dateTimeArray.length > 3) {
                        arr.add(new Event(description, dateTime, FORMAT_TIME_FILE, true, isDone));
                    } else {
                        arr.add(new Event(description, dateTime, FORMAT_NO_TIME_FILE, false, isDone));
                    }
                    break;
                }
            }
        }
        return arr;
    }

    /**
     * Saves a list to the file.
     *
     * @param textToSave Text representing the list.
     * @throws IOException If file is not found.
     */
    public void saveFile(String textToSave) throws IOException {
        FileWriter fileWriter = new FileWriter(this.filePath);
        fileWriter.write(textToSave);
        fileWriter.close();
    }
}
