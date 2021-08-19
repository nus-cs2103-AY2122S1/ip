import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        DukeCommandManager commandManager = new DukeCommandManager();

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

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
