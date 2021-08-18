import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.print("Command: ");
            String command = sc.next();
            if (command.equals("bye")) {
                System.out.println(dukeSays("¡Adiós! See you soon!"));
                running = false;
            } else {
                System.out.println(dukeSays(command));
            }
        }
    }

    private static String dukeSays(String str) {
        return "Duke says: " + str;
    }
}

