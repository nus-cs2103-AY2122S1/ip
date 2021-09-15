package whobot.utils;

import java.util.Arrays;
import java.util.List;

import whobot.main.UI;

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
        assert ui != null;
    }

    /***
     * Shows the Main Help Page
     */
    public void showMainHelp() {
        String helpString = "These are the list of commands you can give me:\n";
        helpString += "\t\t\t 1. " + UI.COLOR_PURPLE + "list [#tagname]" + UI.COLOR_RESET
                + " -> Prints out the List of Tasks.\n";
        helpString += "\t\t\t 2. " + UI.COLOR_PURPLE + "help #command" + UI.COLOR_RESET
                + " -> Prints out Command specific help page.\n";
        helpString += "\t\t\t 3. " + UI.COLOR_PURPLE + "todo #description" + UI.COLOR_RESET
                + " -> Adds a ToDo Task to the List.\n";
        helpString += "\t\t\t 4. " + UI.COLOR_PURPLE + "event #description /at #timing" + UI.COLOR_RESET
                + " -> Adds an Event Task to the List.\n";
        helpString += "\t\t\t 5. " + UI.COLOR_PURPLE + "deadline #description /by #deadline" + UI.COLOR_RESET
                + " -> Adds a Deadline Task to the List.\n";
        helpString += "\t\t\t 6. " + UI.COLOR_PURPLE + "show /on #date" + UI.COLOR_RESET
                + " -> Prints out the List of Tasks on Given Date.\n";
        helpString += "\t\t\t 7. " + UI.COLOR_PURPLE + "find #string" + UI.COLOR_RESET
                + " -> Prints out the List of Tasks containing the Search String.\n";
        helpString += "\t\t\t 8. " + UI.COLOR_PURPLE + "done #index" + UI.COLOR_RESET
                + " -> Marks Task at #index in the List as completed.\n";
        helpString += "\t\t\t 9. " + UI.COLOR_PURPLE + "undo #index" + UI.COLOR_RESET
                + " -> Marks Task at #index in the List as incomplete.\n";
        helpString += "\t\t\t 10. " + UI.COLOR_PURPLE + "delete #index" + UI.COLOR_RESET
                + " -> Delete Task at #index in the List.\n";
        helpString += "\t\t\t 11. " + UI.COLOR_PURPLE + "tag #index /as #tagname" + UI.COLOR_RESET
                + " -> Tags Task at #index in the List with the given #tagname.\n";
        helpString += "\t\t\t 12. " + UI.COLOR_PURPLE + "untag #index" + UI.COLOR_RESET
                + " -> Untags Task at #index in the List.\n";
        helpString += "\t\t\t 13. " + UI.COLOR_PURPLE + "bye/goodbye" + UI.COLOR_RESET
                + " -> Quits the ChatBot.";
        ui.echo(helpString, UI.Type.COMPLETE);
    }

    /***
     * Shows the Help Page for "list" Command
     */
    private void showListHelp() {
        ui.echo(UI.COLOR_PURPLE + "list [#tagname]:" + UI.COLOR_RESET
                + "\n\t\t\tThis command will print all tasks in your list if no tagname is specified."
                + "\n\t\t\tIt will be ordered based on timing and whether completed."
                + "\n\t\t\tIf #tagname is specified then all the tasks under that tag will be displayed."
                + "\n\t\t\tOthers is the default tag for tasks that aren't tagged.",
                UI.Type.COMPLETE);
    }

    /***
     * Shows the Help Page for "todo" Command
     */
    private void showTodoHelp() {
        ui.echo(UI.COLOR_PURPLE + "todo #description:" + UI.COLOR_RESET
                + "\n\t\t\tThis command will add a new ToDo Task with the given description."
                + "\n\t\t\tThe description is required and this type of task has no timing."
                + "\n\t\t\tFor example: todo Call Bob", UI.Type.COMPLETE);
    }

    /***
     * Shows the Help Page for "event" Command
     */
    private void showEventHelp() {
        ui.echo(UI.COLOR_PURPLE + "event #description /at #timing:" + UI.COLOR_RESET
                + "\n\t\t\tThis command command will add a new Event Task with the given description and timing"
                + "\n\t\t\tThe description and timing are required. The timing should be of the format d/m/yyyy HH:mm."
                + "\n\t\t\tFor example: event Team Meeting /at 27/9/2021 16:00", UI.Type.COMPLETE);
    }

    /***
     * Shows the Help Page for "deadline" Command
     */
    private void showDeadlineHelp() {
        ui.echo(UI.COLOR_PURPLE + "deadline #description /by #deadline:" + UI.COLOR_RESET
                        + "\n\t\t\tThis command will add a new Deadline Task with the given description and deadline."
                        + "\n\t\t\tThe description and deadline are required and should be of the format "
                        + " d/m/yyyy HH:mm or d/m/yyyy."
                        + "\n\t\t\tFor example: deadline Return Books /by 28/9/2021 18:00 "
                        + "or deadline Return Books /by 28/9/2021",
                UI.Type.COMPLETE);
    }

    /***
     * Shows the Help Page for "show" Command
     */
    private void showShowHelp() {
        ui.echo(UI.COLOR_PURPLE + "show /on #date:" + UI.COLOR_RESET
                + "\n\t\t\tThis command will show you on tasks on given date. "
                + "\n\t\t\t#date is required and must be in the format d/m/yyyy"
                + "\n\t\t\tFor example: show /on 28/9/2021", UI.Type.COMPLETE);
    }

    /***
     * Shows the Help Page for "find" Command
     */
    private void showFindHelp() {
        ui.echo(UI.COLOR_PURPLE + "find #string:" + UI.COLOR_RESET
                + "\n\t\t\tThis command will show you on tasks that contain the given search string. "
                + "\n\t\t\t#string is required."
                + "\n\t\t\tFor example: find meeting", UI.Type.COMPLETE);
    }

    /***
     * Shows the Help Page for "done" Command
     */
    private void showDoneHelp() {
        ui.echo(UI.COLOR_PURPLE + "done #index:" + UI.COLOR_RESET
                + "\n\t\t\tThis command will mark the task at #index as done. #index is required."
                + "\n\t\t\tFor example: done 2", UI.Type.COMPLETE);
    }

    /***
     * Shows the Help Page for "undo" Command
     */
    private void showUndoHelp() {
        ui.echo(UI.COLOR_PURPLE + "undo #index:" + UI.COLOR_RESET
                + "\n\t\t\tThis command will mark the task at #index as not done. #index is required."
                + "\n\t\t\tFor example: undo 2", UI.Type.COMPLETE);
    }

    /***
     * Shows the Help Page for "delete" Command
     */
    private void showDeleteHelp() {
        ui.echo(UI.COLOR_PURPLE + "delete #index:" + UI.COLOR_RESET
                + "\n\t\t\tThis command will delete the task at #index. #index is required."
                + "\n\t\t\tFor example: delete 2", UI.Type.COMPLETE);
    }

    /***
     * Shows the Help Page for "tag" Command
     */
    private void showTagHelp() {
        ui.echo(UI.COLOR_PURPLE + "tag #index /as #tagname:" + UI.COLOR_RESET
                + "\n\t\t\tThis command will tag the task at #index with the given #tagname."
                + "\n\t\t\tBoth #index and #tagname are required."
                + "\n\t\t\tFor example: tag 2 /as Work", UI.Type.COMPLETE);
    }

    /***
     * Shows the Help Page for "untag" Command
     */
    private void showUntagHelp() {
        ui.echo(UI.COLOR_PURPLE + "untag #index:" + UI.COLOR_RESET
                + "\n\t\t\tThis command will untag the task at #index. #index is required."
                + "\n\t\t\tThe task will be reset to the default tag of \"Others\"."
                + "\n\t\t\tFor example: untag 2", UI.Type.COMPLETE);
    }

    /***
     * Shows the Help Page for invalid command
     */
    private void showDefaultHelp() {
        ui.echo("That command is invalid or does not have a specific help page.", UI.Type.START);
        ui.echo("Input \"help\" to see list of all valid commands.", UI.Type.END);
    }

    /***
     * Shows the Help page for the given command
     * @param command Command to show help for
     */
    public void showCommandHelp(String command) {
        List<String> commandList = Arrays.asList("list", "todo", "event", "deadline", "show",
                "done", "undo", "delete", "tag", "untag", "find");
        int ind = commandList.indexOf(command);
        switch (ind) {
        case 0: showListHelp();
            break;
        case 1: showTodoHelp();
            break;
        case 2: showEventHelp();
            break;
        case 3: showDeadlineHelp();
            break;
        case 4: showShowHelp();
            break;
        case 5: showDoneHelp();
            break;
        case 6: showUndoHelp();
            break;
        case 7: showDeleteHelp();
            break;
        case 8: showTagHelp();
            break;
        case 9: showUntagHelp();
            break;
        case 10: showFindHelp();
            break;
        default: showDefaultHelp();
            break;
        }
    }

}
