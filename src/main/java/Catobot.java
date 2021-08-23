import exception.BotException;
import item.TaskList;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Catobot {
    public static void main(String[] args) {
        // creates an object of Scanner
        Scanner sc = new Scanner(System.in);

        TaskList taskGroup = new TaskList();
        TaskListRequestHandler handler = new TaskListRequestHandler(taskGroup);
        try {
            Storage storage = new Storage("./data/Catobot.txt");
            storage.read(handler);

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

        storage.write(handler.backUpTask());

        } catch (IOException e) {
            handler.respond(e.getMessage());
        }

        handler.exit();
        // closes the scanner
        sc.close();
    }

}
