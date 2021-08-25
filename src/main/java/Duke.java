import java.util.Scanner;

/**
 * Represents a Personal Assistant Chatbot that helps a person to keep track of various things.
 */
public class Duke {
    protected static Storage storage;
    protected static TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (DukeException e) {
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.greetUser();
        Scanner sc = new Scanner(System.in);
        String command;
        boolean isExit = false; 

        while (!isExit) {
            try {
                System.out.println("What's your next command?\n");
                command = sc.nextLine();  
                isExit = Parser.parse(command);
                // ui.showLine(); // show the divider line ("_______")
                // c.execute(tasks, ui, storage);
                // isExit = c.isExit();
            } catch (DukeException e) {
                Ui.printFormattedMessage(e.getMessage());
            }
        }
        sc.close();
    }

    public static void main(String[] args) {
        new Duke("../../../data/duke.txt").run();
    }
}