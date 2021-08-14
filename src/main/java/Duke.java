import java.util.Date;
import java.util.Scanner;

public class Duke {

    public static void runDuke() {
        boolean validInput = false;
        Scanner input = new Scanner(System.in);
        List taskList = new List(100);

        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        while (!validInput) {
            String userInput = input.nextLine();
            String firstWord = userInput;
            String restOfInput = " ";
            String date = " ";
            String beforeDate = " ";

            if (userInput.contains(" ")) {
                firstWord = userInput.split(" ",2)[0];
                restOfInput = userInput.split(" ",2)[1];
            }

            switch (firstWord) {
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    validInput = true;
                    break;
                case "list":
                    System.out.println("Here are the tasks in your list:\n" + taskList.getList());
                    break;
                case "done":
                    System.out.println("Nice! I've marked this task as done:\n" + taskList.taskDone(Integer.parseInt(restOfInput)));
                    break;
            }
        }
    }

    public static void main(String[] args) {
        runDuke();
    }
}
