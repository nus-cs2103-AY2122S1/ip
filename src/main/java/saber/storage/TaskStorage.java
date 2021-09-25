package saber.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import saber.Saber;
import saber.exceptions.SaberStorageLoadException;
import saber.exceptions.SaberStorageStoreException;
import saber.exceptions.SaberTaskTypeNotFoundException;
import saber.task.Deadline;
import saber.task.Event;
import saber.task.Task;
import saber.task.Todo;
import saber.tasklist.TaskList;

/**
 * A class to handle all loading and saving of tasks to the hard disk
 */
public class TaskStorage {
    protected String path;

    /**
     * A constructor for TaskStorage class
     * @param path the file path where the tasks will be saved at
     */
    public TaskStorage(String path) {
        this.path = path;
    }

    /**
     * A function to load tasks from the file
     * @return the tasks saved in the file as an arraylist of tasks
     * @throws SaberStorageLoadException if there is any error in parsing or IO
     */
    @SuppressWarnings("unchecked")
    // This method needs to suppress unchecked warnings due to the nature of the conversion
    // of JSON Object to saber.task.Task
    // This method will still be type safe as only tasks are stored in the file
    public ArrayList<Task> load() throws SaberStorageLoadException {
        JSONParser jsonParser = new JSONParser();
        ArrayList<Task> taskList = new ArrayList<>();

        // This will load from givenpath/data/tasks.json
        java.nio.file.Path path = java.nio.file.Paths.get(this.path, "data", "tasks.json");

        try {
            FileReader reader = new FileReader(path.toString());

            // Read JSON file
            Object obj = jsonParser.parse(reader);
            JSONArray taskJsonList = (JSONArray) obj;

            // Parse each Json Object as task and store it in the arraylist
            taskJsonList.forEach(task -> taskList.add(parseTaskObject((JSONObject) task)));

        } catch (FileNotFoundException e) {
            // Return an empty arraylist if the file is not found
            return new ArrayList<>();
        } catch (IOException | ParseException e) {
            // Throw exception if there is any parsing / IO error
            throw new SaberStorageLoadException("Fail to load tasks");
        }
        return taskList;
    }

    /**
     * A function to parse a JSON representative of a task object back into a Task object
     * @param task the JSON Object to be parsed
     * @return the task as a Task object
     */
    private static Task parseTaskObject(JSONObject task) {
        JSONObject taskObject = (JSONObject) task.get("task");

        String description = (String) taskObject.get("description");
        boolean isDone = (Boolean) taskObject.get("isDone");
        String type = (String) taskObject.get("type");
        String time = (String) taskObject.get("time");

        Saber.TaskType taskType;
        try {
            taskType = Saber.TaskType.valueOf(type);
        } catch (IllegalArgumentException e) {
            throw new SaberTaskTypeNotFoundException("Invalid saber.task.Task Type");
        }

        // Create the new task object depending on the task type
        Task parsedTask;
        switch (taskType) {
        case deadline :
            parsedTask = new Deadline(description, time, isDone);
            break;
        case event :
            parsedTask = new Event(description, time, isDone);
            break;
        case todo :
            parsedTask = new Todo(description, isDone);
            break;
        default :
            parsedTask = new Task(description, isDone);
            break;
        }

        return parsedTask;
    }

    /**
     * A function to store all the tasks in the TaskList in the hard disk. This function will store the tasks
     * at path/data/tasks.json
     * @param taskList the TaskList containing all the tasks to be stored
     * @param taskType an array of TaskType which corresponds to the task type of the tasks in the TaskList
     * @throws SaberStorageStoreException if there is any IO, InvalidPath or Security exceptions
     */
    @SuppressWarnings("unchecked")
    // This method needs to suppress unchecked warnings due to the nature of the conversion
    // of saber.task.Task to JSONObject
    // This method will still be type safe as only tasks are stored in the file
    public void store(TaskList taskList, Saber.TaskType[] taskType) throws SaberStorageStoreException {
        JSONArray jsonTaskList = new JSONArray();
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);

            JSONObject taskDetails = new JSONObject();
            taskDetails.put("description", task.getDescription());
            taskDetails.put("isDone", task.getIsDone());

            switch (taskType[i]) {
            case deadline :
                Deadline deadlineTask = (Deadline) task;

                taskDetails.put("type", taskType[i].name());
                taskDetails.put("time", deadlineTask.getTimeInString());
                break;
            case event :
                Event eventTask = (Event) task;

                taskDetails.put("type", taskType[i].name());
                taskDetails.put("time", eventTask.getTimeInString());
                break;
            default :
                taskDetails.put("type", taskType[i].name());
                taskDetails.put("time", "");
                break;
            }

            JSONObject taskObject = new JSONObject();
            taskObject.put("task", taskDetails);
            jsonTaskList.add(taskObject);
        }

        try {
            java.nio.file.Path path = java.nio.file.Paths.get(this.path, "data", "tasks.json");
            File file = new File(path.toString());

            // Suppress inspection for these methods because we want to ignore the result of these methods as in a
            // typical run these methods will not give any error as the path we are passing in is not a user-defined
            // path. As such, it is safe to suppress these warnings

            //noinspection ResultOfMethodCallIgnored
            file.delete();
            //noinspection ResultOfMethodCallIgnored
            file.getParentFile().mkdirs();

            // This will save the tasks into path/data/tasks.json
            FileWriter writer = new FileWriter(file);
            writer.write(jsonTaskList.toJSONString());
            writer.flush();
        } catch (IOException | InvalidPathException | SecurityException e) {
            throw new SaberStorageStoreException("Fail to save tasks");
        }
    }
}
