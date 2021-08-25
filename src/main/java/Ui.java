import task.Task;
import task.TaskList;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Ui {
    public static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    public static final String MESSAGE_GREETING = "Hello from\n" + LOGO;
    public static final String MESSAGE_HELP = "How can I help you?";
    public static final String MESSAGE_ADD_TASK = "Added the following task:\n"
            + "  %1$s\n"
            + "You have %2$d task(s) in the list";
    public static final String MESSAGE_DELETE_TASK = "Deleted the following task:\n"
            + "  %1$s\n"
            + "You have %2$d task(s) in the list";
    public static final String MESSAGE_LIST_TASK = "Your current task(s):\n"
            + "%1$s";
    public static final String MESSAGE_MARK_TASK = "Marked task %1$d as done";
    public static final String MESSAGE_RETRIEVE_DATA = "Data retrieved!";
    public static final String MESSAGE_GOODBYE = "Good bye!";

    private Scanner in;
    private PrintStream out;

    public Ui() {
        this.in = new Scanner(System.in);
        this.out = System.out;
    }

    public String getUserCommand() {
        return in.nextLine();
    }

    public void sayGreet() {
        this.say(MESSAGE_GREETING);
    }

    public void sayHelp() {
        this.say(MESSAGE_HELP);
    }

    public void sayAddTask(Task t, int numTask) {
        this.say(String.format(MESSAGE_ADD_TASK, t.toString(), numTask));
    }

    public void sayDeleteTask(Task t, int numTask) {
        this.say(String.format(MESSAGE_DELETE_TASK, t.toString(), numTask));
    }

    public void sayMarkDoneTask(int taskNum) {
        this.say(String.format(MESSAGE_MARK_TASK, taskNum));
    }

    public void sayList(TaskList tl) {
        this.say(String.format(MESSAGE_LIST_TASK, tl.toString()));
    }

    public void sayRetrieveData() {
        this.say(MESSAGE_RETRIEVE_DATA);
    }

    public void sayGoodBye() {
        this.say(MESSAGE_GOODBYE);
    }

    public void say(String... s) {
        this.out.print("Duke: ");

        for (String line : s) {
            this.out.println(line);
        }
    }
}
