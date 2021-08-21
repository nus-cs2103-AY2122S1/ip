import java.util.Scanner;

/**
 * Project Duke: Incrementally building a Chatbot.
 *
 * Current Progress: Level 8. Date and Times
 *
 * Description:
 * On running the program, Duke greets the user and awaits for inputted commands:
 *   - 'todo x' -> adds a ToDo task of x with no date/time attached
 *   - 'deadline x /by a b' -> adds a Deadline task of x that needs to be done by date a and time b (time is optional)
 *   - 'event x /at a b c' -> adds an Event task of x that is on date a and starts at time b and ends at time c
 *   - 'list' -> displays current list of tasks
 *   - 'check x' -> displays list of tasks due on date x
 *   - 'done x' -> marks Task x as done
 *   - 'delete x' -> deletes Task x from the task list
 *   - 'bye' -> exits the program
 *   - any other String of different patterns -> throws an exception
 *
 * CS2103T Individual Project AY 21/22 Sem 1
 * @author Benedict Chua
 */
public class Duke {
    public static void main(String[] args) {
        // Initialise program
        Storage storage = new Storage();
        TaskList tasksList = new TaskList(storage.retrieveData(), storage);
        Parser parser = new Parser(tasksList);
        Scanner sc = new Scanner(System.in);
        boolean isExit = false;

        // Greets user
        Ui.showLine();
        Ui.showWelcome();
        Ui.showLine();
        Ui.newLine();

        // Carries out commands inputted by user into the Scanner
        while (!isExit) {
            try {
                Ui.showLine();
                String input = sc.nextLine();
                Command command = parser.getCommand(input);
                command.execute();
                isExit = command.isExit();
            } catch (DukeException e) {
                Ui.displayMessage(new String[] {e.toString()});
            } finally {
                Ui.showLine();
                Ui.newLine();
            }
        }
    }
}
