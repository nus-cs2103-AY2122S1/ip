package duke;

public class Ui {
    private static String startMessage = "____________________________________________________________\n" +
            "Hello! I'm Duke. \nFor " +
            "events and deadlines, be sure to state the due date or the event timing as such: " +
            "'XXX /by dd-MM-yyyy HH:mm'\n" +
            "____________________________________________________________";
    private static String endMessage = "____________________________________________________________\n" +
            "Bye! Hope to see you again soon!\n" +
            "____________________________________________________________";

    private static String taskDoesNotExistError = "____________________________________________________________\n" +
            "☹ OOPS!!! The task you chose does not exist. Use the 'list' command to check the items in your list.\n" +
            "____________________________________________________________";

    public static void customErrorMessage(String message) {
        System.out.println(message);
    }

    public static void sendStartMessage() {
        System.out.println(Ui.startMessage);
    }

    public static void sendEndMessage() {
        System.out.println(Ui.endMessage);
    }

    public static void deletedTaskMessage(TaskItem task, int taskListSize) {
        System.out.println("____________________________________________________________");
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.toString());
        if (taskListSize == 1) System.out.println("Now you have 1 task in the list.");
        if (taskListSize > 1) System.out.println("Now you have " + taskListSize + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    public static void completedTaskMessage(TaskItem task) {
        System.out.println("____________________________________________________________");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.toString());
        System.out.println("____________________________________________________________");
    }

    public static void dateTimeError() {
        System.out.println("Please follow the specified format for entering the date and time of the deadline.");
    }
}
