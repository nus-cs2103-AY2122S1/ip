package lifeline.storage;

import static lifeline.util.ErrorString.ERROR_UNABLE_TO_FIND_SAVED_TASKS;
import static lifeline.util.ErrorString.ERROR_UNABLE_TO_SAVE_TASK;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import lifeline.exception.LifelineException;
import lifeline.task.Deadline;
import lifeline.task.Event;
import lifeline.task.Task;
import lifeline.task.TaskList;
import lifeline.task.ToDo;

/**
 * The class Storage handles the saving of tasks to a JSON file and loading saved tasks from a JSON file.
 */
public class Storage {

    /**
     * File path to save the tasks.
     */
    private String filePath;

    /**
     * Used to convert Java Objects into their JSON representation.
     */
    private Gson gson;

    /**
     * Default constructor for a Storage.
     *
     * @param filePath Path to save and load tasks from.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    /**
     * Saves tasks to JSON.
     *
     * @param tasks tasks to be saved as JSON.
     * @throws LifelineException if unable to save to file.
     */
    public void save(TaskList tasks) throws LifelineException {
        createDirectoryIfMissing();
        writeToFile(tasks);
    }

    /**
     * Loads saved tasks from a JSON file.
     *
     * @return Saved tasks as TaskList.
     * @throws LifelineException if unable to load file.
     */
    public TaskList load() throws LifelineException {
        JsonArray tasksAsJson = parseSavedTasksAsJsonArray();
        TaskList savedTasks = getTasksFromJsonArray(tasksAsJson);
        return savedTasks;
    }

    private JsonArray parseSavedTasksAsJsonArray() throws LifelineException {
        try {
            FileReader fileReader = new FileReader(filePath);
            JsonArray tasksAsJson = JsonParser.parseReader(fileReader).getAsJsonArray();
            fileReader.close();
            return tasksAsJson;
        } catch (IOException e) {
            throw new LifelineException(ERROR_UNABLE_TO_FIND_SAVED_TASKS);
        }
    }

    private TaskList getTasksFromJsonArray(JsonArray tasksAsJson) {
        TaskList savedTasks = new TaskList(new ArrayList<Task>());
        for (int i = 0; i < tasksAsJson.size(); i++) {
            JsonObject currTask = tasksAsJson.get(i).getAsJsonObject();
            if (currTask.has("dueDate")) {
                addDeadlineTask(currTask, savedTasks);
            } else if (currTask.has("startTime")) {
                addEventTask(currTask, savedTasks);
            } else {
                addToDoTask(currTask, savedTasks);
            }
        }
        return savedTasks;
    }

    private void addDeadlineTask(JsonObject deadline, TaskList taskList) {
        String name = deadline.get("name").getAsString();
        LocalDate dueDate = gson.fromJson(deadline.get("dueDate"), LocalDate.class);
        LocalTime dueTime = gson.fromJson(deadline.get("dueTime"), LocalTime.class);
        boolean isDone = deadline.get("isDone").getAsBoolean();
        Deadline savedDeadline = new Deadline(name, dueDate, dueTime, isDone);
        taskList.add(savedDeadline);
    }

    private void addEventTask(JsonObject event, TaskList taskList) {
        String name = event.get("name").getAsString();
        LocalDate date = gson.fromJson(event.get("date"), LocalDate.class);
        LocalTime startTime = gson.fromJson(event.get("startTime"), LocalTime.class);
        LocalTime endTime = gson.fromJson(event.get("endTime"), LocalTime.class);
        boolean isDone = event.get("isDone").getAsBoolean();
        Event savedEvent = new Event(name, date, startTime, endTime, isDone);
        taskList.add(savedEvent);
    }

    private void addToDoTask(JsonObject toDo, TaskList taskList) {
        String name = toDo.get("name").getAsString();
        boolean isDone = toDo.get("isDone").getAsBoolean();
        ToDo savedToDo = new ToDo(name, isDone);
        taskList.add(savedToDo);
    }

    private void createDirectoryIfMissing() {
        int indexOfLastFileSeparator = filePath.indexOf(File.separator);
        String directoryToSaveTo = filePath.substring(0, indexOfLastFileSeparator == -1 ? 0 : indexOfLastFileSeparator);
        File directory = new File(directoryToSaveTo);
        directory.mkdirs();
    }

    private void writeToFile(TaskList tasks) throws LifelineException {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            fileWriter.write(gson.toJson(tasks.getTaskList(), ArrayList.class));
            fileWriter.close();
        } catch (IOException e) {
            throw new LifelineException(ERROR_UNABLE_TO_SAVE_TASK);
        }
    }
}
