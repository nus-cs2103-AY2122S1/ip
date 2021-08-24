import java.io.*;
import java.nio.file.InvalidPathException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class TaskStorage {
    protected String path;

    public TaskStorage(String path) {
        this.path = path;
    }

    @SuppressWarnings("unchecked")
    // This method needs to suppress unchecked warnings due to the nature of the conversion
    // of JSON Object to Task
    // This method will still be type safe as only tasks are stored in the file
    public ArrayList<Task> load() {
        JSONParser jsonParser = new JSONParser();

        ArrayList<Task> taskList = new ArrayList<>();
        java.nio.file.Path path = java.nio.file.Paths.get(this.path, "data", "tasks.json");

        try (FileReader reader = new FileReader(path.toString()))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
            JSONArray taskJsonList = (JSONArray) obj;

            taskJsonList.forEach( task -> taskList.add(parseTaskObject( (JSONObject) task )));

        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        } catch (IOException | ParseException e) {
            throw new SaberStorageLoadException("Fail to load tasks");
        }
        return taskList;
    }

    private static Task parseTaskObject(JSONObject task)
    {
        JSONObject taskObject = (JSONObject) task.get("task");

        String description = (String) taskObject.get("description");
        boolean isDone = (Boolean) taskObject.get("isDone");
        String type = (String) taskObject.get("type");
        String time = (String) taskObject.get("time");
        Saber.TaskType taskType;
        try {
            taskType =  Saber.TaskType.valueOf(type);
        }
        catch (IllegalArgumentException e) {
            throw new SaberTaskTypeNotFoundException("Invalid Task Type");
        }

        Task parsedTask;
        switch (taskType) {
            case deadline :
                parsedTask = new Deadline(description, time, isDone);
                break;

            case event :
                parsedTask = new Event(description, time, isDone);
                break;

            case todo :
                parsedTask = new ToDo(description, isDone);
                break;

            default :
                parsedTask = new Task(description, isDone);
                break;
        };

        return parsedTask;
    }

    @SuppressWarnings("unchecked")
    // This method needs to suppress unchecked warnings due to the nature of the conversion
    // of Task to JSONObject
    // This method will still be type safe as only tasks are stored in the file
    public void store(TaskList taskList, Saber.TaskType[] taskType) throws SaberStorageStoreException {
        JSONArray jsonTaskList = new JSONArray();
        for (int i = 0; i < taskList.size(); i ++) {
            Task task = taskList.get(i);

            JSONObject taskDetails = new JSONObject();
            taskDetails.put("description", task.description);
            taskDetails.put("isDone", task.isDone);

            switch (taskType[i]) {
                case deadline :
                    Deadline deadlineTask = (Deadline) task;
                    taskDetails.put("type", taskType[i].name());
                    taskDetails.put("time", deadlineTask.by);
                    break;

                case event :
                    Event eventTask = (Event) task;
                    taskDetails.put("type", taskType[i].name());
                    taskDetails.put("time", eventTask.at);
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
            FileWriter writer = new FileWriter(file);
            writer.write(jsonTaskList.toJSONString());
            writer.flush();
        } catch (IOException | InvalidPathException | SecurityException e) {
           throw new SaberStorageStoreException("Fail to save tasks");
        }
    }
}
