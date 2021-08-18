import java.util.ArrayList;

public class Command {
    private Task.TaskType taskType;
    private ArrayList<String> listOfCommandInputs;
    private String log;


    public Command(Task.TaskType taskType, ArrayList<String> listOfCommandInputs, String log) {
        this.taskType = taskType;
        this.listOfCommandInputs = listOfCommandInputs;
        this.log = log;
    }

    public Task.TaskType getTaskType() {
        return this.taskType;
    }

    public ArrayList<String> getListOfCommandInputs() {
        return this.listOfCommandInputs;
    }

    public String getLog() {
        return this.log;
    }
}
