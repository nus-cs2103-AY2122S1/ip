import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class Storage {

    private String filepath;
    private Gson gson;

    Storage(String filepath) {
        this.filepath = filepath;
        this.gson = new Gson();
    }

    private void save(TaskList tasks) throws LifelineException {
        try {
            FileWriter fileWriter = new FileWriter("./save/tasks.json");
            fileWriter.write(gson.toJson(tasks, ArrayList.class));
            fileWriter.close();
        } catch (IOException e) {
            throw new LifelineException("Unable to save tasks at the moment");
        }
    }

    private TaskList load(String filepath) throws LifelineException {
        try {
            JsonArray arr = JsonParser.parseReader(new FileReader(filepath)).getAsJsonArray();
            TaskList savedTasks = new TaskList(new ArrayList<Task>());
            for (int i = 0; i < arr.size(); i++) {
                JsonObject currTask = arr.get(i).getAsJsonObject();
                if (currTask.has("by")) {
                    savedTasks.add(new Deadline(currTask.get("name").getAsString(),
                            gson.fromJson(currTask.get("by"), LocalDateTime.class),
                            currTask.get("isDone").getAsBoolean()));

                } else if (currTask.has("startTime")) {
                    savedTasks.add(new Event(currTask.get("name").getAsString(),
                            gson.fromJson(currTask.get("date"), LocalDate.class),
                            gson.fromJson(currTask.get("startTime"), LocalTime.class),
                            gson.fromJson(currTask.get("endTime"), LocalTime.class),
                            currTask.get("isDone").getAsBoolean()));
                } else {
                    savedTasks.add(new ToDo(currTask.get("name").getAsString(),
                            currTask.get("isDone").getAsBoolean()));
                }
            }
            return savedTasks;
        } catch (IOException e) {
            throw new LifelineException("Unable to find your saved tasks!\n");
        }
    }
}
