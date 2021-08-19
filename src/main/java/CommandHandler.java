package main.java;
import java.util.Scanner;

public class CommandHandler {

    protected Scanner scanner;

    protected String input;

    protected TaskList list;

    protected int index;

    CommandHandler(Scanner scanner, String input, TaskList list, int index) {
        this.scanner = scanner;
        this.input = input;
        this.list = list;
        this.index = index;
    }

    public void handle() throws DukeException {
        // TODO Change to enums
        // TODO Error Handling
        if (this.input.equals("bye")) {

            // If the user types "bye", handle command
            ByeCommand command = new ByeCommand(this.input);
            System.out.println(command.reply());

            // End bot
            this.closeScanner();

        } else if (this.input.equals("list")) {

            // If the user types "list", show the list of tasks
            ListCommand command = new ListCommand(this.input, this.list, this.index);
            System.out.println(command.reply());

        } else if (this.input.startsWith("done ")) {

            // If the user types "done X" where X is a non-zero integer, mark the task as complete
            DoneCommand command = new DoneCommand(this.input, this.list);
            System.out.println(command.reply());

        } else if (this.input.startsWith("todo ") && input.length() > 5) {

            // If the user types "todo [XXX]" where [XXX] is a substring, store substring as a Todo object
            TodoCommand command = new TodoCommand(this.input, this.list);
            System.out.println(command.reply());

        } else if (this.input.startsWith("deadline ") && input.contains("/by ")) {

            // If the user types "deadline [XXX]" where [XXX] is a substring, store substring as a Deadline object
            DeadlineCommand command = new DeadlineCommand(this.input, this.list);
            System.out.println(command.reply());

        } else if (this.input.startsWith("event ") && input.contains("/at ")) {

            // If the user types "event [XXX]" where [XXX] is a substring, store substring as an Event object
            EventCommand command = new EventCommand(this.input, this.list);
            System.out.println(command.reply());

        } else {
            throw new DukeException("Invalid command.");
        }
    }

    private void closeScanner() {
        scanner.close();
    }
}
