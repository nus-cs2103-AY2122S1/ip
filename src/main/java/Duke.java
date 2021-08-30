import java.util.Scanner;

public class Duke {

    // List to store all user inputs
    private final TaskList taskList;
    private final Storage storage;
    private final Ui ui;
    private final Parser parser;

    public Duke(){
        taskList = new TaskList();
        storage = new Storage();
        ui = new Ui();
        parser = new Parser(taskList, storage, ui);
    }

    private void initiateSystem() {
        taskList.loadFromStorage(storage.load());
        ui.greet();
        Scanner sc = new Scanner(System.in);
        boolean isActive = true;

        while (isActive) {
            String userInput = sc.nextLine();
            try {
                Command command = parser.parseUserInput(userInput);
                command.runCommand();
                isActive = !(command instanceof ExitCommand);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
        sc.close();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.initiateSystem();
    }
}
