import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        CommandManager commandManager = new CommandManager();
        List<Task> taskList = new ArrayList<>();

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
            if (command.equals("list")) {
                commandManager.respondList(taskList);
            } else if (command.startsWith("done")) {
                commandManager.respondDone(command, taskList);
            } else if (command.startsWith("todo")) {
                commandManager.respondTodo(command, taskList);
            } else if (command.startsWith("deadline")) {
                commandManager.respondDeadline(command, taskList);
            } else if (command.startsWith("event")) {
                commandManager.respondEvent(command, taskList);
            } else {
                commandManager.defaultResponse();
            }
            command = sc.next().trim();
        }

        sc.close();
        commandManager.respondBye();
    }
}
