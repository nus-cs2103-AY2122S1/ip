import java.util.ArrayList;

public class Command {
    private Task.TaskType taskType;
    private ArrayList<String> listOfCommandInputs;
    private String log;


    /**
     * Constructor for packaged command made by parser
     * @param taskType Task type
     * @param listOfCommandInputs The entire input entered by the user
     * @param log The logged statement. Used to update log since there are keywords that are not supposed to
     *            appear in the log statement
     */
    public Command(Task.TaskType taskType, ArrayList<String> listOfCommandInputs, String log) {
        this.taskType = taskType;
        this.listOfCommandInputs = listOfCommandInputs;
        this.log = log;
    }

    /**
     * Returns tasktype
     * @return Task type. Made task type into an enum for ease
     */
    public Task.TaskType getTaskType() {
        return this.taskType;
    }

    /**
     * Returns list of command inputs
     * @return ArrayList of strings, split by " ". Makes it easier to access individual words
     */
    public ArrayList<String> getListOfCommandInputs() {
        return this.listOfCommandInputs;
    }

    /**
     * Returns log statement
     * @return String for logging later
     */
    public String getLog() {
        return this.log;
    }
}
