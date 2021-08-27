package duke.ui;
public class Ui {
    private static final String INDENTATION = "    ";
    private static final String WELCOME_MESSAGE = "Hello! I'm Jacky\nWhat can I do for you?";
    private static final String BYE_MESSAGE = "Bye. Hope to see you again soon!";
    private static final String UNKNOWN_INPUT_MESSAGE = "OOPS!!! I'm sorry, but I don't know what that means :-(";
    private static final String MISSING_DATE = "OOPS!!! Please add a valid date in the format dd/MM/yy HHmm.";
    private static final String PRINT_LIST_MESSAGE = "Here are the tasks in your list:";
    private static final String NUMBER_OF_TASKS_MESSAGE = "Now you have %d tasks in the list.\n";
    private static final String MARK_TASK_AS_DONE_MESSAGE = "Nice! I've marked this task as done: ";
    private static final String ADD_TASK = "Got it. I've added this task:\n" + INDENTATION + INDENTATION;
    private static final String DELETE_TASK = "Noted. I've removed this task: ";
    private static final String EMPTY_LIST = "You haven't added anything to your list!";

    public static void greet() {
        System.out.println(WELCOME_MESSAGE);
    }

    public static void bye() {
        System.out.println(INDENTATION + BYE_MESSAGE);
    }

    public static String emptyDescription(String s) {
        String error = "OOPS!!!";
        switch(s) {
            case "todo":
                error = INDENTATION + "OOPS!!! The description of a todo cannot be empty.";
                break;
            case "deadline":
                error = INDENTATION + "OOPS!!! The description of a deadline cannot be empty.";
                break;
            case "event":
                error = INDENTATION + "OOPS!!! The description of an event cannot be empty.";
                break;
        }
        return error;
    }

    public static String inputUnknown() {
       return INDENTATION + UNKNOWN_INPUT_MESSAGE;
    }

    public static String dateMissing() {
        return INDENTATION + MISSING_DATE;
    }

    public static void printList() {
        System.out.println(INDENTATION + PRINT_LIST_MESSAGE);
    }

    public static void printNoOfTasks(int i) {
        System.out.printf(INDENTATION + NUMBER_OF_TASKS_MESSAGE, i);
    }

    public static void markAsDone() {
        System.out.println(INDENTATION + MARK_TASK_AS_DONE_MESSAGE);
    }

    public static void addTask() {
        System.out.println(INDENTATION + ADD_TASK);
    }

    public static void deleteTask() {
        System.out.println(INDENTATION + DELETE_TASK);
    }

    public static void printEmptyList() {
        System.out.println(INDENTATION + EMPTY_LIST);
    }

    public static String indentation() {
        return INDENTATION;
    }
}
