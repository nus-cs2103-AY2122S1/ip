package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskType;
import duke.task.TaskWithDateTime;
import duke.task.ToDo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Queue;

/**
 * A class that contains methods to read from and write to the storage file
 */
public class Storage {
    private String pathName;
    private String fileName;
    private File dataDirectory;
    private File dataFile;

    /**
     * Initializes an instance of Storage class.
     * @param pathName Path of the file
     * @param fileName Name of the file
     * @throws DukeExceptio If initializing the directory fails
     * @throws IOException If initializing the file fails
     */
    public Storage(String pathName, String fileName) throws DukeException, IOException {
        this.pathName = pathName;
        this.fileName = fileName;
        dataDirectory = initialiseDirectory();
        dataFile = initialiseFile(dataDirectory);
    }

    /**
     * Creates the directory for the storage file if the directory does not exist.
     * @return The File object
     * @throws DukeException If the directory cannot be created
     */
    private File initialiseDirectory() throws DukeException {
        File directory = new File(pathName);
        boolean hasDirectory = directory.exists();

        if (!hasDirectory) {
            hasDirectory = directory.mkdir();
        }

        if (hasDirectory) {
            return directory;
        } else {
            throw new DukeException("\t" + "Unable to initialise directory");
        }
    }

    /**
     * Creates the storage file if it does not exist.
     * @param directory The directory of the storage file
     * @return The File object
     * @throws IOException If the file cannot be created
     */
    private File initialiseFile(File directory) throws IOException {
        File file = new File(directory + "/" + fileName);
        boolean hasFile = file.exists();

        if (!hasFile) {
            hasFile = file.createNewFile();
        }

        if (hasFile) {
            return file;
        } else {
            throw new IOException("\t" + "Unable to initialise file");
        }
    }

    /**
     * Loads tasks from the storage file.
     * @return The list of tasks
     * @throws IOException If the tasks cannot be loaded
     */
    public TaskList loadTasksFromFile() throws IOException {
        TaskList taskList = new TaskList();

        FileReader fileReader = new FileReader(dataFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;

        while ((line = bufferedReader.readLine()) != null) {
            String[] taskDetails = line.trim().split("\\|");
            String type = taskDetails[0].trim();
            boolean isDone = (Integer.parseInt(taskDetails[1].trim()) == 1);
            String description = taskDetails[2].trim();
            String dateTime;

            switch (type) {
            case "T":
                Task todoTask = new ToDo(TaskType.TODO, description, isDone);
                taskList.add(todoTask);
                break;
            case "D":
                dateTime = taskDetails[3].trim();
                Task deadlineTask = new Deadline(TaskType.DEADLINE, description, dateTime, isDone);
                taskList.add(deadlineTask);
                break;
            case "E":
                dateTime = taskDetails[3].trim();
                Task eventTask = new Event(TaskType.EVENT, description, dateTime, isDone);
                taskList.add(eventTask);
                break;
            }
        }

        bufferedReader.close();

        return taskList;
    }

    /**
     * Saves the tasks to the storage file.
     * @param taskList The list of tasks to be saved
     * @throws IOException If the tasks cannot be saved
     */
    public void saveTasksToFile(TaskList taskList) throws IOException {
        FileWriter fileWriter = new FileWriter(dataFile,false);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);

            TaskType type = task.getType();
            boolean isDone = task.isDone();
            String description = task.getDescription();
            String dateTime;

            if (type == TaskType.TODO) {
                dateTime = "";
            } else {
                dateTime = ((TaskWithDateTime) task).getDateTimeInput();
            }

            String taskToSave = taskSaveFormat(type, isDone, description, dateTime);
            bufferedWriter.write(taskToSave + System.lineSeparator());
        }

        bufferedWriter.close();
    }
`
    /**
     * Creates and returns the formatted task details to be saved.
     * @param type Type of task
     * @param isDone Flag to indicate if the task has been completed
     * @param description The task description
     * @param dateTime The date/time of the task
     * @return The formatted task details
     */
    private String taskSaveFormat(TaskType type, boolean isDone, String description, String dateTime) {
        if (dateTime.equals("")) {
            return type.getAbbr() + " | " + (isDone ? "1" : "0") + " | " + description;
        } else {
            return type.getAbbr() + " | " + (isDone ? "1" : "0") + " | " + description + " | " + dateTime;
        }
    }
}
