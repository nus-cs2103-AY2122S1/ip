package retriever;

import java.util.ArrayList;

import retriever.task.Task;

/**
 * The class handles the user interaction between the
 * user and the chatbot.
 */
public class Ui {
    private static String retrieverResponse;

    // Help Section Strings.
    private static String welcomeToHelpSectionMessage = "Master, I shall guide you to my best ability to smell.";
    private static String byeCommandMessage = "Use the bye command to end the session.";
    private static String byeCommandExample = "Command Example: bye";
    private static String deadlineCommandMessage = "Use the deadline command to add a deadline type task.";
    private static String deadlineCommandExample = "Command Example: deadline CS2103T iP /by 23/09/2021";
    private static String deadlineCommandFormat = "-> deadline TASK_DESCRIPTION /by DD/MM/YYYY";
    private static String deleteCommandMessage = "Use the delete command to delete a task.";
    private static String deleteCommandExample = "Command Example: delete 4";
    private static String deleteCommandFormat = "-> delete TASK_NUMBER";
    private static String doneCommandMessage = "Use the done command to mark a task as done.";
    private static String doneCommandExample = "Command Example: done 3";
    private static String doneCommandFormat = "-> done TASK_NUMBER";
    private static String eventCommandMessage = "Use the event command to add an event type task.";
    private static String eventCommandExample = "Command Example: event Apple iPhone 13 /at 14/09/2021";
    private static String eventCommandFormat = "-> event TASK_DESCRIPTION /at DD/MM/YYYY";
    private static String findCommandMessage = "Use the find command to find tasks.";
    private static String findCommandExample = "Command Example: find notes";
    private static String findCommandFormat = "-> find KEYWORD";
    private static String helpCommandMessage = "Use the help command to open the help section.";
    private static String helpCommandExample = "Command Example: help";
    private static String listCommandMessage = "Use the list command to view the tasks "
            + "currently present in the task list.";
    private static String listCommandExample = "Command Example: list";
    private static String todoCommandMessage = "Use the todo command to add a todo type task.";
    private static String todoCommandExample = "Command Example: todo laundry";
    private static String todoCommandFormat = "-> todo TASK_DESCRIPTION";
    private static String viewCommandMessage = "Use the view command to view scheduled tasks for a day.";
    private static String viewCommandExample = "Command Example: view 23/09/2021";
    private static String viewCommandFormat = "-> view DD/MM/YYYY";

    // Predetermined strings.
    private String emptyListMessage = "My Memory Is Empty, Please Feed Items!";
    private String printListMessage = "-> Your Tasks, My Master:";
    private String goodBoyMessage = "Woof! Whose a Good Boy?";
    private String badBoyMessage = "Woof! Whose a Bad Boy?";
    private String scheduleMessage = "Master, You are Busy For The Day...";

    /**
     * Sets up the scanner to take in user input.
     */
    public Ui() {
    }

    /**
     * Prints the help section.
     */
    public static void printHelpSection() {
        retrieverResponse = welcomeToHelpSectionMessage + "\n\n"
                + byeCommandMessage + "\n" + byeCommandExample + "\n\n"
                + deadlineCommandMessage + "\n" + deadlineCommandFormat + "\n" + deadlineCommandExample + "\n\n"
                + eventCommandMessage + "\n" + eventCommandFormat + "\n" + eventCommandExample + "\n\n"
                + todoCommandMessage + "\n" + todoCommandFormat + "\n" + todoCommandExample + "\n\n"
                + deleteCommandMessage + "\n" + deleteCommandFormat + "\n" + deleteCommandExample + "\n\n"
                + doneCommandMessage + "\n" + doneCommandFormat + "\n" + doneCommandExample + "\n\n"
                + findCommandMessage + "\n" + findCommandFormat + "\n" + findCommandExample + "\n\n"
                + listCommandMessage + "\n" + listCommandExample + "\n\n"
                + helpCommandMessage + "\n" + helpCommandExample + "\n\n"
                + viewCommandMessage + "\n" + viewCommandFormat + "\n" + viewCommandExample + "\n\n";
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
        retrieverResponse = badBoyMessage + "\n"
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
        retrieverResponse = goodBoyMessage + "\n"
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
            retrieverResponse = "Sorry Master, I Couldn't Smell And Find What You Asked For!"
                    + "\n (Task With the Given Keyword Does Not Exist)\n";
            return;
        }

        retrieverResponse = "Woof! Look What I Found: /n";

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
            retrieverResponse = "Master, You Can Play With Me, No Scheduled Tasks For The Day, Woooof!";
            return;
        }

        retrieverResponse = scheduleMessage + "\n";
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
            retrieverResponse = emptyListMessage;
        } else {
            retrieverResponse = printListMessage + "\n";
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

