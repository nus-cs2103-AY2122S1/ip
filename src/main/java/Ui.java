public class Ui {
    private static final String LINE = "    ____________________________________________________________";
    private static final String INDENTATION = "     ";

    private static final String GREETING = "Hello from\n"
            + " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public void greet(){
        System.out.println(GREETING);
        printWithFormat("Hello! I'm Duke\n" + INDENTATION + "What can I do for you?");
    }

    public void taskAddedMessage(Task task, int taskLeft){
        String msg = "Got it. I've added this task:\n" + INDENTATION + task.getStatus();
        msg += "\n" + INDENTATION + "Now you have " + taskLeft + " tasks in the list.";
        printWithFormat(msg);
    }

    public void taskDeletedMessage(Task task, int taskLeft){
        String msg = "Noted. I've removed this task:\n" + INDENTATION + task.getStatus();
        msg += "\n" + INDENTATION + "Now you have " + taskLeft + " tasks in the list.";
        printWithFormat(msg);
    }

    public void taskDoneMessage(Task task){
        String msg = "Nice! I've marked this task as done:\n" + INDENTATION + task.getStatus();
        printWithFormat(msg);
    }

    public void listTaskMessage(TaskList taskList){
        String msg = "Here are the tasks in your list:\n";
        for (int i = 1; i <= taskList.size(); i++) {
            msg += INDENTATION + + i + "." + taskList.getTask(i).getStatus() + "\n";
        }
        printWithFormat(msg);
    }

    public void bye(){
        printWithFormat("Bye. Hope to see you again soon!");
    }

    public static void printWithFormat(String message) {
        System.out.println(LINE);
        System.out.println(INDENTATION + message);
        System.out.println(LINE);
    }

    public static String formatErrorMessage(String message) {
        String errorMessage = LINE + "\n";
        errorMessage += INDENTATION + "☹ OOPS!!! " + message + "\n";
        errorMessage += LINE;
        return errorMessage;
    }
}
