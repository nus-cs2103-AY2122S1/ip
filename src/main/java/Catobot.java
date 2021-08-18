import exception.BotException;
import item.*;
import java.util.Scanner;

public class Catobot {
    public static void main(String[] args) {
        // creates an object of Scanner
        Scanner sc = new Scanner(System.in);
        TaskList taskGroup = new TaskList();
        TaskListRequestHandler handler = new TaskListRequestHandler(taskGroup);

        // takes input from the keyboard
        String request = sc.nextLine();
        Command command = Command.find(request);

        while (command != Command.CLOSE) {
            try {
                handler.decideResponse(command, request);
            } catch (BotException e) {
                handler.respond(e.getMessage());
            } finally {
                request = sc.nextLine();
                command = Command.find(request);
            }
        }

        handler.exit();
        // closes the scanner
        sc.close();
    }

}
