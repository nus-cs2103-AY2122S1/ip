import java.util.Scanner;

public class Duke {
    private static final TaskList taskList = new TaskList();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String command;
        String input;
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "What can I do for you?\n");

        while(true) {
            System.out.print("You: ");
            command = sc.next();
            input = sc.nextLine().trim();
            System.out.print("Duke: ");
            switch (command) {
                case "bye":
                    System.out.println("Nice talking to you, goodbye!");
                    return;
                case "list":
                    System.out.println("Here's your todo list!");
                    taskList.printList();
                    break;
                case "done":
                    taskList.doTask(Integer.parseInt(input) - 1);
                    taskList.printList();
                    break;
                default:
                    try {
                        taskList.add(Task.createTask(command, input));
                    } catch (NoSuchCommandException e) {
                        System.out.println(e.getMessage());
                    }
            }
        }
    }
}
