import java.util.Scanner;

public class Ui {
    private static final String introductionText = "Hello I am Kermit ( *・∀・)ノ゛, eaten any flies today?\nWhat can I do for you?";
    private static final String listText = "Here are the tasks in your list:";
    private static final String completeTaskText = "Ribbit Ribbit! Good job, task has been marked as complete:";
    private static final String goodbyeText = "Bye. Hope to see you again soon!";
    private static final String errorText = "Burp burp! Something went wrong!";
    private static final String loadingErrorText = "Could not read this file Nuuuuuuu!";
    Scanner sc = new Scanner(System.in);

    /**
     * Adds a top and bottom horizontal line to text
     *
     * @param text Text to be formatted.
     * @return Formatted version of text.
     */
    private static String formatText(String text) {
        String horizontalDivider = "____________________________________________________________________________";
        return horizontalDivider + "\n" + text + "\n" + horizontalDivider;
    }

    private void formatAndPrintText(String text) {
        System.out.println(formatText(text));
    }

    public void showIntroMessage() {
        formatAndPrintText(introductionText);
    }

    public void showGoodbyeMessage() {
        formatAndPrintText(goodbyeText);
    }

    public void showListItems(ToDo list) {
        formatAndPrintText(listText + "\n" + list);
    }

    /**
     * Pretty format text when task is added
     *
     * @param task Task that is added to list
     * @param list List that task was added to
     */
    public void showAddTaskMessage(Task task, ToDo list) {
        formatAndPrintText("Got it. I've added this task:\n"
                + task + "\nNow you have " + list.size() + " tasks in the list.");
    }

    public void showDeleteTaskMessage(Task task, ToDo list) {
        formatAndPrintText("Noted. I've removed this task:\n"
                + task + "\nNow you have " + list.size() + " tasks in the list.");
    }

    public void showCompleteTaskMessage(Task task) {
        formatAndPrintText(completeTaskText + "\n" + task);
    }

    public void showErrorMessage(KermitException e) {
        formatAndPrintText(errorText + "\n" + e.getMessage());
    }

    public void showLoadingError() {
        formatAndPrintText(loadingErrorText);
    }

    public String readCommand() {
        return sc.nextLine();
    }
}
