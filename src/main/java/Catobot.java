import exception.BotException;
import exception.EmptyCommandException;
import exception.InvalidCommandException;
import item.*;

import java.util.Scanner;
public class Catobot {
    private static final String name = "Catobot";
    private static final String banner = "(=^^=)(=^^=)(=^^=)(=^^=)";
    private static final String greeting = "Hello I am " + name + " (>^^<)\n    What can I do for you meow?";
    private static final String byeMessage = "Bye meow! I will always wait here meow(>^^<)";

    public static void main(String[] args) {
        // creates an object of Scanner
        Scanner sc = new Scanner(System.in);
        respond(greeting);
        TaskList taskGroup = new TaskList();
        // takes input from the keyboard
        String request = sc.nextLine();
        RequestType command = RequestType.find(request.split(" ")[0]);
            while (command != RequestType.CLOSE) {
                try {
                    decideResponse(taskGroup, command, request);
                } catch (BotException e) {
                    respond(e.getMessage());
                } finally {
                    request = sc.nextLine();
                    command = RequestType.find(request.split(" ")[0]);
                }
            }
            respond(byeMessage);
            // closes the scanner
            sc.close();

    }

    private static void respond(String message) {
        String s = String.format("    %s\n    %s\n    %s", banner, message, banner);
        System.out.println(s);
    }

    private static void decideResponse(TaskList taskGroup, RequestType command, String request) throws BotException {
        String[] inputs;
        String description;
        String date;
        switch (command) {
            case LIST:
                respond(taskGroup.display());
                break;
            case DONE:
                try {
                    int index = Integer.parseInt(request.substring("done".length()).trim());
                    respond(taskGroup.completeTask(index));
                } catch(NumberFormatException e) {
                    throw new InvalidCommandException();
                }
                break;
            case DELETE:
                try {
                    int index = Integer.parseInt(request.substring("delete".length()).trim());
                    respond(taskGroup.deleteTask(index));
                } catch(NumberFormatException e) {
                    throw new InvalidCommandException();
                }
                break;
            case TODO:
                description = request.substring("todo".length()).trim();
                respond(taskGroup.add(Todo.of(description)));
                break;
            case DEADLINE:
                if (!request.contains("/by") || request.split("/by").length < 2) {
                    throw new InvalidCommandException("Don't cheat me, give me a due time so I can watch you >.<");
                }
                inputs = request.split("deadline")[1].trim().split(" /by ");
                if (inputs.length == 1) {
                    throw new EmptyCommandException("deadline");
                }
                description = inputs[0].trim();
                date = inputs[1];
                respond(taskGroup.add(Deadline.of(description, date)));
                break;
            case EVENT:
                if (!request.contains("/at") || request.split("/at").length < 2) {
                    throw new InvalidCommandException("Oh no, I am not sure when this is happening >.<");
                }
                inputs = request.split("event")[1].trim().split(" /at ");
                if (inputs.length == 1) {
                    throw new EmptyCommandException("event");
                }
                description = inputs[0].trim();
                date = inputs[1];
                respond(taskGroup.add(Event.of(description, date)));
                break;
            default:
                throw new InvalidCommandException();
        }

    }

}
