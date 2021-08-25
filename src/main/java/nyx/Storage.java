package nyx;

import java.io.FileWriter;
import java.io.File;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.ArrayList;

/**
 * Deals with loading tasks from and saving tasks into the hard disk.
 */
public class Storage {
    private final File data;

    /**
     * Constructs a Storage object using the specified folder name and file name.
     * @param folderName Name of the folder where the file is.
     * @param fileName Name of the file to store the tasks.
     */
    public Storage(String folderName, String fileName) {
        this.data = new File(Paths.get(folderName, fileName).toString());
    }

    private Task lineToTask(String line) throws NyxException {
        String[] splitLine = line.split(", ");

        boolean isDone = splitLine[1].equals("1");

        Task task;

        switch(splitLine[0]) {
        case "T":
            task = new ToDo(splitLine[2], isDone);
            break;
        case "D":
            task = new Deadline(splitLine[2], splitLine[3], isDone);
            break;
        case "E":
            task = new Event(splitLine[2], splitLine[3], isDone);
            break;
        default:
            throw new NyxException("Invalid task symbol found. Unable to load data!");
        }

        return task;
    }

    /**
     * Loads tasks from the hard disk and returns them in an ArrayList.
     * @return ArrayList of Task objects.
     * @throws NyxException If the tasks are loaded from the hard disk unsuccessfully.
     */
    public ArrayList<Task> loadData() throws NyxException {
        ArrayList<Task> taskList= new ArrayList<Task>();
        try {
            if (data.exists()) {
                ArrayList<String> lines = new ArrayList<String>();
                Files.lines(data.toPath()).forEach(lines::add);
                for (String line : lines) {
                    taskList.add(lineToTask((line)));
                }
            } else {
                data.getParentFile().mkdirs();
                data.createNewFile();
            }
            return taskList;
        } catch (IOException e) {
            throw new NyxException("Unable to load existing data");
        }
    }

    /**
     * Overwrites the list of tasks in the hard disk with the specified list.
     * @param taskList TaskList object that keeps track of all the tasks.
     * @throws IOException If an error is encountered while overwriting the hard disk.
     */
    public void overwriteData(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(data.getAbsolutePath());
        fw.write(taskList.genSaveFormat());
        fw.close();
    }

    /**
     * Appends the specified task into the hard disk.
     * @param task The task to save.
     * @throws IOException If an error is encountered while appending the task to the hard disk.
     */
    public void addData(Task task) throws IOException {
        FileWriter fw = new FileWriter(data.getAbsolutePath(), true);
        fw.write(task.dataFormat());
        fw.close();
    }
}
