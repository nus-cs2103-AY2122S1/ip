import java.util.Scanner;

public class Duke {

    private static boolean isRunning = false;

    public static void main(String[] args) {
        Duke.isRunning = true;
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greeting();
        Scanner input = new Scanner(System.in);
        while (isRunning) {
            String command = input.nextLine();
            if (command.equals("bye")) {
                exit();
            } else {
                echo(command);
            }
        }

    }

    private static void greeting() {
        System.out.println("------------------");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("------------------\n");
    }

    private static void echo(String statement) {
        System.out.println("------------------");
        System.out.println(statement + "\n");
        System.out.println("------------------\n");
    }

    private static void exit() {
        Duke.isRunning = false;
        System.out.println("------------------");
        System.out.println("Bye. Hope to see you soon!\n");
        System.out.println("------------------\n");
    }
}