import java.util.Scanner;

public class Duke {
    private TaskList tasks = new TaskList();

    private void start() {
        //Welcome message
        Ui.printWelcomeMessage();
        tasks = Storage.load();

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
                    Ui.printGoodbyeMessage();
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
        sc.close();
    }

    public static void main(String[] args) {
        new Duke().start();
    }
}
