import java.util.Scanner;

public class Duke {
    private final String welcomeMessage = "Hello! I'm Duke\n" + "What can I do for you?";
    private final String borderLine = "____________________________________________________________";
    private final String goodbyeMessage = "Bye. Hope to see you again soon!";

    public static void main(String[] args) {
        new Duke().runProgram();
    }

    public void runProgram() {
        System.out.println(borderLine + welcomeMessage + borderLine);
    }
}
