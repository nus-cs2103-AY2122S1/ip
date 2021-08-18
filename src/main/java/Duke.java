import java.util.Scanner;

public class Duke {

    private static Scanner sc;
    private static Task[] list;
    private static int index;

    public static void main(String[] args) {
        sc = new Scanner(System.in);
        list = new Task[100];
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
        printSingleDivider();

        if (input.equals("bye")) {

            System.out.println("Output: Goodbye! See you again!");
            printDoubleDivider();
            sc.close();
            return;

        } else if (input.equals("list")) {

            System.out.println("Output: This is your current list!\n");
            for (int i = 0; i < index; i++) {
                System.out.println(i + 1 + ". " + list[i]);
            }

        } else if (input.startsWith("done")) {

            try {
                int markDone = Integer.parseInt(input.substring(5)) - 1;
                list[markDone].markAsDone();

                System.out.println("You have successfully marked this task as done:\n");
                System.out.println(list[markDone]);
            } catch (StringIndexOutOfBoundsException | NumberFormatException e1) {
                System.out.println("Please specify which task you would like to mark \n" +
                                    "as complete by adding a single number after 'done'!\n" +
                                    "i.e. done 1");
            } catch (ArrayIndexOutOfBoundsException | NullPointerException e2) {
                System.out.println("There is no task under that number!");
            }

        } else {

            list[index++] = new Task(input);
            System.out.println("Output: You have added '" + input + "' to the list!");

        }

        printDoubleDivider();
        getInput();
    }

    private static void printDoubleDivider() {
        System.out.println("\n=================================================\n");
    }

    private static void printSingleDivider() {
        System.out.println("\n- - - - - - - - - - - - - - - - - - - - - - - - -\n");
    }

}

