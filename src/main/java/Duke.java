import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        printWithLines("Hello! I'm 8-Bit!\nWhat can I do for you?");
        getUserInput();
    }

    private static void printWithLines(String msg) {
        String line = "-------------------------------------------------------";
        System.out.println(line + "\n" + msg + "\n" + line);
    }

    private static void getUserInput() {
        Scanner sc = new Scanner(System.in);

        String command = sc.nextLine();
        while (!command.equals("bye")) {
            printWithLines(command);
            command = sc.nextLine();
        }

        printWithLines("Bye. Hope to see you again soon!");
        sc.close();
    }
}
