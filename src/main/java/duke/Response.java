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

    public static String helloMessage() {
        return "Hello.";
    }

    public static String newUserWelcomeMessage() {
        return "Hello! I'm Butler Pooh.";
    }

    public static String existingUserWelcomeMessage() {
        return "Welcome back!";
    }

    public static String leftoverTaskMessage(TaskList taskList) {
        return "Let me see what you have left off: \n" + taskList;
    }

    public static String inputRequestMessage() {
        return "What can I do for you?\n(Enter 'help' to see available commands)";
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

    public static String helpMessage() {
        StringBuilder resp = new StringBuilder();
        resp.append("Here are a list of commands you can use: \n");
        resp.append("todo: add a todo task\n");
        resp.append("event: add an event task\n");
        resp.append("deadline: add a deadline task\n");
        resp.append("list: list all the tasks\n");
        resp.append("done: mark a task as done\n");
        resp.append("delete: remove a task\n");
        resp.append("find: find tasks containing keyword\n");
        resp.append("clear: remove all tasks\n");
        resp.append("undo: undo last action\n");
        resp.append("bye: quit this program\n");
        return resp.toString();
    }

    public static String byeMessage() {
        return "Bye. Hope to see you again soon!";
    }

    public static String invalidCommandMessage() {
        return "Pardon me?\n(Enter 'help' to see available commands)";
    }

}
