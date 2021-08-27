package Duke;

import java.util.Scanner;

/**
 * This class encapsulates the understanding of commands.
 * It calls the relevant command depending on the user's input.
 */
public class Parser {

    private Scanner sc = new Scanner(System.in);

    /**
     * Gets the user's input and return the relevant command to execute.
     * @return A command which determines how the program should react.
     */
    public ICommand getInput() {
        String input = sc.nextLine();

        // Exit
        if (input.equals("bye")) {
            return new ByeCommand();

        // List out tasks
        } else if (input.equals("list")) {
            return new ListTasksCommand();

        } else if (input.split(" ")[0].equals("done")) {
            return new DoneCommand(input);

        // Delete tasks
        } else if (input.split(" ")[0].equals("delete")) {
            return new DeleteCommand(input);

        // Add to-do task
        } else if (input.split(" ")[0].equals("todo")){
            return new AddToDoCommand(input);

        // Add deadline task
        } else if (input.split(" ")[0].equals("deadline")){
            return new AddDeadlineCommand(input);

        // Add event task
        } else if (input.split(" ")[0].equals("event")){
            return new AddEventCommand(input);

        // Unknown command
        } else {
            return new UnknownCommand();
        }
    }
}
