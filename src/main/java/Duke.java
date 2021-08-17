import java.util.Scanner;

public class Duke {
    private static void start() {
        String greetings = "Hello! What can I do for you?";
        System.out.println(greetings);

        Scanner sc = new Scanner(System.in);
        String cmd = "";
        while (!cmd.equals("bye")) {
            System.out.println(cmd + "\n");
            cmd = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        start();
    }
}
