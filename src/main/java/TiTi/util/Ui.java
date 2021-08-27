package TiTi.util;

import TiTi.task.Task;

/**
 * Control the interaction with the user.
 * Prints to the user interface.
 */
public class Ui {
    private static final String STARTER_NORMAL =   "   (=^ ･ｪ･^=) < ";
    private static final String STARTER_BUFFER =   "                ";
    private static final String STARTER_SLEEPY =   "   (=^ ‐ｪ‐^=) < ";
    private static final String STARTER_CONFUSED = "   (=^ ･x･^=)? < ";
    private static final String STARTER_HAPPY =    "   (=^ ･ω･^=)❀ < ";

    private SavedHistory savedHistory;
    private TaskList taskList;
    private Parser parser;
    private boolean isContinue;

    /**
     * Constructor for Ui class.
     *
     * @param savedHistory loads and writes to the saved data
     * @param taskList list of current tasks
     */
    public Ui(SavedHistory savedHistory, TaskList taskList) {
        this.savedHistory = savedHistory;
        this.taskList = taskList;
        this.parser = new Parser(savedHistory, taskList);
        isContinue = true;
    }

    /**
     * Prints Welcome message when user first run programme.
     */
    public void welcome() {
        System.out.println(STARTER_NORMAL + "Hello! I'm TiTi~ ");
        System.out.println(STARTER_BUFFER + "What would you like to do nya? ");
    }

    /**
     * Return false if programme has terminated. True if otherwise.
     *
     * @return if continue with programme
     */
    public boolean isContinue() {
        return isContinue;
    }

    /**
     * Prints the response for the user command onto the user interface.
     */
    public void userCommand() {
        Response response = parser.cue();
        Task task = response.task;

        switch(response.cue) {
        case EXIT:
            System.out.println(STARTER_SLEEPY + "Already done? Come back again soon nya~");
            isContinue = false;
            break;

        case LIST:
            if (taskList.size() == 0) {
                System.out.println(STARTER_NORMAL + "Looks like you have no tasks nya~");
                break;
            }
            System.out.println(STARTER_NORMAL + "Have you competed these tasks nya?");
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println(STARTER_BUFFER + (i + 1) + ". " + taskList.get(i));
            }
            break;

        case TASKERROR:
            System.out.println(STARTER_CONFUSED + "Nya?... I can't find the task...");
            break;

        case DONE:
            System.out.println(STARTER_HAPPY + "Nya! You've worked hard haven't you!");
            System.out.println(STARTER_BUFFER + "I'll mark this task as done:");
            System.out.println(STARTER_BUFFER + "  " + task);
            break;

        case DELETE:
            System.out.println(STARTER_NORMAL + "Nya! This task shall be removed:");
            System.out.println(STARTER_BUFFER + "  " + task.toString());
            System.out.println(STARTER_BUFFER + printTaskCount(taskList));
            break;

        case MISSINGDESCRIPTION:
            System.out.println(STARTER_CONFUSED + "Nya? Give me a description of the task.");
            break;
            
        case TODO:
            System.out.println(STARTER_NORMAL + "A new task? I'll add this to the list:");
            System.out.println(STARTER_BUFFER + "  " + task);
            System.out.println(STARTER_BUFFER + printTaskCount(taskList));
            System.out.println(STARTER_BUFFER + "Don't forget to complete it nya~");
            break;

        case DEADLINE:
            System.out.println(STARTER_NORMAL + "A new deadline? Sounds tough nya...");
            System.out.println(STARTER_BUFFER + "  " + task);
            System.out.println(STARTER_BUFFER + printTaskCount(taskList));
            System.out.println(STARTER_BUFFER + "Gambatte nya~");
            break;

        case EVENT:
            System.out.println(STARTER_NORMAL + "A new event? Let me record it down:");
            System.out.println(STARTER_BUFFER + "  " + task);
            System.out.println(STARTER_BUFFER + printTaskCount(taskList));
            System.out.println(STARTER_BUFFER + "I'll be waiting nya~");
            break;

        default:
            System.out.println(STARTER_CONFUSED + "Nya?... I can't find what you are looking for...");
        }
    }


    private String printTaskCount(TaskList taskList) {
        if (taskList.size() == 1) {
            return "We now have " + taskList.size() + " task on our list.";
        } else {
            return "We now have " + taskList.size() + " tasks on our list.";
        }
    }


}
