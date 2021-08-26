package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Storage {

    String folderName;
    String fileName;
    String dirPath;
    String filePath;
    Ui ui = new Ui();

    public Storage(String filePath) {
        String[] stringArr = filePath.split("/");
        folderName = stringArr[0];
        fileName = stringArr[1];
        dirPath = System.getProperty("user.dir") + "\\src\\main\\" + folderName;
        this.filePath = dirPath + "\\" + fileName;
    }

    public List<Task> load() throws InitialisationError {
        List<Task> taskList = new ArrayList<>();
        try {
            if (Files.notExists(Path.of(dirPath))) {
                Files.createDirectories(Path.of(dirPath));
            }
            File txtDataFile = new File(filePath);
            if (!txtDataFile.exists()) {
                ui.printErrorMessage("No stored data! Starting a brand new state!");
                txtDataFile = new File(filePath);
                txtDataFile.createNewFile();
            } else {
                // file exist, read the file.
                taskList = convertJsonToTaskListObj("dukeData.json");
            }
        } catch (Exception e) {
            throw new InitialisationError("An error occurred while reading file: " + fileName);
        }

        return taskList;

    }

    @SuppressWarnings("unchecked")
    public void saveData(TaskList taskList) {
//        String dirPath = System.getProperty("user.dir") + "\\src\\main\\data\\dukeData.json";
        File savedData = new File(filePath);

        //Add employees to list
        JSONArray jsonTaskList = new JSONArray();

        for (int i = 0; i < taskList.getTotalNumberOfTask(); i++) {
            Task currTask = taskList.getTaskById(i);
            JSONObject TaskDetails = new JSONObject();
            String type = currTask.getType().toString();
            TaskDetails.put("type", type);
            TaskDetails.put("value", currTask.getValue());
            TaskDetails.put("time", currTask.getTime() == null ? "" : currTask.getTime().toString());
            TaskDetails.put("done", currTask.isDone());

            JSONObject taskObj = new JSONObject();
            taskObj.put("task", TaskDetails);
            jsonTaskList.add(taskObj);
        }

        try {
            savedData.delete();
            savedData.createNewFile();
        } catch (Exception e) {
            System.out.println("An error occurred");
        }

        //Write JSON file
        try (FileWriter file = new FileWriter(filePath)) {
            //We can write any JSONArray or JSONObject instance to the file
            file.write(jsonTaskList.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public List<Task> convertJsonToTaskListObj(String fileName) {
        List<Task> taskList = new ArrayList<>();

        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
        String dirPath = System.getProperty("user.dir") + "\\src\\main\\data";
        try (FileReader reader = new FileReader(dirPath + "\\" + fileName)) {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray taskArr = (JSONArray) obj;

            //Iterate over task array
            taskArr.forEach(task -> taskList.add(parseTaskObject((JSONObject) task)));

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return taskList;

    }

    private Task parseTaskObject(JSONObject task) {
        //Get task object within list
        JSONObject taskObject = (JSONObject) task.get("task");

        String value = (String) taskObject.get("value");
        boolean done = (boolean) taskObject.get("done");
        String time = (String) taskObject.get("time");
        String type = (String) taskObject.get("type");
        type = type.toUpperCase();
        Task newTask = new Todo("");

        if (type.equals(CommandList.TODO.toString())) {
            newTask = new Todo(value);
        } else if (type.equals(CommandList.DEADLINE.toString())) {
            newTask = new Deadline(value, LocalDate.parse(time));
        } else if (type.equals(CommandList.EVENT.toString())) {
            newTask = new Event(value, LocalDate.parse(time));
        }

        if (done) {
            newTask.markDone();
        }
        return newTask;
    }

}
