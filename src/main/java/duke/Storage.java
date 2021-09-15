package duke;

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

public class Storage {

    private final String filePath;

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

    private static Task dataToTask(String str) throws DukeException {
        try {
            String[] taskArr = str.split(",");
            String taskType = taskArr[0];
            boolean isTaskDone = taskArr[1].equals(Task.DONE_STRING);
            String taskDescription = taskArr[2];
            String taskDate = "";
            if (taskArr.length > Task.MIN_TASK_ATTRIBUTES) {
                taskDate = taskArr[Task.MIN_TASK_ATTRIBUTES];
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

    public void saveTaskToFile(Task task) throws DukeException {
        saveStringToFile(task.toFileData() + "\n");
    }

    private void saveStringToFile(String str) throws DukeException {
        try {
            FileWriter fileWriter = new FileWriter(this.filePath, true);
            fileWriter.write(str);
            fileWriter.close();
        }
        catch (IOException e) {
            throw new UpdateFileException();
        }
    }

    public void deleteTaskFromFile(int taskIndex, TaskList tasks) throws DukeException {
        updateTaskFromFile(taskIndex, true, tasks);
    }

    public void editTaskFromFile(int taskIndex, TaskList tasks) throws DukeException {
        updateTaskFromFile(taskIndex, false, tasks);
    }

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

    public void createNewFile() throws CreateFileException {
        try {
            FileWriter fileWriter = new FileWriter(filePath, false);
            fileWriter.close();
        } catch (IOException e) {
            throw new CreateFileException();
        }
    }

}
