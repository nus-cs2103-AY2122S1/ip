package duke.main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Task;
import duke.task.TaskList;
import duke.task.TaskTag;

/**
 * Represents a file to store data, operations to write on the file.
 *
 * @author Gordon
 * @version CS2103T, Semester 2
 */
public class Storage {
    private String filePath;
    private String fileName;
    private File file;
    private FileWriter fileWriter;

    /**
     * Class constructor.
     *
     * @param filePath the path from the project directory to the storage file.
     * @param fileName the name of the file.
     */
    public Storage(String filePath, String fileName) {
        this.filePath = filePath;
        this.fileName = fileName;
        assert filePath != null && fileName != null : "filepath and filename cannot be null";
    }

    /**
     * Opens the storage file, if it exists, else creates a new file.
     *
     * @return the storage file.
     * @throws DukeException thrown if there is an exception from creating a new file.
     */
    public File loadStorageFile() throws DukeException {
        try {
            File fileDirectory = new File(filePath);
            if (!fileDirectory.exists()) {
                fileDirectory.mkdir();
            }
            file = new File(filePath + "/" + fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            file = new File(filePath);
            throw new DukeException(DukeException.Exceptions.IOException);
        }
        return file;
    }

    /**
     * Loads all the tasks stored in the storage file into an arraylist.
     *
     * @return an arraylists of tasks stored.
     * @throws DukeException thrown if there is an error creating the new tasks.
     */
    public ArrayList<Task> loadTasks() throws DukeException {
        File storageFile = loadStorageFile();
        ArrayList<Task> tasks = new ArrayList<>();
        Task task;
        try {
            if (storageFile.length() < 1) {
                return tasks;
            }
            Scanner sc = new Scanner(storageFile);
            String storedTask;
            while (sc.hasNext()) {
                storedTask = sc.nextLine();
                task = createNewTask(storedTask);
                tasks.add(task);
            }
            sc.close();
            return tasks;
        } catch (IOException e) {
            throw new DukeException(DukeException.Exceptions.IOException);
        }
    }
    private Task createNewTask(String taskString) throws DukeException {
        Task task;
        if (taskString.contains("D |")) {
            String[] taskDescriptionTagAndDate = getTaskDescriptionTagAndDate(taskString, "| D | ",
                    "by ");
            task = Task.createTask("D", taskDescriptionTagAndDate[0], taskDescriptionTagAndDate[2],
                    taskDescriptionTagAndDate[1]);
        } else if (taskString.contains("E |")) {
            String[] taskDescriptionDateAndTag = getTaskDescriptionTagAndDate(taskString, "| E | ",
                    "at ");
            task = Task.createTask("E", taskDescriptionDateAndTag[0], taskDescriptionDateAndTag[2],
                    taskDescriptionDateAndTag[1]);
        } else {
            // todo tasks have no dates, so only take task description.
            String[] taskDescriptionAndTag = getTaskDescriptionTagAndDate(taskString, "| T | ");
            task = Task.createTask("T", taskDescriptionAndTag[0], taskDescriptionAndTag[1]);
        }
        if (taskString.contains("| 0 |")) {
            task.markAsDone();
        }
        return task;
    }
    @SuppressWarnings("checkstyle:AbbreviationAsWordInName")
    private String[] getTaskDescriptionTagAndDate(String taskString, String taskMarker, String ... timeMarker) {
        String divider = " | ";
        int startOfTaskDescriptionIndex = getStartingIndex(taskString, taskMarker);
        int startOfTaskDateIndex = -1;
        String taskTagSymbol = TaskTag.getStorageTagSymbol();
        if (timeMarker != null) {
            startOfTaskDateIndex = getStartingIndex(taskString, timeMarker[0]);
        }
        String taskDescription = taskString.substring(startOfTaskDescriptionIndex,
                startOfTaskDateIndex);
        String taskDate;
        String taskTag;
        if (!taskDescription.contains(TaskTag.getStorageTagSymbol())) {
            taskDate = taskDescription.substring(startOfTaskDateIndex);
            taskTag = "";
        } else {
            int startOfTagIndex = getStartingIndex(taskString, taskTagSymbol);
            taskDate = taskDescription.substring(startOfTaskDateIndex, startOfTagIndex - taskTagSymbol.length());
            taskTag = taskTagSymbol.substring(startOfTagIndex - divider.length());
        }
        return new String[]{taskDescription, taskTag, taskDate};
    }
    private int getStartingIndex(String string, String stringSlicer) {
        return string.indexOf(stringSlicer) + stringSlicer.length();
    }
    /**
     * Stores all the tasks from the tasks array into the storage file.
     *
     * @param tasks an arraylist of tasks.
     * @throws IOException exception caused in creating new file.
     */
    public void store(TaskList tasks) throws IOException {
        fileWriter = new FileWriter(file, false);
        String data = "";
        for (int i = 0; i < tasks.getNumTasks(); i++) {
            data = data.concat(tasks.getTask(i).formatToStore() + "\n");
        }
        fileWriter.write(data);
        fileWriter.close();
    }

}
