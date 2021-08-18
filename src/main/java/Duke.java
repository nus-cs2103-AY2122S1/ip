import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MyList toDoList = new MyList();

        String welcomeMessage = "Hello I'm Duke!\nWhat can I do for you?";
        printMessage(welcomeMessage);

        boolean run = true;
        while (run) {
            String command = sc.nextLine();
            // parse command
            if (command.equals("bye")) {
                run = false;
            } else {
                switch (command) {
                case "list":
                    printMessage(toDoList.getAllItems());
                    break;
                default:
                    printMessage(toDoList.addItem(command));
                }
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
