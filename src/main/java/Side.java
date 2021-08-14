import java.util.Scanner;

public class Side {
    private static final String LINEBREAK = "---------------------------------------------------------------------";
    private static final String GREETING = LINEBREAK + "" + "\nHello! I'm Side, your personal assistant. " +
            "How can I help you today?\n" + LINEBREAK;
    private static final String GOODBYE = LINEBREAK + "\nOh, you have to go? Goodbye, I'll see you soon!\n"
            + LINEBREAK;
    private static final String NOSUCHTASK = "Sorry, this task doesn't exist!";

    private static void printLogo() {
        String logo = " ___  _____  _____   _____  \n"
                + "|  _|  | |  | ___ \\  | |__\n"
                + " \\ \\   | |  | |_| |  | |  \n"
                + "|___| _|_|_ |____/   |_|__\n";
        System.out.println(logo);
    }

    private static void echo(String input) {
        System.out.println(LINEBREAK);
        System.out.println("added: " + input);
        System.out.println(LINEBREAK);
    }

    private static void printResponse(String input) {
        System.out.println(LINEBREAK);
        System.out.println(input);
        System.out.println(LINEBREAK);
    }

    public static void main(String[] args) {
        printLogo();
        System.out.println(GREETING);
        TaskList tasks = new TaskList();

        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        while (!userInput.equalsIgnoreCase("bye")) {
            if (userInput.equalsIgnoreCase("list")) {
                printResponse(tasks.toString());
            } else if (userInput.matches("done \\d+")){
                int taskNum = Character.getNumericValue(userInput.charAt(5));
                if (taskNum > tasks.length() || taskNum == 0) {
                    printResponse(NOSUCHTASK);
                } else {
                    printResponse(tasks.markTaskDone(taskNum - 1));
                }
            } else {
                tasks.addTask(userInput);
                echo(userInput);
            }
            userInput = scanner.nextLine();
        }

        System.out.println(GOODBYE);
    }
}
