import java.util.Scanner;

public class Duke {

    //This function prints the horizontal line
    public static void printHorizontalLine() {
        System.out.println("          ___________________________________________________________________________");
    }

    //This function prints the welcome message
    public static void printWelcomeMessage() {
        printHorizontalLine();
        System.out.println("            Hello! I'm Duke");
        System.out.println("            What can I do for you?");
        printHorizontalLine();
    }

    //This function prints the bye message
    public static void printByeMessage() {
        printHorizontalLine();
        System.out.println("            Bye. Hope to see you again soon!");
        printHorizontalLine();
    }

    public static void main(String[] args) {
        printWelcomeMessage();

        Scanner in = new Scanner(System.in);

        String str;

        str = in.nextLine();

        while (!str.equals("bye")) {
            printHorizontalLine();
            System.out.println("            " + str);
            printHorizontalLine();
            str = in.nextLine();
        }

        printByeMessage();
    }
}
