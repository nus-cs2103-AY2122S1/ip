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
    ArrayList<Task> tasks = new ArrayList<>();
    File dir;
    File txtFile;
    String filePath;

    private enum TaskType {
        Todo, Deadline, Event
    }

    /**
     * Constructs a Storage object.
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
        if (!this.txtFile.createNewFile()) {
            Scanner s = new Scanner(this.txtFile);
            while (s.hasNext()) {
                String line = s.nextLine();
                boolean isDone = line.charAt(7) == 'X';
                assert line.charAt(3) == '[';
                char taskType = line.charAt(4);
                switch (taskType) {
                case 'T':
                    tasks.add(new Todo(line.substring(10), isDone));
                    break;
                case 'D': {
                    int index = line.indexOf(" (by: ");
                    addTaskToTasks(line, isDone, TaskType.Deadline, index);
                    break;
                }
                case 'E':
                    int index = line.indexOf(" (at: ");
                    addTaskToTasks(line, isDone, TaskType.Event, index);
                    break;
                default:
                    break;
                }
            }
        }
        return tasks;
    }

    /**
     * Adds either an Event or Deadline to tasks.
     *
     * @param line Current line in the file.
     * @param isDone Whether the task is marked as done.
     * @param type Type of the task.
     * @param index Index of separator between description and date.
     */
    public void addTaskToTasks(String line, boolean isDone, TaskType type, int index) {
        String description = line.substring(10, index);
        String dateTime = line.substring(index + 6, line.length() - 1);
        String[] dateTimeArray = dateTime.split(" ");
        if (type == TaskType.Event) {
            if (dateTimeArray.length > 3) {
                tasks.add(new Deadline(description, dateTime, FORMAT_TIME_FILE, true, isDone));
            } else {
                tasks.add(new Deadline(description, dateTime, FORMAT_NO_TIME_FILE, false, isDone));
            }
        } else if (type == TaskType.Deadline) {
            if (dateTimeArray.length > 3) {
                tasks.add(new Event(description, dateTime, FORMAT_TIME_FILE, true, isDone));
            } else {
                tasks.add(new Event(description, dateTime, FORMAT_NO_TIME_FILE, false, isDone));
            }
        }
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
