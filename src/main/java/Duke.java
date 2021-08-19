import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        DukeCommandManager commandManager = new DukeCommandManager();

        commandManager.gettingStart();
        Scanner sc = new Scanner(System.in).useDelimiter("\n");
        String command = sc.next().trim();

        while (!command.equals("bye")) {
            String commandType = command.split(" ", 2)[0];
            commandManager.processCommand(command, commandType);
            command = sc.next().trim();
        }

        sc.close();
        commandManager.respondBye();
    }
}
