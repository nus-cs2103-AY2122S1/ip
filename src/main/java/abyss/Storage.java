package abyss;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import abyss.exception.LoadTaskException;
import abyss.task.Task;
import abyss.task.TaskManager;

/**
 * Represents a storage file which stores user tasks.
 */
public class Storage {
    private String filePath;
    private File file;

    /**
     * Constructs a Storage.
     *
     * @param filePath File path of the storage file.
     */
    public Storage(String filePath) throws IOException {
        this.filePath = filePath;
        this.file = new File(filePath);
        if (!file.isFile()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
    }

    /**
     * Loads tasks from storage file into a task list.
     *
     * @return Loaded task list.
     * @throws IOException If there is error reading from file.
     * @throws LoadTaskException If there is invalid task in the file.
     */
    public TaskManager loadTasks() throws IOException, LoadTaskException {
        TaskManager tasks = new TaskManager();
        FileReader fileReader = new FileReader(filePath);
        BufferedReader reader = new BufferedReader(fileReader);
        String line;
        while ((line = reader.readLine()) != null) {
            String[] entry = line.split(" \\| ", 4);
            String taskType = entry[0];
            String isDone = entry[1];
            Task task;
            switch (taskType) {
            case "T":
                task = tasks.addToDo(entry[2]);
                if (isDone.equals("1")) {
                    task.markAsDone();
                }
                break;
            case "D":
                task = tasks.addDeadline(entry[2], entry[3]);
                if (isDone.equals("1")) {
                    task.markAsDone();
                }
                break;
            case "E":
                task = tasks.addEvent(entry[2], entry[3]);
                if (isDone.equals("1")) {
                    task.markAsDone();
                }
                break;
            default:
                throw new LoadTaskException("Invalid task in file.");
            }
        }
        reader.close();
        return tasks;
    }

    /**
     * Saves tasks from given task list into the storage file.
     *
     * @param taskManager List of tasks to be stored.
     * @throws IOException If there is error writing to file.
     */
    public void saveTasks(TaskManager taskManager) throws IOException {
        StringBuffer input = new StringBuffer();
        for (int i = 0; i < taskManager.getNumberOfTasks(); i++) {
            Task task = taskManager.get(i);
            input.append(task.toFileEntry());
            input.append("\n");
        }
        FileWriter writer = new FileWriter(filePath);
        writer.write(input.toString());
        writer.close();
    }
}
