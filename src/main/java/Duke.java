import java.util.Locale;
import java.util.Scanner;

public class Duke {
    public static final Task[] tasks = new Task[100];
    public static int count = 0;

    private Command parseCommand(String commandString) {
        String[] tokens = commandString.split(" ");
        String payload = tokens.length < 2 ? "" : commandString.replace(tokens[0], "").trim();

        switch (tokens[0]) {
            case "list":
                return new ListCommand();
            case "done":
                return new MarkDoneCommand(Duke.tasks[Integer.parseInt(tokens[1]) - 1]);
            case "todo":
                return new AddCommand(new TodoTask(payload, false));
            case "deadline":
                String deadlineContent = payload.substring(0, payload.lastIndexOf("/by")).trim();
                String deadline = payload.substring(payload.lastIndexOf("/by") + 3).trim();
                return new AddCommand(new DeadlineTask(deadlineContent, false, deadline));
            case "event":
                String eventContent = payload.substring(0, payload.lastIndexOf("/at")).trim();
                String date = payload.substring(payload.lastIndexOf("/at") + 3).trim();
                return new AddCommand(new EventTask(eventContent, false, date));
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
