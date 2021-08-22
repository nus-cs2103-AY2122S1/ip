import java.util.ArrayList;

public class Ui {
    public final static String logo = " ____        _        \n"
                              + "|  _ \\ _   _| | _____ \n"
                              + "| | | | | | | |/ / _ \\\n"
                              + "| |_| | |_| |   <  __/\n"
                              + "|____/ \\__,_|_|\\_\\___|\n";
    public final static String INDENTED_HORIZONTAL_LINE = "    ____________________________________________________________";
    public final static String INDENT = "     ";
    public final static String INTRODUCTION = "Hello! I'm Ricky\n" + Ui.INDENT + "What can I do for you?";
    public final static String BYE_MESSAGE = "Bye. Hope to see you again soon!";
    public final static String DONE_MESSAGE = "Nice! I've marked this task as done:";
    public final static String LIST_MESSAGE = Ui.INDENT + "Here are the tasks in your list:";
    public final static String ERROR_MSG_UNKOWN_MSG = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    public final static String ERROR_MSG_EMPTY_DESCRIPTION = "☹ OOPS!!! The description of a todo cannot be empty.";
    public final static String NO_TASK_MESSAGE = "Good day! You have no task in hand right now.";

    public static void printMessage(String message) {
        System.out.println(Ui.INDENTED_HORIZONTAL_LINE);
        System.out.println(Ui.INDENT + message);
        System.out.println(Ui.INDENTED_HORIZONTAL_LINE + "\n");
    }

    public static void printIntro() {
        System.out.println(Ui.logo);
        printMessage(Ui.INTRODUCTION);
    }

    public static void printBye() {
        printMessage(Ui.BYE_MESSAGE);
    }

    public static void printList(ArrayList<Task> taskList) {
        if (taskList.size() == 0) {
            printMessage(Ui.NO_TASK_MESSAGE);
            return;
        }
        System.out.println(Ui.INDENTED_HORIZONTAL_LINE);
        System.out.println(Ui.LIST_MESSAGE);
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(Ui.INDENT + (i + 1) + ". " + taskList.get(i).toString());
        }
        System.out.println(Ui.INDENTED_HORIZONTAL_LINE + "\n");
    }
}
