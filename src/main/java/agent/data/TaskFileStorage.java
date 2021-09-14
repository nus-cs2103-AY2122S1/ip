package agent.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import agent.exceptions.InvalidTaskDataException;
import agent.exceptions.TaskFileIoException;
import agent.io.FileInputOutputHandler;
import agent.tasks.Task;

/**
 * This class encapsulates the data layer for storing Tasks in a file.
 * Provides saving and loading functionality from the file used to store <code>Task</code>
 * data.
 *
 * @author kevin9foong
 */
public class TaskFileStorage implements TaskStorage {
    private final FileInputOutputHandler taskFileInputOutputHandler;

    /**
     * Initializes a <code>TaskFileOutputHandler</code> which performs read and write
     * operations on file on specified file path.
     *
     * @throws TaskFileIoException thrown when failure to read or write to Task save file occurs.
     */
    public TaskFileStorage() throws TaskFileIoException {
        String taskFilePath = "data/tasks.txt";
        try {
            taskFileInputOutputHandler = new FileInputOutputHandler(taskFilePath);
        } catch (IOException ioe) {
            throw new TaskFileIoException();
        }
    }

    private static List<Task> convertRepresentationStringsToTaskList(List<String> taskRepresentations)
            throws InvalidTaskDataException {
        List<Task> taskList = new ArrayList<>();
        for (String taskRep : taskRepresentations) {
            Task task = Task.getTaskFromRepresentation(taskRep);
            if (task != null) {
                taskList.add(task);
            }
        }
        return taskList;
    }

    private static List<String> convertTaskListToRepresentationStrings(List<Task> taskList) {
        List<String> taskRepresentations = new LinkedList<>();
        for (Task task : taskList) {
            taskRepresentations.add(task.getTaskRepresentation());
        }

        return taskRepresentations;
    }

    /**
     * Writes the string representation of each task in given task list into a text file.
     *
     * @param taskList list of tasks to be stored.
     * @throws TaskFileIoException thrown when failure to write to specified save file occurs.
     */
    public void saveTasks(List<Task> taskList) throws TaskFileIoException {
        try {
            taskFileInputOutputHandler.saveLinesToFile(convertTaskListToRepresentationStrings(taskList));
        } catch (IOException ioe) {
            throw new TaskFileIoException();
        }
    }

    /**
     * Reads the file containing string representations of tasks and returns the task list stored.
     *
     * @return list of tasks stored in text file.
     * @throws TaskFileIoException thrown when failure to read from specified save file occurs.
     */
    public List<Task> loadTasks() throws TaskFileIoException, InvalidTaskDataException {
        try {
            return convertRepresentationStringsToTaskList(taskFileInputOutputHandler.readLinesFromFile());
        } catch (IOException ioe) {
            throw new TaskFileIoException();
        }
    }
}
