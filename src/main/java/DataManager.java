import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataManager {
    //TODO create file method
    //TODO error when tasklist is null for empty constructor
    private static final String STORAGE_PATH = "data/duke.txt";
    private static final File DATA_FILE = new File(STORAGE_PATH);

    private List<Task> taskList = new ArrayList<>();

    public DataManager() {}

    public DataManager(List<Task> taskList) {
        this.taskList = taskList;
    }

    public void listToTxt(FileWriter fileWriter) throws IOException {
        for (int i = 0; i < taskList.size(); i++) {
            String currentTask = taskList.get(i).toString() + "\n";
            fileWriter.write(currentTask);
        }
        fileWriter.close();
    }

    public List<Task> txtToList(File dataFile) throws IOException {
        List<Task> taskList = new ArrayList<>();
        String currentTaskString = "";
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

    private Task getTaskFromString(String taskString) {
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
                return new Task(); //todo error

        }
    }

    private boolean getTaskStatusFromString(String taskString) {
        String status = Character.toString(taskString.charAt(4));
        return status.equals("X");
    }

    private String getTaskNameFromString(String taskString) {
        int startingIndex = taskString.indexOf("] ");  //todo change hard code-ish?
        int endingIndex = taskString.indexOf("(");

        if (endingIndex < 0) { //case of todo
            return taskString.substring(startingIndex + 2);
        } else {
            return taskString.substring(startingIndex + 2, endingIndex - 2);
        }
    }

    private String getDateAndTimeFromString(String taskString) {
        int startingIndex = taskString.indexOf(":");
        int endingIndex = taskString.indexOf(")");

        return taskString.substring(startingIndex + 2, endingIndex - 1);
    }

    private String getTaskTypeFromString(String taskString) {
        Character taskType = taskString.charAt(1);
        switch (taskType) {
            case 'T':
                return "todo";
            case 'D':
                return "deadline";
            case 'E':
                return "event";
            default:
                return ""; //todo error
        }


    }

    public void saveData() {
        try {
            FileWriter fileWriter = new FileWriter(DATA_FILE);
            listToTxt(fileWriter);
        } catch (IOException e) {
            //TODO when file dont exists
            e.printStackTrace();
        }
    }

    public void update(List<Task> newTaskList) {
        this.taskList = newTaskList;
        saveData();
    }

}
