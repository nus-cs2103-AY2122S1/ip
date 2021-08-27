package main.java;
import java.util.ArrayList;
import java.util.Scanner;

public class CommandHandler {

    protected String input;

    protected ArrayList<Task> list;

    enum CommandStart {
        LIST,
        DONE,
        DELETE,
        TODO,
        DEADLINE,
        EVENT
    }

    CommandHandler(ArrayList<Task> list) {
        this.list = list;
    }

    public void handle(String input) throws DukeException {

        CommandStart start = null;
        Command command;

        if (input.equals("list")) {

            // If the user types "list", show the list of tasks
            start = CommandStart.LIST;

        } else if (input.startsWith("done ")) {

            // If the user types "done X" where X is a non-zero integer, mark the task as complete
            start = CommandStart.DONE;

        } else if (input.startsWith("delete ")) {

            // If the user types "delete X" where X is a non-zero integer, remove the task
            start = CommandStart.DELETE;

        } else if (input.startsWith("todo ")) {

            // If the user types "todo [XXX]" where [XXX] is a substring, store substring as a Todo object
            start = CommandStart.TODO;

        } else if (input.startsWith("deadline ") && input.contains("/by ")) {

            // If the user types "deadline [XXX]" where [XXX] is a substring, store substring as a Deadline object
            start = CommandStart.DEADLINE;

        } else if (input.startsWith("event ") && input.contains("/at ")) {

            // If the user types "event [XXX]" where [XXX] is a substring, store substring as an Event object
            start = CommandStart.EVENT;

        } else if (!input.equals("bye")) {
            throw new DukeException("You've entered an unknown request... The bot doesn't know what that means!");
        }

        if (start != null) {
            switch(start) {
                case LIST:
                    command = new ListCommand(input, this.list);
                    System.out.println(command.reply());
                    break;
                case DONE:
                    command = new DoneCommand(input, this.list);
                    System.out.println(command.reply());
                    break;
                case DELETE:
                    command = new DeleteCommand(input, this.list);
                    System.out.println(command.reply());
                    break;
                case TODO:
                    command = new TodoCommand(input, this.list);
                    System.out.println(command.reply());
                    break;
                case DEADLINE:
                    command = new DeadlineCommand(input, this.list);
                    System.out.println(command.reply());
                    break;
                case EVENT:
                    command = new EventCommand(input, this.list);
                    System.out.println(command.reply());
                    break;
            }
        }
    }
}