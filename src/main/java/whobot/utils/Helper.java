package whobot.utils;

import whobot.main.UI;

import java.util.Arrays;
import java.util.List;

/***
 * Class to Display all Help Messages
 */
public class Helper {

    /** UI to which output should be sent */
    private final UI ui;

    /***
     * Helper Class Constructor
     * @param ui UI to which output should be sent
     */
    public Helper(UI ui) {
        this.ui = ui;
    }

    /***
     * Shows the Main Help Page
     */
    public void showMainHelp() {
        String helpString = "These are the list of commands you can give me:\n";
        helpString += "\t\t\t 1. "+ UI.COLOR_PURPLE + "list" + UI.COLOR_RESET
                + " -> Prints out the List of Tasks.\n";
        helpString += "\t\t\t 2. "+ UI.COLOR_PURPLE + "help #command" + UI.COLOR_RESET
                + " -> Prints out Command specific help page.\n";
        helpString += "\t\t\t 3. "+ UI.COLOR_PURPLE + "todo #description" + UI.COLOR_RESET
                + " -> Adds a ToDo Task to the List.\n";
        helpString += "\t\t\t 4. "+ UI.COLOR_PURPLE + "event #description /at #timing" + UI.COLOR_RESET
                + " -> Adds an Event Task to the List.\n";
        helpString += "\t\t\t 5. "+ UI.COLOR_PURPLE + "deadline #description /by #deadline" + UI.COLOR_RESET
                + " -> Adds a Deadline Task to the List.\n";
        helpString += "\t\t\t 6. "+ UI.COLOR_PURPLE + "show /on #date" + UI.COLOR_RESET
                + " -> Prints out the List of Tasks on Given Date.\n";
        helpString += "\t\t\t 7. "+ UI.COLOR_PURPLE + "done #index" + UI.COLOR_RESET
                + " -> Marks Task at #index in the List as completed.\n";
        helpString += "\t\t\t 8. "+ UI.COLOR_PURPLE + "undo #index" + UI.COLOR_RESET
                + " -> Marks Task at #index in the List as incomplete.\n";
        helpString += "\t\t\t 9. "+ UI.COLOR_PURPLE + "delete #index" + UI.COLOR_RESET
                + " -> Delete Task at #index in the List.\n";
        helpString += "\t\t\t 10. "+ UI.COLOR_PURPLE + "bye/goodbye" + UI.COLOR_RESET
                + " -> Quits the ChatBot.";
        ui.echo(helpString, UI.TYPE.COMPLETE);
    }

    /***
     * Shows the Help Page for "list" Command
     */
    private void showListHelp() {
        ui.echo(UI.COLOR_PURPLE + "list:" + UI.COLOR_RESET +
                "\n\t\t\tThis command will print all tasks in your list." +
                "\n\t\t\tIt will be ordered based on timing and whether completed.", UI.TYPE.COMPLETE);
    }

    /***
     * Shows the Help Page for "todo" Command
     */
    private void showTodoHelp() {
        ui.echo(UI.COLOR_PURPLE + "todo #description:" + UI.COLOR_RESET +
                "\n\t\t\tThis command will add a new ToDo Task with the given description." +
                "\n\t\t\tThe description is required and this type of task has no timing." +
                "\n\t\t\tFor example: todo Call Bob", UI.TYPE.COMPLETE);
    }

    /***
     * Shows the Help Page for "event" Command
     */
    private void showEventHelp() {
        ui.echo(UI.COLOR_PURPLE + "event #description /at #timing:" + UI.COLOR_RESET +
                "\n\t\t\tThis command command will add a new Event Task with the given description and timing" +
                "\n\t\t\tThe description and timing are required. The timing should be of the format d/m/yyyy HH:mm." +
                "\n\t\t\tFor example: event Team Meeting /at 27/9/2021 16:00", UI.TYPE.COMPLETE);
    }

    /***
     * Shows the Help Page for "deadline" Command
     */
    private void showDeadlineHelp() {
        ui.echo(UI.COLOR_PURPLE + "deadline #description /by #deadline:" + UI.COLOR_RESET +
                "\n\t\t\tThis command will add a new Deadline Task with the given description and deadline." +
                "\n\t\t\tThe description and deadline are required and should be of the format d/m/yyyy HH:mm or d/m/yyyy." +
                "\n\t\t\tFor example: deadline Return Books /by 28/9/2021 18:00 or deadline Return Books /by 28/9/2021",
                UI.TYPE.COMPLETE);
    }

    /***
     * Shows the Help Page for "show" Command
     */
    private void showShowHelp() {
        ui.echo(UI.COLOR_PURPLE +"show /on #date:" + UI.COLOR_RESET +
                "\n\t\t\tThis command will show you on tasks on given date. " +
                "\n\t\t\t#date is required and must be in the format d/m/yyyy" +
                "\n\t\t\tFor example: show /on 28/9/2021", UI.TYPE.COMPLETE);
    }

    /***
     * Shows the Help Page for "done" Command
     */
    private void showDoneHelp() {
        ui.echo(UI.COLOR_PURPLE +"done #index:" + UI.COLOR_RESET +
                "\n\t\t\tThis command will mark the task at #index as done. #index is required." +
                "\n\t\t\tFor example: done 2", UI.TYPE.COMPLETE);
    }

    /***
     * Shows the Help Page for "undo" Command
     */
    private void showUndoHelp() {
        ui.echo(UI.COLOR_PURPLE + "undo #index:" + UI.COLOR_RESET +
                "\n\t\t\tThis command will mark the task at #index as not done. #index is required." +
                "\n\t\t\tFor example: undo 2", UI.TYPE.COMPLETE);
    }

    /***
     * Shows the Help Page for "delete" Command
     */
    private void showDeleteHelp() {
        ui.echo(UI.COLOR_PURPLE +"delete #index:" + UI.COLOR_RESET +
                "\n\t\t\tThis command will delete the task at #index. #index is required." +
                "\n\t\t\tFor example: delete 2", UI.TYPE.COMPLETE);
    }

    /***
     * Shows the Help Page for invalid command
     */
    private void showDefaultHelp() {
        ui.echo("That command is invalid or does not have a specific help page.", UI.TYPE.START);
        ui.echo("Input \"help\" to see list of all valid commands.", UI.TYPE.END);
    }

    /***
     * Shows the Help page for the given command
     * @param command Command to show help for
     */
    public void showCommandHelp(String command) {
        List<String> commandList = Arrays.asList("list", "todo", "event", "deadline", "show", "done", "undo", "delete");
        int ind = commandList.indexOf(command);
        switch (ind) {
            case 0 -> showListHelp();
            case 1 -> showTodoHelp();
            case 2 -> showEventHelp();
            case 3 -> showDeadlineHelp();
            case 4 -> showShowHelp();
            case 5 -> showDoneHelp();
            case 6 -> showUndoHelp();
            case 7 -> showDeleteHelp();
            default -> showDefaultHelp();
        }
    }

}
