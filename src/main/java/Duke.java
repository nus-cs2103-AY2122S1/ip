import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;


    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = storage.load();
    }

    private void run() {
        //Welcome message
        ui.printWelcomeMessage();

        Scanner sc = new Scanner(System.in);
        Parser parser = new Parser(tasks);
        boolean end = false;

        //Main functionality of Duke
        while(!end) {
            String command = "";
            if(sc.hasNextLine()) {
                command = sc.nextLine().trim();
            }
            try {
                if (parser.parse(command) == 1) {
                    end = true;
                    ui.printGoodbyeMessage();
                    storage.save(tasks);
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
        sc.close();
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
