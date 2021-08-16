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
            if (userInput.equals("list")) {
                printResponse(todoList.list());
            } else if (userInput.matches("done [0-9]+")) {
                int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
                printResponse(todoList.markAsDone(taskNumber));
            } else {
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
