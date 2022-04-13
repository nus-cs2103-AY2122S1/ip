package duke.utils;

import duke.exception.CreateFileException;
import duke.exception.DukeException;
import duke.exception.InvalidTaskTypeException;
import duke.exception.LoadFileException;
import duke.exception.UpdateFileException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/** Storage class for saving tasks to a user's file system */
public class Storage {

    private final String filePath;

    /**
     * Constructor for the Storage class
     * @param filePath The file path of the file that the Storage does operations on
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the specified filePath
     *
     * @return An ArrayList of Tasks loaded from the file.
     * @throws DukeException throws exception when Tasks cannot be loaded
     */
    public ArrayList<Task> loadTasks() throws DukeException {
        try {
            ArrayList<Task> res = new ArrayList<>();
            File taskFile = new File(this.filePath);
            if (!taskFile.createNewFile()) {
                Scanner fileReader = new Scanner(taskFile);
                while (fileReader.hasNextLine()) {
                    String data = fileReader.nextLine();
                    res.add(dataToTask(data));
                }
            }
            return res;
        } catch (IOException | DukeException e) {
            throw new LoadFileException();
        }
    }

    /**
     * Converts the data in the text file to a Task
     *
     * @param str A line representing a task in the text file E.g. T,1,TODO1
     * @return A Task instance built from str
     * @throws DukeException When the Task cannot be built
     */
    private static Task dataToTask(String str) throws DukeException {
        try {
            String delimiter = Task.STORAGE_DELIMITER;

            String[] taskArr = str.split(delimiter);
            String taskType = taskArr[Task.TASK_TYPE_INDEX];
            boolean isTaskDone = taskArr[Task.TASK_DONE_INDEX].equals(Task.DONE_STRING);
            String taskDescription = taskArr[Task.TASK_DESCRIPTION_INDEX];
            String taskDate = "";
            if (taskArr.length > Task.TASK_DATE_INDEX) {
                taskDate = taskArr[Task.TASK_DATE_INDEX];
            }
            Task res;
            switch (taskType) {
            case(Task.TODO_ALPHABET):
                res = new Todo(taskDescription, isTaskDone);
                break;
            case(Task.DEADLINE_ALPHABET):
                res = new Deadline(taskDescription, taskDate, isTaskDone);
                break;
            case(Task.EVENT_ALPHABET):
                res = new Event(taskDescription, taskDate, isTaskDone);
                break;
            default:
                throw new InvalidTaskTypeException(str);
            }
            return res;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new LoadFileException();
        }

    }

    /**
     * Saves task to the text file
     *
     * @param task The task to save to the file
     * @throws DukeException When the task cannot be saved
     */
    public void saveTaskToFile(Task task) throws DukeException {
        saveStringToFile(task.toFileData() + "\n");
    }

    /**
     * Saves a string into the file specified by the filePath
     *
     * @param str The String to be saved into the file
     * @throws DukeException When the String cannot be saved to the file
     */
    private void saveStringToFile(String str) throws DukeException {
        try {
            FileWriter fileWriter = new FileWriter(this.filePath, true);
            fileWriter.write(str);
            fileWriter.close();
        } catch (IOException e) {
            throw new UpdateFileException();
        }
    }

    /**
     * Deletes a task from the text file
     *
     * @param taskIndex Index of the task to delete from the file
     * @param tasks The TaskList that the task is from
     * @throws DukeException When the task cannot be deleted from the file
     */
    public void deleteTaskFromFile(int taskIndex, TaskList tasks) throws DukeException {
        updateTaskFromFile(taskIndex, true, tasks);
    }

    /**
     * Edits a task in the text file
     *
     * @param taskIndex Index of the task to edit in the file
     * @param tasks The TaskList that the task is from
     * @throws DukeException When the task cannot be edited from the file
     */
    public void editTaskFromFile(int taskIndex, TaskList tasks) throws DukeException {
        updateTaskFromFile(taskIndex, false, tasks);
    }

    /**
     * Updates (Edits or Deletes) a task in the file
     *
     * @param taskIndex The index of the task to be updated
     * @param isDeleteTask Whether to delete the task
     * @param tasks The TaskList that the task is from
     * @throws DukeException When the file cannot be updated
     */
    private void updateTaskFromFile(int taskIndex, boolean isDeleteTask, TaskList tasks) throws DukeException {
        try {
            StringBuilder newTasks = new StringBuilder();
            File taskFile = new File(filePath);
            Scanner fileReader = new Scanner(taskFile);
            int index = 0;
            while (fileReader.hasNextLine()) {
                if (index != taskIndex) {
                    newTasks.append(fileReader.nextLine()).append("\n");
                } else if (!isDeleteTask) {
                    newTasks.append(tasks.getTask(taskIndex).toFileData()).append("\n");
                    fileReader.nextLine();
                } else {
                    fileReader.nextLine();
                }
                index += 1;
            }
            FileWriter fileWriter = new FileWriter(filePath, false);
            fileWriter.write(String.valueOf(newTasks));
            fileWriter.close();
        } catch (IOException e) {
            throw new UpdateFileException();
        }
    }

    /**
     * Creates an empty text file at the filePath specified
     *
     * @throws CreateFileException When the file cannot be created
     */
    public void createNewFile() throws CreateFileException {
        try {
            FileWriter fileWriter = new FileWriter(filePath, false);
            fileWriter.close();
        } catch (IOException e) {
            throw new CreateFileException();
        }
    }

}
