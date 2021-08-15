import java.util.Scanner;

public class Duke {
    public static final Task[] tasks = new Task[100];
    public static int count = 0;

    private Command parseCommand(String commandString) {
        String[] tokens = commandString.split(" ");
        switch (tokens[0]) {
            case "list":
                return new ListCommand();
            case "done":
                return new MarkDoneCommand(Duke.tasks[Integer.parseInt(tokens[1]) - 1]);
            default:
                return new AddCommand(new Task(commandString, false));
        }
    }

    public void run() {
       Message.print(new String[] {"Yo, I'm Xiri.", "How can I help you?"});

       Scanner scanner = new Scanner(System.in);
       String commandString = scanner.nextLine();
       while (!commandString.equals("bye")) {
           parseCommand(commandString).run();
           commandString = scanner.nextLine();
       }
       Message.print("Bye, see you later.");
    }

    public static void main(String[] args) {
       Duke xiri = new Duke();
       xiri.run();
    }
}
