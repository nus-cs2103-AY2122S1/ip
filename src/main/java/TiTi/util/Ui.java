package TiTi.util;

import TiTi.task.Task;

import java.util.Scanner;

/**
 * Control the interaction with the user.
 * Prints to the user interface.
 */
public class Ui {
    private static final String STARTER_NORMAL =   "(=^ ･ｪ･^=) < ";
    private static final String STARTER_BUFFER =   "       ";
    private static final String STARTER_SLEEPY =   "(=^ ‐ｪ‐^=) < ";
    private static final String STARTER_CONFUSED = "(=^ ･x･^=)? < ";
    private static final String STARTER_HAPPY =    "(=^ ･ω･^=)❀ < ";

    private SavedHistory savedHistory;
    private TaskList taskList;
    private Parser parser;
    private boolean isContinue;
    private Scanner sc;

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
        sc = new Scanner(System.in);
    }

    /**
     * Prints Welcome message when user first run programme.
     */
    public void welcome() {
        System.out.println(welcomeMessage());
    }

    public String welcomeMessage() {
        return STARTER_NORMAL + "Hello! I'm TiTi~ \n" +
                STARTER_BUFFER + "What would you like to do nya? \n";
    }



    /**
     * Return false if programme has terminated. True if otherwise.
     *
     * @return if continue with programme
     */
    public boolean isContinue() {
        return isContinue;
    }


    public void UserCommand() {
        String input = sc.nextLine();
        System.out.println(getResponse(input));
    }

    /**
     * Prints the response for the user command onto the user interface.
     */
    public String getResponse(String input) {
        Response response = parser.cue(input);
        TaskList tempTaskList = response.taskList;
        String result = "";

        switch(response.cue) {
        case EXIT:
            result += STARTER_SLEEPY + "Already done? Come back again soon nya~ \n";
            isContinue = false;
            break;

        case LIST:
            if (taskList.size() == 0) {
                result += STARTER_NORMAL + "Looks like you have no tasks nya~ \n";
                break;
            }
            result += STARTER_NORMAL + "Have you competed these tasks nya? \n";
            for (int i = 0; i < taskList.size(); i++) {
                result += STARTER_BUFFER + (i + 1) + ". " + taskList.get(i) + " \n";
            }
            break;

        case TASKERROR:
            result += STARTER_CONFUSED + "Nya?... I can't find the task... \n";
            break;

        case DONE:
            result += STARTER_HAPPY + "Nya! You've worked hard haven't you! \n";
            result += STARTER_BUFFER + "I'll mark this task as done: \n";
            result += STARTER_BUFFER + "  " + tempTaskList.get(0) + " \n";
            break;

        case DELETE:
            result += STARTER_NORMAL + "Nya! This task shall be removed: \n";
            result += STARTER_BUFFER + "  " + tempTaskList.get(0) + " \n";
            result += STARTER_BUFFER + printTaskCount(taskList) + " \n";
            break;

        case MISSINGDESCRIPTION:
            result += STARTER_CONFUSED + "Nya? Give me a description of the task pwease. \n";
            break;
            
        case TODO:
            result += STARTER_NORMAL + "A new task? I'll add this to the list: \n";
            result += STARTER_BUFFER + "  " + tempTaskList.get(0) + " \n";
            result += STARTER_BUFFER + printTaskCount(taskList) + " \n";
            result += STARTER_BUFFER + "Don't forget to complete it nya~ \n";
            break;

        case DEADLINE:
            result += STARTER_NORMAL + "A new deadline? Sounds tough nya... \n";
            result += STARTER_BUFFER + "  " + tempTaskList.get(0) + " \n";
            result += STARTER_BUFFER + printTaskCount(taskList) + " \n";
            result += STARTER_BUFFER + "Gambatte nya~ \n";
            break;

        case EVENT:
            result += STARTER_NORMAL + "A new event? Let me record it down: \n";
            result += STARTER_BUFFER + "  " + tempTaskList.get(0) + " \n";
            result += STARTER_BUFFER + printTaskCount(taskList) + " \n";
            result += STARTER_BUFFER + "I'll be waiting nya~ \n";
            break;

        case FIND:
            result += STARTER_NORMAL + "Found it! Here you go! \n";
            for (int i = 0; i < tempTaskList.size(); i++) {
                result += STARTER_BUFFER + (i + 1) + ". " + tempTaskList.get(i) + " \n";
            }
            break;

        default:
            result += STARTER_CONFUSED + "Nya?... I can't find what you are looking for... \n";
        }

        return result;
    }


    private String printTaskCount(TaskList taskList) {
        if (taskList.size() == 1) {
            return "We now have " + taskList.size() + " task on our list.";
        } else {
            return "We now have " + taskList.size() + " tasks on our list.";
        }
    }

}
