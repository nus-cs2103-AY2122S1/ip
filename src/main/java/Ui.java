import java.util.ArrayList;

public class Ui {
    private static final String INDENTATION = "    ";
    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private static final String INTRO = "Hello from\n"
            + INDENTATION + " ____        _        \n"
            + INDENTATION + "|  _ \\ _   _| | _____ \n"
            + INDENTATION + "| | | | | | | |/ / _ \\\n"
            + INDENTATION + "| |_| | |_| |   <  __/\n"
            + INDENTATION + "|____/ \\__,_|_|\\_\\___|\n"
            + INDENTATION + "What can I do for you?";

    public static void printMessageWithFormat(String msg){
        System.out.println(INDENTATION + HORIZONTAL_LINE);
        System.out.println(INDENTATION + msg);
        System.out.println(INDENTATION + HORIZONTAL_LINE);
    }

    protected void greet(){
        printMessageWithFormat(INTRO);
    }

    protected void farewellMsg(){
        printMessageWithFormat("Bye bye, i go sleep already. See you again.");
    }

    protected void addTaskMsg(int taskLeft, Task task){
        String msg = "Got it. I've added this task:\n" + INDENTATION + "  " + task.checkStatus();
        msg += String.format("\n%sNow you have %d tasks in the list.", INDENTATION, taskLeft);
        printMessageWithFormat(msg);
    }

    protected void deleteTaskMsg(int taskLeft, Task task){
        String msg = "Noted. I've removed this task:\n" + INDENTATION + "  " + task.checkStatus();
        msg += String.format("\n%sNow you have %d tasks in the list.", INDENTATION, taskLeft);
        printMessageWithFormat(msg);
    }

    protected void markDoneMsg(Task task){
        String msg = "Nice! I've marked this task as done:\n   ";
        msg += INDENTATION + task.checkStatus();
        printMessageWithFormat(msg);
    }

    protected void listTasks(ArrayList<Task> taskList){
        String msg = "Here are the tasks in your list:";
        for (int i = 1; i <= taskList.size(); i++){
            msg += String.format("\n%s%d. %s", INDENTATION, i, taskList.get(i-1).checkStatus());
        }
        printMessageWithFormat(msg);
    }
}
