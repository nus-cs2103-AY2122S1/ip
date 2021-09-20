package uhtredragnarson.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import uhtredragnarson.task.Deadline;
import uhtredragnarson.task.Event;
import uhtredragnarson.task.Task;
import uhtredragnarson.task.ToDo;

/**
 * Storage handles the .txt file that stores the user's tasks.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructor for this class.
     *
     * @param filePath The file path to the .txt file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    protected void appendToFile(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAppend + "\n");
        fw.close();
    }

    protected void deleteFromFile(String textToDelete) throws IOException {
        File file = new File(filePath);
        Scanner s = new Scanner(file);
        StringBuilder newFileText = new StringBuilder();
        while (s.hasNext()) {
            String line = s.nextLine();
            if (textToDelete.equals(line)) {
                continue;
            }
            newFileText.append(line).append("\n");
        }
        FileWriter fw = new FileWriter(filePath);
        fw.write(newFileText.toString());
        fw.close();
    }

    /**
     * Reads from the .txt file and returns a list of user tasks.
     *
     * @return List of tasks
     * @throws IOException Throws an exception if the I/O operations failed or are interrupted.
     */
    public List<Task> createTaskList() throws IOException {
        List<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            boolean isDirectoryCreated = file.getParentFile().mkdirs();
            boolean isFileCreated = file.createNewFile();
            assert isDirectoryCreated;
            assert isFileCreated;
        }
        Scanner s = new Scanner(file);
        while (s.hasNext()) {
            String line = s.nextLine();
            char taskType = line.charAt(1);
            boolean isDone = line.charAt(4) == 'X';
            switch (taskType) {
            case 'T':
                ToDo todo = new ToDo(line.substring(7));
                if (isDone) {
                    todo.markAsDone();
                }
                tasks.add(todo);
                break;
            case 'D':
                int byIndex = line.indexOf(" (by: ");
                Deadline deadline = new Deadline(line.substring(7, byIndex),
                        line.substring(byIndex + 6, line.length() - 1));
                if (isDone) {
                    deadline.markAsDone();
                }
                tasks.add(deadline);
                break;
            case 'E':
                int atIndex = line.indexOf(" (at: ");
                Event event = new Event(line.substring(7, atIndex),
                        line.substring(atIndex + 6, line.length() - 1));
                if (isDone) {
                    event.markAsDone();
                }
                tasks.add(event);
                break;
            default:
                break;
            }
        }
        return tasks;
    }
}
