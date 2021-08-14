import java.util.Scanner;

public class Side {
    private static final String LINEBREAK = "---------------------------------------------------------------------";
    private static final String GREETING = LINEBREAK + "" + "\nHello! I'm Side, your personal assistant. " +
            "How can I help you today?\n" + LINEBREAK;
    private static final String GOODBYE = LINEBREAK + "\nOh, you have to go? Goodbye, I'll see you soon!\n"
            + LINEBREAK;

    private static void printLogo() {
        String logo = " ___  _____  _____   _____  \n"
                + "|  _|  | |  | ___ \\  | |__\n"
                + " \\ \\   | |  | |_| |  | |  \n"
                + "|___| _|_|_ |____/   |_|__\n";
        System.out.println(logo);
    }

    private static void echo(String input) {
        System.out.println(LINEBREAK);
        System.out.println("I heard: " + input);
        System.out.println(LINEBREAK);
    }

    public static void main(String[] args) {
        printLogo();
        System.out.println(GREETING);

        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        while (!userInput.equalsIgnoreCase("bye")) {
            echo(userInput);
            userInput = scanner.nextLine();
        }

        System.out.println(GOODBYE);
    }
}
