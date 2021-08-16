import java.util.Scanner;

public class Blue {
    private static final String LOGO = " ____  _                \n"
            + "|  . \\| | _   _   ____  \n"
            + "|____/| || | | | /  _  \\\n"
            + "|  . \\| || |_| ||   ___/\n"
            + "|____/|_||_____| \\_____/\n";
    private static final String GREET_CONTENT = "Hello! I'm Blue\n"
            + "What can I do for you?";
    private static final String EXIT_CONTENT = "Bye. Hope to never see you again!";

    public static void main(String[] args) {
        System.out.println(LOGO);
        greet();
        String command = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            command = scanner.nextLine();
            if (command.equals(Command.EXIT)) {
                speak(EXIT_CONTENT);
                break;
            } else {
                echo(command);
            }
        }
        scanner.close();
    }

    private static void greet() {
        speak(GREET_CONTENT);
    }

    private static void echo(String content) {
        speak(content);
    }

    private static void speak(String content) {
        String line = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
        System.out.println(line);
        System.out.println(content);
        System.out.println(line);
    }
}
