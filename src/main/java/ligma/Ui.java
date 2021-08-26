package ligma;

import ligma.task.Task;

public class Ui {
    private static final String PARTITION = "______________________";

    private static void printFormattedReply(String line) {
        System.out.println(PARTITION + "\n "
                + line + "\n" + PARTITION);
    }

    public static void printFormattedReply(String[] lines) {
        String reply = PARTITION;
        for (int i = 0; i < lines.length; i++) {
            reply += "\n " + lines[i];
        }
        reply += "\n" + PARTITION;
        System.out.println(reply);
    }

    public static void printTaskList(TaskList tasks) {
        String[] tasksText = tasks.getStringArr();
        int len = tasksText.length;
        for (int i = 0; i < len; i++) {
            tasksText[i] = (i + 1) + ". " + tasksText[i];
        }
        Ui.printFormattedReply(tasksText);
    }

    public static void printFoundTasks(Task[] tasks) {
        int len = tasks.length;
        String reply = PARTITION + "\n Found " + len + " matches:";
        for (int i = 0; i < len; i++) {
            reply += String.format("\n %d." + tasks[i], i + 1);
        }
        reply += "\n" + PARTITION;
        System.out.println(reply);
    }

    public static void introduceSelf() {
        String[] intro = {"Hello! I'm Ligma, Ligma Balls.", "What can I do for you?"};
        printFormattedReply(intro);
    }

    public static void sayGoodbye() {
        String[] bye = {"Bye. I love Imagine Dragons...\n\n\n",
                "Imagine Dragon Deez Nuts Cross Your Face."};
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
