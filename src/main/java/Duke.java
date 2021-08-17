import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello! I'm \n" + logo + "\nHow can I help?");
        Scanner scanner = new Scanner(System.in);

        String input = "";

        while (!input.equals("bye")) {
            input = scanner.nextLine();
            printOutput(input);
        }

        printOutput("Bye. Hope to see you again soon!");
    }

    public static void printOutput(String input) {
        String line = "---------------------";
        System.out.println(line + "\n  " + input + "\n" + line + "\n");
    }
}

