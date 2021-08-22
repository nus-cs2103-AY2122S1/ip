import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Helper {

    public static void showMainHelp() {
        String helpString = "These are the list of commands you can give me:\n";
        helpString += "\t\t\t 1. "+ Duke.COLOR_PURPLE + "list" + Duke.COLOR_RESET
                + " -> Prints out the List of Tasks.\n";
        helpString += "\t\t\t 2. "+ Duke.COLOR_PURPLE + "help #command" + Duke.COLOR_RESET
                + " -> Prints out Command specific help page.\n";
        helpString += "\t\t\t 3. "+ Duke.COLOR_PURPLE + "todo #description" + Duke.COLOR_RESET
                + " -> Adds a ToDo Task to the List\n";
        helpString += "\t\t\t 4. "+ Duke.COLOR_PURPLE + "event #description /at #timing" + Duke.COLOR_RESET
                + " -> Adds an Event Task to the List\n";
        helpString += "\t\t\t 5. "+ Duke.COLOR_PURPLE + "deadline #description /by #deadline" + Duke.COLOR_RESET
                + " -> Adds a Deadline Task to the List\n";
        helpString += "\t\t\t 6. "+ Duke.COLOR_PURPLE + "done #index" + Duke.COLOR_RESET
                + " -> Marks Task at #index in the List as completed\n";
        helpString += "\t\t\t 7. "+ Duke.COLOR_PURPLE + "undo #index" + Duke.COLOR_RESET
                + " -> Marks Task at #index in the List as incomplete\n";
        helpString += "\t\t\t 8. "+ Duke.COLOR_PURPLE + "delete #index" + Duke.COLOR_RESET
                + " -> Delete Task at #index in the List\n";
        helpString += "\t\t\t 9. "+ Duke.COLOR_PURPLE + "bye/goodbye" + Duke.COLOR_RESET
                + " -> Quits the ChatBot";
        Duke.echo(helpString, Duke.TYPE.COMPLETE);
    }

    public static void showListHelp() {
        Duke.echo(Duke.COLOR_PURPLE + "list:" + Duke.COLOR_RESET +
                "\n\t\t\tThis command will print all tasks in your list." +
                "\n\t\t\tIt will be ordered based on timing and whether completed.", Duke.TYPE.COMPLETE);
    }

    public static void showTodoHelp() {
        Duke.echo(Duke.COLOR_PURPLE + "todo #description:" + Duke.COLOR_RESET +
                "\n\t\t\tThis command will add a new ToDo Task with the given description." +
                "\n\t\t\tThe description is required and this type of task has no timing." +
                "\n\t\t\tFor example: todo Call Bob", Duke.TYPE.COMPLETE);
    }

    public static void showEventHelp() {
        Duke.echo(Duke.COLOR_PURPLE + "event #description /at #timing:" + Duke.COLOR_RESET +
                "\n\t\t\tThis command command will add a new Event Task with the given description and timing" +
                "\n\t\t\tThe description and timing are required. The timing should be of the format d/m/yyyy HH:mm." +
                "\n\t\t\tFor example: event Team Meeting /at 27/9/2021 16:00", Duke.TYPE.COMPLETE);
    }

    public static void showDeadlineHelp() {
        Duke.echo(Duke.COLOR_PURPLE + "deadline #description /by #deadline:" + Duke.COLOR_RESET +
                "\n\t\t\tThis command will add a new Deadline Task with the given description and deadline." +
                "\n\t\t\tThe description and deadline are required. The deadline should be of the format d/m/yyyy HH:mm or d/m/yyyy." +
                "\n\t\t\tFor example: deadline Return Books /by 28/9/2021 18:00 or deadline Return Books /by 28/9/2021", Duke.TYPE.COMPLETE);
    }

    public static void showDoneHelp() {
        Duke.echo(Duke.COLOR_PURPLE +"done #index:" + Duke.COLOR_RESET +
                "\n\t\t\tThis command will mark the task at #index as done. #index is required." +
                "\n\t\t\tFor example: done 2", Duke.TYPE.COMPLETE);
    }

    public static void showUndoHelp() {
        Duke.echo(Duke.COLOR_PURPLE + "undo #index:" + Duke.COLOR_RESET +
                "\n\t\t\tThis command will mark the task at #index as not done. #index is required." +
                "\n\t\t\tFor example: undo 2", Duke.TYPE.COMPLETE);
    }

    public static void showDeleteHelp() {
        Duke.echo(Duke.COLOR_PURPLE +"delete #index:" + Duke.COLOR_RESET +
                "\n\t\t\tThis command will delete the task at #index. #index is required." +
                "\n\t\t\tFor example: delete 2", Duke.TYPE.COMPLETE);
    }

    public static void showDefaultHelp() {
        Duke.echo("That command is invalid or does not have a specific help page.", Duke.TYPE.START);
        Duke.echo("Input \"help\" to see list of all valid commands.", Duke.TYPE.END);
    }

    public static void showCommandHelp(String command) {
        List<String> commandList = Arrays.asList("list", "todo", "event", "deadline", "done", "undo", "delete");
        int ind = commandList.indexOf(command);
        switch (ind) {
            case 0 -> showListHelp();
            case 1 -> showTodoHelp();
            case 2 -> showEventHelp();
            case 3 -> showDeadlineHelp();
            case 4 -> showDoneHelp();
            case 5 -> showUndoHelp();
            case 6 -> showDeleteHelp();
            default -> showDefaultHelp();
        }
    }

}
