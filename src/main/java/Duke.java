import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        TodoList todoList = new TodoList();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Look at me! I'm\n" + logo + "\nHow can I help?");

        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        while(!userInput.equals("bye")) {
            switch (userInput) {
                case "list":
                    printResponse(todoList.list());
                    break;
                default:
                    printResponse(todoList.add(userInput));
            }

            System.out.print("Whats next? ");
            userInput = scanner.nextLine();
        }
        printResponse("Ooooh yeah! Can do!");
    }

    private static void printResponse(String message) {
        System.out.println("============Duke says============");
        System.out.println(message);
        System.out.println("=================================");
    }
}
