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
            handleInputs(todoList, userInput);
            System.out.print("Whats next? ");
            userInput = scanner.nextLine();
        }
        PrintResponse.print("Ooooh yeah! Can do!");
    }

    private static void handleInputs(TodoList todoList, String userInput) {
        if (userInput.equals("list")) {
            todoList.list();
        } else if (userInput.matches("done [0-9]+")) {
            int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
            todoList.markAsDone(taskNumber);
        } else if (userInput.matches("^deadline .+/.+")) {
            int firstSpace = userInput.indexOf(' ');
            String[] deadlineDetails = userInput.substring(firstSpace + 1).split(" /by ");
            String name = deadlineDetails[0];
            String dateTime = deadlineDetails[1];
            todoList.addDeadline(name, dateTime);
        } else if (userInput.matches("^event .+/.+")) {
            int firstSpace = userInput.indexOf(' ');
            String[] deadlineDetails = userInput.substring(firstSpace + 1).split(" /at ");
            String name = deadlineDetails[0];
            String dateTime = deadlineDetails[1];
            todoList.addEvent(name, dateTime);
        } else if (userInput.matches("^todo .+")) {
            int firstSpace = userInput.indexOf(' ');
            String name = userInput.substring(firstSpace + 1);
            todoList.addTodo(name);
        } else {
            PrintResponse.print("IDGI...");
        }
    }


}
