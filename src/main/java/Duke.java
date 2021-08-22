import java.time.DateTimeException;

public class Duke {
    private static final Ui ui = new Ui();
    private static final TaskManager taskManager = new TaskManager();

    public static void main(String[] args) {
        ui.greet();
        boolean isExit = false;
        while(!isExit) {
            String userInput = ui.getUserInput();
            try {
                Command c = Parser.parse(userInput);
                c.execute(taskManager, ui);
                isExit = c.isExit();
            } catch (DukeException | IllegalArgumentException | DateTimeException e) {
                ui.handleError(e);
            }
        }
    }
}
