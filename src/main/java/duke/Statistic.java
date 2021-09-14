package duke;

/**
 * Class to handle calculating certain statistics to show the user
 * todo number of todo tasks, event tasks, deadline tasks
 * todo number of done tasks
 */
public class Statistic {

    private Duke duke;
    private TaskList taskList;
    private Ui response;

    /**
     * Constructor for the Statistic class
     *
     * @param duke
     * @param taskList
     */
    public Statistic(Duke duke, TaskList taskList) {
        this.duke = duke;
        this.taskList = taskList;
        this.response = new Ui();
    }

    /**
     * Calculates the number of tasks of each type
     *
     * @return formatted message to send back to parser
     */
    public String numberOfTasks() {
        int numOfToDo = 0;
        int numOfEvent = 0;
        int numOfDeadline = 0;
        for (int i = 0; i < taskList.getNumberOfTasks(); i++) {
            String taskIcon = taskList.get(i).getTaskIcon();
            if (taskIcon == "T") {
                numOfToDo += 1;
            } else if (taskIcon == "E") {
                numOfEvent += 1;
            } else if (taskIcon == "D") {
                numOfDeadline += 1;
            }
        }
        return response.statTaskResponse(numOfToDo, numOfEvent, numOfDeadline);
    }

    /**
     * Calculates number of tasks marked as done
     *
     * @return formatted message to send back to parser
     */
    public String numberOfDoneTasks() {
        int result = taskList.getNumberOfDoneTasks();
        return response.statDoneResponse(result);
    }

    /**
     * Calculates number of tasks not marked as done
     *
     * @return formatted message to send back to parser
     */
    public String numberOfNotDoneTasks() {
        int result = taskList.getNumberOfNotDoneTasks();
        return response.statNotDoneResponse(result);
    }
}
