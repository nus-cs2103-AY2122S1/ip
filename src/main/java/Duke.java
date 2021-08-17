import java.util.Scanner;

public class Duke {

    private static Scanner sc;

    public static void main(String[] args) {
        sc = new Scanner(System.in);

        // Show the logo
        showLogo();

        // Gets the user input
        getInput();
    }

    private static void showLogo() {
        String logo = " _______       ___      _______   __     __   _\n"
                    + "|   ____|     / ^ \\     |   _  \\  \\ \\   / /  | |\n"
                    + "|  | ___     / /_\\ \\    |  |_|  |  \\ \\ / /   | |\n"
                    + "|  ||_  |   /  ___  \\   |  __  <    \\   /    |_|\n"
                    + "|  |__| |  /  /   \\  \\  |  | \\  \\    | |      _ \n"
                    + "|_______| /__/\t   \\__\\ |--|  \\--\\   |_|     |_|\n";

        printDoubleDivider();
        System.out.println("Hello! My name is\n" + logo);
        printDoubleDivider();
        System.out.println("How may I help you?");
        printDoubleDivider();
    }

    private static void getInput() {
        System.out.print("Input: ");
        handleInput(sc.nextLine());
    }

    private static void handleInput(String input) {
        if (input.equals("bye")) {
            printSingleDivider();
            System.out.println("Output: Goodbye! See you again!");
            printDoubleDivider();
            sc.close();
            return;
        } else {
            printSingleDivider();
            System.out.println("Output: " + input);
            printDoubleDivider();
            getInput();
        }
    }

    private static void printDoubleDivider() {
        System.out.println("\n=================================================\n");
    }

    private static void printSingleDivider() {
        System.out.println("\n- - - - - - - - - - - - - - - - - - - - - - - - -\n");
    }

}

