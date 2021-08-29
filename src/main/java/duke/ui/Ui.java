package duke.ui;

import duke.data.messages.Messages;

public class Ui {

    private static final String DIVIDER = "____________________________________________________________";
    private static final String TAB = "  ";
    private static final String LS = System.lineSeparator();


    public static void prettify(String message){
        System.out.println(TAB + DIVIDER);
        System.out.println(message);
        System.out.println(TAB + DIVIDER);
    }

    public static void printWelcomeMsg() {
        String message =  TAB + TAB + Messages.MESSAGE_WELCOME_1 + LS + TAB + TAB + Messages.MESSAGE_WELCOME_2;
        prettify(message);
    }

    public static void printGoodbyeMsg() {
        prettify(Messages.MESSAGE_GOODBYE);
    }

    public static String emptyDescriptionMsg(String type) {
        String errorMsg = TAB + TAB + "☹ OOPS!!! ";
        switch(type) {
        case "todo":
            errorMsg += Messages.MESSAGE_EMPTY_TODO;
            break;
        case "deadline":
            errorMsg += Messages.MESSAGE_EMPTY_DEADLINE;

            break;
        case "event":
            errorMsg += Messages.MESSAGE_EMPTY_EVENT;
            break;
        case "delete":
            errorMsg += Messages.MESSAGE_EMPTY_DELETE;
            break;
        case "done":
            errorMsg += Messages.MESSAGE_EMPTY_DONE;
            break;
        case "find":
            errorMsg += Messages.MESSAGE_EMPTY_FIND;
            break;
        }
        return errorMsg;
    }

    public static String unknownInputMsg() {
        return TAB + TAB + Messages.MESSAGE_INPUT_UNKNOWN;
    }

    public static void printCreateDirectoryErr() {
        System.err.println("IOException: unable to create directory");
    }

    public static String ioMsg(){
        return Messages.MESSAGE_IO_ERR;
    }

    public static void printAddMsg(String task, Integer num) {
        String msg = Messages.MESSAGE_ADD + LS + TAB + TAB + TAB + task + LS +
                String.format(Messages.MESSAGE_TASKS_LEFT, num);
        prettify(msg);
    }

    public static void printNoSuchTaskMsg() {
        prettify(Messages.MESSAGE_NO_TASKS_FOUND);
    }

    public static void printRemoveMsg(String task, Integer num) {
        String msg = Messages.MESSAGE_REMOVE + LS + TAB + TAB + TAB + task + LS +
                String.format(Messages.MESSAGE_TASKS_LEFT, num);
        prettify(msg);
    }

    public static void printTaskDone(String task) {
        String msg = TAB + Messages.MESSAGE_DONE + LS + TAB + TAB + task;
        prettify(msg);
    }
}
