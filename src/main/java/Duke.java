import java.util.Scanner;

public class Duke {
    public static void echo(String s) {
        System.out.println("Duke: " + s);
    }

    public static String getResponse() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    public static void start() {
        System.out.println("Duke: What can I help you with?");

        String response = "";

        while (true) {
            response = Duke.getResponse();

            if (response.equals("bye")) {
                break;
            }

            Duke.echo(response);
        }
    }

    public static void exit() {
        System.out.println("Duke: Good bye");
        System.out.println("Shutting down Duke...");
    }

    public static void main(String[] args) {
        Duke.greet();
        Duke.start();
        Duke.exit();
    }
}
