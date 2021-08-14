import java.util.Scanner;

public class Duke {

    public static void runDuke() {
        boolean validInput = false;
        Scanner input = new Scanner(System.in);

        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        while (!validInput) {
            String userInput = input.nextLine();

            if (!userInput.equals("bye")) {
                System.out.println(userInput);
            } else {
                System.out.println("Bye. Hope to see you again soon!");
                validInput = true;
            }
        }
    }

    public static void main(String[] args) {
        runDuke();
    }
}
