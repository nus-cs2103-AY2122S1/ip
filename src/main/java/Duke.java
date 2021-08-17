import java.util.Scanner;

public class Duke {

    private static Scanner sc;
    private static String[] list;
    private static int index;

    public static void main(String[] args) {
        sc = new Scanner(System.in);
        list = new String[100];
        index = 0;

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

        } else if (input.equals("list")) {

            printSingleDivider();
            System.out.println("Output: This is your current list!\n");
            for (int i = 0; i < index; i++) {
                System.out.println(i + 1 + ". " + list[i]);
            }
            printDoubleDivider();

            getInput();

        } else {

            list[index++] = input;

            printSingleDivider();
            System.out.println("Output: You have added '" + input + "' to the list!");
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

