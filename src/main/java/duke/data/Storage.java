package duke.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import duke.exceptions.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Message;

/**
 * Stores the tasks and data provided by the user, so that when the program re-opens data can be restored.
 */
public class Storage {
    private File dataFile;

    private TaskList taskList = new TaskList();

    public Storage(String storagePath) {
        dataFile = new File(storagePath);
    }

    /**
     * Returns a boolean to whether a new data file is created.
     *
     * @return boolean of whether a new data file is created
     * @throws IOException if an I/O error occurs
     */
    public boolean createNewDataFile() throws IOException {
        if (!dataFile.exists()) {
            if (dataFile.getParentFile().mkdirs()) {
                System.out.println("Directory for file created.");
            }
            if (dataFile.createNewFile()) {
                System.out.println("File created: duke.txt");
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Loads the stored data into the program when it restarts.
     *
     * @return a list of stored tasks
     * @throws IOException if an I/O error occurs
     * @throws DukeException if a .txt file to list conversion error occurs
     */
    public List<Task> load() throws IOException, DukeException {
        return txtToList(dataFile);
    }

    /**
     * Converts a TaskList into a txt file to be stored.
     *
     * @param fileWriter fileWriter
     * @throws IOException if an I/O error occurs
     */
    public void listToTxt(FileWriter fileWriter) throws IOException {
        for (int i = 0; i < taskList.size(); i++) {
            String currentTask = taskList.getTask(i).toString() + "\n";
            fileWriter.write(currentTask);
        }
        fileWriter.close();
    }

    /**
     * Converts a txt file into a list of tasks to be loaded when the program restarts.
     *
     * @param dataFile the stored data file
     * @return a list of stored tasks
     * @throws IOException if an I/O error occurs
     */
    public List<Task> txtToList(File dataFile) throws IOException, DukeException {
        assert dataFile.exists() : "Data file does not exist";
        List<Task> taskList = new ArrayList<>();
        String currentTaskString;
        BufferedReader reader = new BufferedReader(new FileReader(dataFile));

        do {
            currentTaskString = reader.readLine();
            if (currentTaskString != null) {
                Task currentTask = getTaskFromString(currentTaskString);
                taskList.add(currentTask);
            }
        } while (currentTaskString != null);

        return taskList;

    }

    private Task getTaskFromString(String taskString) throws DukeException {
        String taskType = getTaskTypeFromString(taskString);
        String taskName = getTaskNameFromString(taskString);
        boolean taskStatus = getTaskStatusFromString(taskString);

        switch (taskType) {
        case "todo":
            return new Todo(taskName, taskStatus);
        case "deadline":
            String deadline = getDateAndTimeFromString(taskString);
            return new Deadline(taskName, deadline, taskStatus);
        case "event":
            String eventTime = getDateAndTimeFromString(taskString);
            return new Event(taskName, eventTime, taskStatus);
        default:
            throw new DukeException(Message.MESSAGE_TXT_TO_LIST_CONVERSION_ERROR);
        }
    }

    private boolean getTaskStatusFromString(String taskString) {
        String status = Character.toString(taskString.charAt(4));
        return status.equals("X");
    }

    private String getTaskNameFromString(String taskString) {
        int startingIndex = taskString.indexOf("] ");
        int endingIndex = taskString.indexOf("(");
        boolean isStartingIndexValid = startingIndex > 0;
        assert isStartingIndexValid : "Invalid task format in txt";

        if (endingIndex < 0) { //case of todo
            return taskString.substring(startingIndex + 2);
        } else {
            return taskString.substring(startingIndex + 2, endingIndex - 1);
        }
    }

    private String getDateAndTimeFromString(String taskString) {
        int startingIndex = taskString.indexOf(":");
        int endingIndex = taskString.indexOf(")");
        assert startingIndex > 0 && endingIndex > 0 : "Invalid task format in txt";

        return taskString.substring(startingIndex + 2, endingIndex);
    }

    private String getTaskTypeFromString(String taskString) throws DukeException {
        char taskType = taskString.charAt(1);
        switch (taskType) {
        case 'T':
            return "todo";
        case 'D':
            return "deadline";
        case 'E':
            return "event";
        default:
            assert false : "Invalid task type from txt";
            throw new DukeException(Message.MESSAGE_TXT_TO_LIST_CONVERSION_ERROR);
        }
    }

    /**
     * Saves the task list into the storage.
     */
    public void saveData() {
        try {
            FileWriter fileWriter = new FileWriter(dataFile);
            listToTxt(fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates the storage with the new task list.
     *
     * @param newTaskList the updated task list
     */
    public void update(TaskList newTaskList) {
        this.taskList = newTaskList;
        saveData();
    }

}
