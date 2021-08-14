import java.util.Scanner;

public class Duke {

    public static void runDuke() {
        boolean validInput = false;
        Scanner input = new Scanner(System.in);
        List tasks = new List(100);

        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        while (!validInput) {
            String userInput = input.nextLine();

            switch (userInput) {
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    validInput = true;
                    break;
                case "list":
                    System.out.println(tasks.getList());
                    break;
                default:
                    tasks.addToList(userInput);
                    System.out.println("added: " + userInput);
                    break;
            }
        }
    }

    public static void main(String[] args) {
        runDuke();
    }
}
