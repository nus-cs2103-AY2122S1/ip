import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("\t Hello! I'm Duke");
        getCommand();
    }

    private static void getCommand() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\t What would you like me to do?\n");
        String command = scanner.nextLine();

        // Checks if user command is bye
        if (command.toLowerCase().equals("bye")) {
            System.out.println("\t Bye. Hope to see you again soon");
        } else {
            System.out.println("\t " + command);
            getCommand();
        }
    }
}
