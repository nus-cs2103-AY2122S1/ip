import java.util.Scanner;

public class Duke {
    private TaskStorage taskStorage = TaskStorage.getInstance();

    public void run() {
       Message.print(new String[] {"Yo, I'm Xiri.", "How can I help you?"});

       Scanner scanner = new Scanner(System.in);
       String commandString = scanner.nextLine();
       while (!commandString.equals("bye")) {
           try {
               CommandParser.parse(commandString).run();
           } catch (DukeException e) {
               Message.print(e.getMessage());
           }
           commandString = scanner.nextLine();
       }
       Message.print("Bye, see you later.");
    }

    public static void main(String[] args) {
       Duke xiri = new Duke();
       xiri.run();
    }
}
