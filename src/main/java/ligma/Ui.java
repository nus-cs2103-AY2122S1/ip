package ligma;

import ligma.task.Task;

public class Ui {
    private static final String PARTITION = "______________________";

    private static void printFormattedReply(String line) {
        System.out.println(PARTITION + "\n "
                + line + "\n" + PARTITION);
    }

    /**
     * Prints the lines given in proper format with partitioning.
     *
     * @param lines strings to be printed line by line
     */
    public static void printFormattedReply(String[] lines) {
        String reply = PARTITION;
        for (int i = 0; i < lines.length; i++) {
            reply += "\n " + lines[i];
        }
        reply += "\n" + PARTITION;
        System.out.println(reply);
    }

    /**
     * Prints all tasks from the tasklist given.
     *
     * @param tasks tasklist whose tasks are to be printed
     */
    public static void printTaskList(TaskList tasks) {
        String[] tasksText = tasks.getStringArr();
        int len = tasksText.length;
        for (int i = 0; i < len; i++) {
            tasksText[i] = (i + 1) + ". " + tasksText[i];
        }
        Ui.printFormattedReply(tasksText);
    }

    /**
     * Prints tasks that matches a target string for a FindCommand.
     *
     * @param tasks tasks that match the target string
     */
    public static void printFoundTasks(Task[] tasks) {
        int len = tasks.length;
        String reply = PARTITION + "\n Found " + len + " matches:";
        for (int i = 0; i < len; i++) {
            reply += String.format("\n %d." + tasks[i], i + 1);
        }
        reply += "\n" + PARTITION;
        System.out.println(reply);
    }

    /**
     * Prints Ligma's introduction.
     */
    public static void introduceSelf() {
        String[] intro = {"Hello! I'm Ligma, Ligma Balls.", "What can I do for you?"};
        printFormattedReply(intro);
    }

    /**
     * Prints Ligma's exit message.
     */
    public static void sayGoodbye() {
        String line1 = "Bye. I love Imagine Dragons...\n\n\n";
        String line2 = "Imagine Dragon Deez Nuts Cross Your Face.";
        String[] bye = {line1, line2};
        printFormattedReply(bye);
    }

    public static void printErrorMessage(Exception e) {
        printFormattedReply(e.getMessage());
    }
    public static void printErrorMessage(String errMsg) {
        printFormattedReply(errMsg);
    }

    public static void printSuccessMessage(String commandDesc) {
        printFormattedReply("Successfully " + commandDesc);
    }
}
