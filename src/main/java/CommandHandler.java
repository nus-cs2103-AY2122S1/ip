package main.java;
import java.util.Scanner;

public class CommandHandler {

    protected Scanner scanner;

    protected String input;

    protected Task[] list;

    protected int index = 0;

    CommandHandler(Scanner scanner, Task[] list) {
        this.scanner = scanner;
        this.list = list;
    }

    public void handle(String input) throws DukeException {
        // TODO Change to enums?
        if (input.equals("list")) {

            // If the user types "list", show the list of tasks
            ListCommand command = new ListCommand(input, index, this.list);
            System.out.println(command.reply());

        } else if (input.startsWith("done ")) {

            // If the user types "done X" where X is a non-zero integer, mark the task as complete
            DoneCommand command = new DoneCommand(input, this.list);
            System.out.println(command.reply());

        } else if (input.startsWith("todo ")) {

            // If the user types "todo [XXX]" where [XXX] is a substring, store substring as a Todo object
            TodoCommand command = new TodoCommand(input, index, this.list);
            System.out.println(command.reply());
            index++;

        } else if (input.startsWith("deadline ") && input.contains("/by ")) {

            // If the user types "deadline [XXX]" where [XXX] is a substring, store substring as a Deadline object
            DeadlineCommand command = new DeadlineCommand(input, index, this.list);
            System.out.println(command.reply());
            index++;

        } else if (input.startsWith("event ") && input.contains("/at ")) {

            // If the user types "event [XXX]" where [XXX] is a substring, store substring as an Event object
            EventCommand command = new EventCommand(input, index, this.list);
            System.out.println(command.reply());
            index++;

        } else if (!input.equals("bye")){
            throw new DukeException("You've entered an unknown request... The bot doesn't know what that means!");
        }
    }

    private void closeScanner() {
        scanner.close();
    }
}