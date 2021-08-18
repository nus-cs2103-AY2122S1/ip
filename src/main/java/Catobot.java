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

    public static void main(String[] args) throws BotException {
        // creates an object of Scanner
        Scanner sc = new Scanner(System.in);
        respond(greeting);
        TaskList taskGroup = new TaskList();
        // takes input from the keyboard
        String request = sc.nextLine();
        // String command = request.split(" ")[0];
            while (!request.matches("^bye")) {
                try {
                    // prints the response
                    System.out.println(request);
                    if (request.matches("^list")) {
                        respond(taskGroup.display());

                    } else if (request.matches("^done.*")) {
                        try {
                            int index = Integer.parseInt(request.substring("done".length()).trim());
                            respond(taskGroup.completeTask(index));
                        } catch(NumberFormatException e) {
                            throw new InvalidCommandException();
                        }
                    } else if (request.matches("^todo.*")) {
                        String description = request.substring("todo".length()).trim(); // one empty space to separate command
                        respond(taskGroup.add(Todo.of(description)));

                    } else if (request.matches("^deadline.*")) {
                        if (!request.contains("/by")) {
                            throw new InvalidCommandException();
                        }
                        String[] inputs = request.split("deadline")[1].trim().split(" /by ");
                        if (inputs.length == 1) {
                            throw new EmptyCommandException("deadline");
                        }
                        String description = inputs[0].trim();
                        String date = inputs[1];
                        respond(taskGroup.add(Deadline.of(description, date)));

                    } else if (request.matches("^event.*")) {
                        if (!request.contains("/at")) {
                            throw new InvalidCommandException();
                        }
                        String[] inputs = request.split("event")[1].trim().split(" /at ");
                        if (inputs.length == 1) {
                            throw new EmptyCommandException("event");
                        }
                        String description = inputs[0].trim();
                        String date = inputs[1];
                        respond(taskGroup.add(Event.of(description, date)));

                    } else {
                        throw new InvalidCommandException();
                    }
                } catch (BotException e) {
                    respond(e.getMessage());
                } finally {
                    request = sc.nextLine();
                    // command = request.split(" ")[0];
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

}
