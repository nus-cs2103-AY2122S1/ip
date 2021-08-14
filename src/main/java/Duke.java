import java.util.Scanner;

public class Duke {

    public static void runDuke() {
        boolean validInput = false;
        Scanner input = new Scanner(System.in);
        List tasks = new List(100);

        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        while (!validInput) {
            String userInput = input.nextLine();

            String firstWord = userInput.contains(" ") ? userInput.split(" ")[0] : userInput;
            String index = userInput.contains(" ") ? userInput.split(" ")[1] : "";

            switch (firstWord) {
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    validInput = true;
                    break;
                case "list":
                    System.out.println("Here are the tasks in your list:\n" + tasks.getList());
                    break;
                case "done":
                    System.out.println("Nice! I've marked this task as done:\n" + tasks.taskDone(Integer.parseInt(index)));
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
