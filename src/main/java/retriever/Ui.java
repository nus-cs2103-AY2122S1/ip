package retriever;

import java.util.ArrayList;

import retriever.task.Task;

/**
 * The class handles the user interaction between the
 * user and the chatbot.
 */
public class Ui {
    // Response would be stored here
    private static String retrieverResponse;

    // Help Section Strings.
    private static final String WELCOME_TO_HELP_SECTION_MESSAGE = "Master, I shall guide you to my best "
            + "ability to smell.";
    private static final String BYE_COMMAND_MESSAGE = "Use the bye command to end the session.";
    private static final String BYE_COMMAND_EXAMPLE = "Command Example: bye";
    private static final String DEADLINE_COMMAND_MESSAGE = "Use the deadline command to add a deadline type task.";
    private static final String DEADLINE_COMMAND_EXAMPLE = "Command Example: deadline CS2103T iP /by 23/09/2021";
    private static final String DEADLINE_COMMAND_FORMAT = "-> deadline TASK_DESCRIPTION /by DD/MM/YYYY";
    private static final String DELETE_COMMAND_MESSAGE = "Use the delete command to delete a task.";
    private static final String DELETE_COMMAND_EXAMPLE = "Command Example: delete 4";
    private static final String DELETE_COMMAND_FORMAT = "-> delete TASK_NUMBER";
    private static final String DONE_COMMAND_MESSAGE = "Use the done command to mark a task as done.";
    private static final String DONE_COMMAND_EXAMPLE = "Command Example: done 3";
    private static final String DONE_COMMAND_FORMAT = "-> done TASK_NUMBER";
    private static final String EVENT_COMMAND_MESSAGE = "Use the event command to add an event type task.";
    private static final String EVENT_COMMAND_EXAMPLE = "Command Example: event Apple iPhone 13 /at 14/09/2021";
    private static final String EVENT_COMMAND_FORMAT = "-> event TASK_DESCRIPTION /at DD/MM/YYYY";
    private static final String FIND_COMMAND_MESSAGE = "Use the find command to find tasks.";
    private static final String FIND_COMMAND_EXAMPLE = "Command Example: find notes";
    private static final String FIND_COMMAND_FORMAT = "-> find KEYWORD";
    private static final String HELP_COMMAND_MESSAGE = "Use the help command to open the help section.";
    private static final String HELP_COMMAND_EXAMPLE = "Command Example: help";
    private static final String LIST_COMMAND_MESSAGE = "Use the list command to view the tasks "
            + "currently present in the task list.";
    private static final String LIST_COMMAND_EXAMPLE = "Command Example: list";
    private static final String TODO_COMMAND_MESSAGE = "Use the todo command to add a todo type task.";
    private static final String TODO_COMMAND_EXAMPLE = "Command Example: todo laundry";
    private static final String TODO_COMMAND_FORMAT = "-> todo TASK_DESCRIPTION";
    private static final String VIEW_COMMAND_MESSAGE = "Use the view command to view scheduled tasks for a day.";
    private static final String VIEW_COMMAND_EXAMPLE = "Command Example: view 23/09/2021";
    private static final String VIEW_COMMAND_FORMAT = "-> view DD/MM/YYYY";
    private static final String TASK_STORING_AND_RETRIEVING_ISSUE = "If you have observed that your tasks are not being"
            + " stored or retrieved well, kindly check the Console or the file \"storageLog.log\" to "
            + "identify the error.";

    // Predetermined strings.
    private final String WELCOME_MESSAGE = "Hello, I am Retriever\nHow Can I Help You Today?";
    private final String GOOD_BYE_MESSAGE = "-> Sad To See You Go!";
    private final String EMPTY_LIST_MESSAGE = "My Memory Is Empty, Please Feed Items!";
    private final String NO_TASK_FOUND_MESSAGE = "Sorry Master, I Couldn't Smell And Find What You Asked For!"
            + "\n (Task With the Given Keyword Does Not Exist)\n";
    private final String PRINT_LIST_MESSAGE = "-> Your Tasks, My Master:";
    private final String GOOD_BOY_MESSAGE = "Woof! Whose a Good Boy?";
    private final String BAD_BOY_MESSAGE = "Woof! Whose a Bad Boy?";
    private final String SCHEDULE_EMPTY_MESSAGE = "Master, You Can Play With Me, No Scheduled Tasks For The "
            + "Day, Woooof!";
    private final String SCHEDULE_MESSAGE = "Master, You are Busy For The Day...";

    // Dog Logo
    private final String LOGO = "\t  __      ^\n"
            + "\to'')}____//\n"
            + "\t`_'      )\n"
            + "\t(_(_/-(_/\n";

    /**
     * Initializes the Ui class.
     */
    public Ui() {
    }

    /**
     * Prints the help section.
     */
    public static void printHelpSection() {
        retrieverResponse = WELCOME_TO_HELP_SECTION_MESSAGE + "\n\n"
                + BYE_COMMAND_MESSAGE + "\n" + BYE_COMMAND_EXAMPLE + "\n\n"
                + DEADLINE_COMMAND_MESSAGE + "\n" + DEADLINE_COMMAND_FORMAT + "\n" + DEADLINE_COMMAND_EXAMPLE + "\n\n"
                + EVENT_COMMAND_MESSAGE + "\n" + EVENT_COMMAND_FORMAT + "\n" + EVENT_COMMAND_EXAMPLE + "\n\n"
                + TODO_COMMAND_MESSAGE + "\n" + TODO_COMMAND_FORMAT + "\n" + TODO_COMMAND_EXAMPLE + "\n\n"
                + DELETE_COMMAND_MESSAGE + "\n" + DELETE_COMMAND_FORMAT + "\n" + DELETE_COMMAND_EXAMPLE + "\n\n"
                + DONE_COMMAND_MESSAGE + "\n" + DONE_COMMAND_FORMAT + "\n" + DONE_COMMAND_EXAMPLE + "\n\n"
                + FIND_COMMAND_MESSAGE + "\n" + FIND_COMMAND_FORMAT + "\n" + FIND_COMMAND_EXAMPLE + "\n\n"
                + LIST_COMMAND_MESSAGE + "\n" + LIST_COMMAND_EXAMPLE + "\n\n"
                + HELP_COMMAND_MESSAGE + "\n" + HELP_COMMAND_EXAMPLE + "\n\n"
                + VIEW_COMMAND_MESSAGE + "\n" + VIEW_COMMAND_FORMAT + "\n" + VIEW_COMMAND_EXAMPLE + "\n\n"
                + TASK_STORING_AND_RETRIEVING_ISSUE + "\n\n";
    }

    /**
     * Prints the message received as an argument.
     *
     * @param message The message user would like to print.
     */
    public void printMessage(String message) {
        retrieverResponse = message;
    }

    /**
     * Prints the welcome message.
     */
    public void printWelcomeMessage() {
        retrieverResponse = WELCOME_MESSAGE + "\n" + LOGO + "\n"
                + "Need Help? Type 'help'" + "\n" + "in the text field below.";
    }

    /**
     * Prints the goodbye message.
     */
    public void printGoodByeMessage() {
        retrieverResponse = GOOD_BYE_MESSAGE;
    }

    /**
     * Prints the error message.
     *
     * @param errorMessage The exception message to be printed.
     */
    public void printErrorMessage(String errorMessage) {
        retrieverResponse = errorMessage;
    }

    /**
     * Prints the task added message in a specified format.
     *
     * @param task The task that has been added.
     * @param taskListLength The length of the task list, after adding the task.
     */
    public void printTaskAdded(Task task, int taskListLength) {
        retrieverResponse = "Where's My Treat? I Added:\n"
                + task + "\nYou Owe Me " + taskListLength + " Treat(s), Master!";
    }

    /**
     * Prints the task deleted message in a specified format.
     *
     * @param task The task that has been deleted.
     * @param taskListLength The length of the task list, after deleting the task.
     */
    public void printTaskDeleted(Task task, int taskListLength) {
        retrieverResponse = BAD_BOY_MESSAGE + "\n"
                + "Task Deleted!\n"
                + task
                + "\nYou Owe Me " + taskListLength + " Treat(s), Master!";
    }

    /**
     * Prints the task done message in a specified format.
     *
     * @param task The task that has been done.
     */
    public void printTaskMarkedAsDone(Task task) {
        retrieverResponse = GOOD_BOY_MESSAGE + "\n"
                + "Task Done!\n"
                + task;
    }

    /**
     * Prints the tasks with a matching keyword.
     *
     * @param taskList The ArrayList containing tasks with matching keyword.
     */
    public void printTasksFoundByKeyword(ArrayList<Task> taskList) {
        if (taskList.size() == 0) {
            retrieverResponse = NO_TASK_FOUND_MESSAGE;
            return;
        }

        retrieverResponse = "Woof! Look What I Found: \n";

        for (int i = 0; i < taskList.size(); i++) {
            retrieverResponse += (i + 1) + ". " + taskList.get(i) + "\n";
        }

        retrieverResponse += "You Owe Me " + taskList.size() + " Treat(s), Master!";
    }

    /**
     * Prints the tasks which are scheduled for the date entered.
     *
     * @param taskList The ArrayList containing tasks scheduled for the date entered.
     */
    public void printTasksScheduledForTheDay(ArrayList<Task> taskList) {
        if (taskList.size() == 0) {
            retrieverResponse = SCHEDULE_EMPTY_MESSAGE;
            return;
        }

        retrieverResponse = SCHEDULE_MESSAGE + "\n";

        for (int i = 0; i < taskList.size(); i++) {
            retrieverResponse += (i + 1) + ". " + taskList.get(i) + "\n";
        }
    }

    /**
     * Prints the tasks stored in the list.
     *
     * @param taskList The ArrayList containing the tasks to be printed.
     */
    public void printTaskList(ArrayList<Task> taskList) {
        // If the list is empty
        if (taskList.size() == 0) {
            retrieverResponse = EMPTY_LIST_MESSAGE;
        } else {
            retrieverResponse = PRINT_LIST_MESSAGE + "\n";
            for (int i = 0; i < taskList.size(); i++) {
                retrieverResponse += ((i + 1) + ". " + taskList.get(i)) + "\n";
            }
        }
    }

    /**
     * Returns the output that the command generated.
     *
     * @return The response to the command entered.
     */
    public String getRetrieverResponse() {
        return retrieverResponse;
    }
}

