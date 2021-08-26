package duke;
import java.util.Scanner;

public class UI {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_RED = "\u001B[31m";
    private final Scanner input;


    public UI() {
        input = new Scanner(System.in);
    }

    public void display(String output) {
        System.out.println(ANSI_CYAN + "     " + output + ANSI_RESET);
    }

    public void printTaskList(TaskList taskList) {
        if ((taskList != null) && (taskList.getTaskListLength() > 0)) {
            display("Here are the tasks in your list:");
            for (int i = 0; i < taskList.getTaskListLength(); i++) {
                display((i + 1) + "." + taskList.getTask(i));
            }
        } else {
            display("The Task List is Empty");
        }
    }

    public void showWelcome() {
        System.out.println("\n");
        String buffer = "     ";
        String logo =   buffer + " ____        _\n"
                + buffer + "|  _ \\ _   _| | _____\n"
                + buffer + "| | | | | | | |/ / _ \\\n"
                + buffer + "| |_| | |_| |   <  __/\n"
                + buffer + "|____/ \\__,_|_|\\_\\___|\n";
        display("Hello from\n" + logo);

        System.out.println("____________________________________________________________________________________" +
                "____________________________________");
        display("Hello! I'm Duke");
        display("What can I do for you?");
        System.out.println("____________________________________________________________________________________" +
                "____________________________________\n");
    }

    public void showLine() {
        System.out.println("____________________________________________________________________________________" +
                "____________________________________");
    }

    public String readCommand() {
        return input.hasNextLine() ? input.nextLine().trim() : "";
    }

    public void showError(String error) {
        System.out.println(ANSI_RED + "     " + error + "\n     Try Again \\_(\"v\")_/" + ANSI_RESET);
    }
}