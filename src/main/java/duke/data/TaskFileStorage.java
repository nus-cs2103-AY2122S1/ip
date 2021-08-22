package duke.data;

import duke.io.FileInputOutputHandler;
import duke.tasks.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * This class encapsulates the data layer for storing Tasks in a file.
 *
 * @author kevin9foong
 */
public class TaskFileStorage implements TaskStorage {
    private final FileInputOutputHandler taskFileInputOutputHandler;

    public TaskFileStorage() throws IOException {
        String taskFilePath = "data/tasks.txt";
        taskFileInputOutputHandler = new FileInputOutputHandler(taskFilePath);
    }

    private static List<Task> convertRepresentationStringsToTaskList(List<String> taskRepresentations) {
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
     * @param taskList list of tasks to be stored
     * @throws IOException thrown when failure to write to file occurs
     */
    public void saveTasks(List<Task> taskList) throws IOException {
        taskFileInputOutputHandler.saveLinesToFile(convertTaskListToRepresentationStrings(taskList));
    }

    /**
     * Reads the file containing string representations of tasks and returns the task list stored.
     *
     * @return List of tasks stored in text file
     * @throws IOException thrown when failure to read from file occurs
     */
    public List<Task> loadTasks() throws IOException {
        return convertRepresentationStringsToTaskList(taskFileInputOutputHandler.readLinesFromFile());
    }
}
