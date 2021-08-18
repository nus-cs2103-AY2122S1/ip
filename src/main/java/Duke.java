import java.util.Scanner;
// current assumption is that all input is valid

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MyList toDoList = new MyList();

        String welcomeMessage = "Hello I'm Duke!\nWhat can I do for you?";
        printMessage(welcomeMessage);

        boolean run = true;
        while (run) {
            String command = sc.nextLine();
            String[] commandTokens = command.split(" ");
            // parse command
            switch (commandTokens[0]) {
                case "bye":
                    run = false;
                    break;
                case "done":
                    printMessage(toDoList.markAsCompleted(command.substring(5).trim()));
                    break;
                case "list":
                    printMessage(toDoList.getAllItems());
                    break;
                default:
                    printMessage(toDoList.addItem(command));
            }
        }
        printMessage("Goodbye for now!");
    }

    private static void printMessage(String message) {
        System.out.println("-------------------------");
        System.out.println(message);
        System.out.println("-------------------------");
    }
}
