package duke;

/**
 * Represents a helper class that returns message strings.
 */
public class Response {

    public static String dukeLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        return "Hello from\n" + logo;
    }

    public static String newUserWelcomeMessage() {
        return "Hello! I'm Duke";
    }

    public static String existingUserWelcomeMessage() {
        return "Hello! Welcome back!";
    }

    public static String leftoverTaskMessage(TaskList taskList) {
        return "These are the task(s) you have left off: \n" + taskList;
    }

    public static String inputRequestMessage() {
        return "What can I do for you?";
    }

    public static String markTaskDoneMessage() {
        return "Nice! I've marked this task as done.";
    }

    public static String addedTaskMessage() {
        return "Got it. I've added this task.";
    }

    public static String removedTaskMessage() {
        return "Noted. I've removed this task.";
    }

    public static String TaskListClearedMessage() {
        return "Noted. I have cleared the task list.";
    }

    public static String byeMessage() {
        return "Bye. Hope to see you again soon!";
    }

    public static String invalidCommandMessage() {
        return "I'm sorry, but I don't know what that means... :(";
    }

}
