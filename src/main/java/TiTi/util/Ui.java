package titi.util;

import java.util.Scanner;

/**
 * Represents the control for the user interface interaction.
 */
public class Ui {
    private static final String STARTER_NORMAL = "(=^ ･ｪ･^=) < ";
    private static final String STARTER_BUFFER = "       ";
    private static final String STARTER_SLEEPY = "(=^ ‐ｪ‐^=) < ";
    private static final String STARTER_CONFUSED = "(=^ ･x･^=)? < ";
    private static final String STARTER_HAPPY = "(=^ ･ω･^=)❀ < ";

    private TaskList taskList;
    private Parser parser;
    private boolean isContinue;
    private Scanner sc;

    /**
     * Initialises an Ui instance.
     *
     * @param savedHistory loads and writes to the saved data
     * @param taskList list of current tasks
     */
    public Ui(SavedHistory savedHistory, TaskList taskList) {
        this.taskList = taskList;
        this.parser = new Parser(savedHistory, taskList);
        isContinue = true;
        sc = new Scanner(System.in);
    }

    /**
     * Prints Welcome message when user first run programme.
     */
    public void welcome() {
        System.out.println(getWelcomeMessage());
    }

    /**
     * Returns string representation of welcome message
     *
     * @return Welcome message when user first run programme
     */
    public String getWelcomeMessage() {
        return STARTER_NORMAL + "Hello! I'm TiTi~ \n"
                + STARTER_BUFFER + "What would you like to do nya? \n";
    }

    /**
     * Returns false if programme has terminated. True if otherwise.
     *
     * @return if continue with programme
     */
    public boolean isContinue() {
        return isContinue;
    }

    /**
     * Scans for user command input, and print response to terminal.
     */
    public void cueUserCommand() {
        String input = sc.nextLine();
        System.out.println(getResponse(input));
    }

    /**
     * Returns string representation of the response for the user command
     * onto the user interface.
     *
     * @return response to user input
     */
    public String getResponse(String input) {
        Response response = parser.parse(input);
        TaskList tempTaskList = response.getTaskList();

        // Formulate response
        switch(response.getCue()) {
        case EXIT:
            return getExitResponse();

        case LIST:
            return getListResponse();

        case TASK_ERROR:
            return getTaskErrorResponse();

        case DONE:
            return getDoneResponse(tempTaskList);

        case DELETE:
            return getDeleteResponse(tempTaskList);

        case MISSING_DESCRIPTION:
            return getMissingDescriptionResponse();

        case TODO:
            return getToDoResponse(tempTaskList);

        case DEADLINE:
            return getDeadlineResponse(tempTaskList);

        case EVENT:
            return getEventResponse(tempTaskList);

        case FIND:
            return getFindResponse(tempTaskList);

        default:
            return getDefaultResponse();
        }
    }

    private String getExitResponse() {
        String result = "";
        isContinue = false;
        result += STARTER_SLEEPY + "Already done? Come back again soon nya~ \n";
        return result;
    }

    private String getListResponse() {
        String result = "";
        if (taskList.size() == 0) {
            result += STARTER_NORMAL + "Looks like you have no tasks nya~ \n";
            return result;
        }
        result += STARTER_NORMAL + "Have you competed these tasks nya? \n";
        for (int i = 0; i < taskList.size(); i++) {
            result += STARTER_BUFFER + (i + 1) + ". " + taskList.get(i) + " \n";
        }
        return result;
    }

    private String getTaskErrorResponse() {
        String result = "";
        result += STARTER_CONFUSED + "Nya?... I can't find the task... \n";
        return result;
    }


    private String getDoneResponse(TaskList tempTaskList) {
        String result = "";
        result += STARTER_HAPPY + "Nya! You've worked hard haven't you! \n";

        if (tempTaskList.size() == 1) {
            result += STARTER_BUFFER + "I'll mark this task as done: \n";
            result += STARTER_BUFFER + "  " + tempTaskList.get(0) + " \n";
            return result;
        }

        result += STARTER_BUFFER + "I'll mark these tasks as done: \n";
        for (int i = 0; i < tempTaskList.size(); i++) {
            result += STARTER_BUFFER + (i + 1) + ". " + tempTaskList.get(i) + " \n";
        }
        return result;
    }


    private String getDeleteResponse(TaskList tempTaskList) {
        String result = "";
        if (tempTaskList.size() == 1) {
            result += STARTER_NORMAL + "Nya! This task shall be removed: \n";
            result += STARTER_BUFFER + "  " + tempTaskList.get(0) + " \n";
            result += STARTER_BUFFER + printTaskCount(taskList) + " \n";
            return result;
        }

        result += STARTER_NORMAL + "Nya! These tasks shall be removed: \n";
        for (int i = 0; i < tempTaskList.size(); i++) {
            result += STARTER_BUFFER + (i + 1) + ". " + tempTaskList.get(i) + " \n";
        }
        result += STARTER_BUFFER + printTaskCount(taskList) + " \n";
        return result;
    }


    private String getMissingDescriptionResponse() {
        String result = "";
        result += STARTER_CONFUSED + "Nya? Give me a description of the task pwease. \n";
        return result;
    }

    private String getToDoResponse(TaskList tempTaskList) {
        String result = "";
        result += STARTER_NORMAL + "A new task? I'll add this to the list: \n";
        result += STARTER_BUFFER + "  " + tempTaskList.get(0) + " \n";
        result += STARTER_BUFFER + printTaskCount(taskList) + " \n";
        result += STARTER_BUFFER + "Don't forget to complete it nya~ \n";
        return result;
    }

    private String getDeadlineResponse(TaskList tempTaskList) {
        String result = "";
        result += STARTER_NORMAL + "A new deadline? Sounds tough nya... \n";
        result += STARTER_BUFFER + "  " + tempTaskList.get(0) + " \n";
        result += STARTER_BUFFER + printTaskCount(taskList) + " \n";
        result += STARTER_BUFFER + "Gambatte nya~ \n";
        return result;
    }

    private String getEventResponse(TaskList tempTaskList) {
        String result = "";
        result += STARTER_NORMAL + "A new event? Let me record it down: \n";
        result += STARTER_BUFFER + "  " + tempTaskList.get(0) + " \n";
        result += STARTER_BUFFER + printTaskCount(taskList) + " \n";
        result += STARTER_BUFFER + "I'll be waiting nya~ \n";
        return result;
    }

    private String getFindResponse(TaskList tempTaskList) {
        String result = "";
        result += STARTER_NORMAL + "Found it! Here you go! \n";
        for (int i = 0; i < tempTaskList.size(); i++) {
            result += STARTER_BUFFER + (i + 1) + ". " + tempTaskList.get(i) + " \n";
        }
        return result;
    }

    private String getDefaultResponse() {
        String result = "";
        result = STARTER_CONFUSED + "Nya?... I can't find what you are looking for... \n";
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
